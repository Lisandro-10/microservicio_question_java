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
import com.lisandro.microservicioQuestions.repositories.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	public Question createQuestion(QuestionDto questionData, Long articleId) throws Exception{
		  Question newQuestion = new Question();
		  newQuestion.setCreationDate(new Date());
		  newQuestion.setCustomerName(questionData.getCustomerName());
		  //TODO revisar que articleId existe
		  newQuestion.setArticleId(articleId);
		  newQuestion.setQuestionDescription(questionData.getQuestionDescription());
		  try {
			return questionRepository.save(newQuestion);
		} catch (Exception e) {
			throw new Exception("Error: " + e.getMessage());
		}
	}

	public List<QuestionDto> getQuestionsByIdClient(Long articleId) {
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
	
}
