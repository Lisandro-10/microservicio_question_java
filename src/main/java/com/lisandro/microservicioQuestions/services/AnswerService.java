package com.lisandro.microservicioQuestions.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisandro.microservicioQuestions.models.Answer;
import com.lisandro.microservicioQuestions.repositories.AnswerRepository;

@Service
public class AnswerService {
	
	@Autowired
	private QuestionService questionService;
	@Autowired
	private AnswerRepository answerRepository;

	public Answer createAnswer(Answer answer, int questionId) {
		answer.setQuestion(questionService.getQuestionById(questionId));
		answer.setCreationDate(new Date());
		return  answerRepository.save(answer);
		
	}
}
