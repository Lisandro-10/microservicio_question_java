package com.lisandro.microservicioQuestions.controllers;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lisandro.microservicioQuestions.dtos.QuestionDto;
import com.lisandro.microservicioQuestions.models.Question;
import com.lisandro.microservicioQuestions.security.ValidateLoggedIn;
import com.lisandro.microservicioQuestions.services.QuestionService;


@RestController
@RequestMapping(path = "/v1")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;

	// Post question
	@PostMapping(value = "/{articleId}/questions")
	public ResponseEntity<Question> createQuestion(@ValidateLoggedIn @RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @PathVariable String articleId, @RequestBody QuestionDto questionDto) throws Exception{
		Question newQuestion = questionService.createQuestion(questionDto, articleId);
		return ResponseEntity.status(HttpStatus.CREATED).body(newQuestion);
	}
	
	//Get questions
	@GetMapping("/{articleId}/questions")
	public ResponseEntity<?> getQuestions(@ValidateLoggedIn @RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @PathVariable String articleId){
		return ResponseEntity.ok(questionService.getQuestionsById(articleId));
	}
	
	
	
	
	
	
}
