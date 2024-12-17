package com.lisandro.microservicioQuestions.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisandro.microservicioQuestions.dtos.QuestionDto;
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
			throw new Exception("Error");
		}
	}
	
}
