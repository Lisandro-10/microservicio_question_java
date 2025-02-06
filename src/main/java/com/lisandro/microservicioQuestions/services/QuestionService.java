package com.lisandro.microservicioQuestions.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisandro.microservicioQuestions.dtos.QuestionDto;
import com.lisandro.microservicioQuestions.exceptions.RecordNotFoundExcepcion;
import com.lisandro.microservicioQuestions.models.Answer;
import com.lisandro.microservicioQuestions.models.Question;
import com.lisandro.microservicioQuestions.rabbit.ConsumeArticleValidation;
import com.lisandro.microservicioQuestions.rabbit.EmitArticleValidation;
import com.lisandro.microservicioQuestions.repositories.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private EmitArticleValidation rabbitController;
	
	public Question createQuestion(QuestionDto questionData, String articleId) throws Exception{
		rabbitController.sendArticleValidation(articleId, questionData.getId());
		Question newQuestion = new Question();
		newQuestion.setCreationDate(new Date());
		newQuestion.setCustomerName(questionData.getCustomerName());
		newQuestion.setArticleId(null);
		newQuestion.setActive(false);
		newQuestion.setQuestionDescription(questionData.getQuestionDescription());
		try {
			return questionRepository.save(newQuestion);
		} catch (Exception e) {
			throw new Exception("Error: " + e.getMessage());
		}
	}

	public List<QuestionDto> getQuestionsByIdClient(String articleId) {
		List<Optional<Question>> optionalQuestion = questionRepository.findByArticleId(articleId);
		if(optionalQuestion != null) {
			List<Question> questions = new ArrayList<>();
			for (Optional<Question> optional : optionalQuestion) {
				questions.add(optional.get());
			}
			return filterQuestionsData(questions);
		}
		throw new RecordNotFoundExcepcion("Error --> object not found.");
	}
	
	public List<QuestionDto> filterQuestionsData(List<Question> questions){
		List<QuestionDto> questionsDto = new ArrayList<>();
		for (Question question : questions) {
			QuestionDto questionDto = new QuestionDto();
			questionDto.setCustomerName(question.getCustomerName());
			questionDto.setQuestionDescription(question.getQuestionDescription());
			List<Answer> answers = question.getAnswers();
			if(answers != null) {
				List<String> answersDescription = new ArrayList<String>();
				for(Answer answer: answers) {
					answersDescription.add(answer.getAnswerDescription());
				}
				questionDto.setAnswers(answersDescription);
			}
			questionsDto.add(questionDto);
		}
		return questionsDto;
	}
	
	public void activateQuestion(Long questionId,String articleId) {
		Optional<Question> questionDb= questionRepository.findById(questionId);
		if(questionDb != null) {
			questionDb.get().setActive(true);
			System.out.println("Articulo verificado! La pregunta " + questionDb.get().getQuestionDescription() + " se encuentra activa.");
		}
		
	}
}
