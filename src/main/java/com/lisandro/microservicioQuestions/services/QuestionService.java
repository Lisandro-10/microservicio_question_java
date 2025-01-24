package com.lisandro.microservicioQuestions.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisandro.microservicioQuestions.dtos.AnswerDto;
import com.lisandro.microservicioQuestions.dtos.QuestionAdminDto;
import com.lisandro.microservicioQuestions.dtos.QuestionDto;
import com.lisandro.microservicioQuestions.exceptions.RecordNotFoundExcepcion;
import com.lisandro.microservicioQuestions.models.Answer;
import com.lisandro.microservicioQuestions.models.Question;
import com.lisandro.microservicioQuestions.repositories.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerService answerService;
	
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
	
	public Question getQuestionById(Long questionId) {
		Optional <Question> questionDb = questionRepository.findById((long) questionId);
		if(questionDb != null) return questionDb.get();
		throw new RecordNotFoundExcepcion("Quesion not found.");
	}
	
	public List<QuestionAdminDto> getQuestionsByIdAdmin(Long articleId) {
		return adaptQuestionAdminData(getQuestions(articleId));
	}
	
	public List<QuestionDto> getQuestionsByIdClient(Long questionId) {
		return filterQuestionsData(getQuestions(questionId));
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
	
	public List<QuestionAdminDto> adaptQuestionAdminData(List<Question> questions){
		List<QuestionAdminDto> questionsAdminDto = new ArrayList<>();
		for (Question question : questions) {
			List<Answer> answerList = question.getAnswers();
			if(answerList.isEmpty()) {
				questionsAdminDto.add(new QuestionAdminDto(question, new ArrayList<>())); 
			}else {
				List<AnswerDto> answersDto = new ArrayList<>();
				for (Answer answer : answerList) {
					 answersDto.add(answerService.convertAnswerData(answer, question.getId()));
				}
				questionsAdminDto.add(new QuestionAdminDto(question, answersDto));
			}
		}
		return questionsAdminDto;
	}
	
	public List<Question> getQuestions(Long articleId) {
		List<Optional<Question>> optionalQuestion = questionRepository.findByArticleId(articleId);
		if(optionalQuestion != null) {
			List<Question> questions = new ArrayList<>();
			for (Optional<Question> optional : optionalQuestion) {
				questions.add(optional.get());
			}
			return questions;
		}
		throw new RecordNotFoundExcepcion("Error --> object not found.");
	}
	
}
