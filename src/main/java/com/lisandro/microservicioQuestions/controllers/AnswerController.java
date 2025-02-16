package com.lisandro.microservicioQuestions.controllers;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lisandro.microservicioQuestions.models.Answer;
import com.lisandro.microservicioQuestions.security.ValidateAdminUser;
import com.lisandro.microservicioQuestions.security.ValidateLoggedIn;
import com.lisandro.microservicioQuestions.services.AnswerService;

@RestController
@RequestMapping(path = "/v1/answer")
public class AnswerController {
	
	@Autowired
	private AnswerService answerService;
	
	@PostMapping("/{questionId}")
	public ResponseEntity<?> createAnswer(@ValidateLoggedIn @RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @PathVariable Long questionId, @RequestBody Answer answer) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(answerService.createAnswer(answer, questionId));
	}	

}
