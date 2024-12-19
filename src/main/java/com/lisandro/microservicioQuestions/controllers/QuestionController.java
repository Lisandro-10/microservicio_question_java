package com.lisandro.microservicioQuestions.controllers;

import java.util.List;

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
import com.lisandro.microservicioQuestions.security.ValidateAdminUser;
import com.lisandro.microservicioQuestions.services.QuestionService;


//Chequear bien tema rutas
@RestController
@RequestMapping(path = "/v1")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;

	// Post question
	@PostMapping(value = "/{articleId}/questions")
	public ResponseEntity<Question> createQuestion(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @PathVariable Long articleId, @RequestBody QuestionDto questionDto) throws Exception{
		return ResponseEntity.status(HttpStatus.CREATED).body(questionService.createQuestion(questionDto, articleId));
	}
	
	
	//Get question
	@GetMapping("/{articleId}/questions/{questionId}")
	public ResponseEntity<?> getQuestion(@ValidateAdminUser @RequestHeader(HttpHeaders.AUTHORIZATION) String auth ,@PathVariable Long articleId, @PathVariable Long questionId){
		//TODO usar spring security para obtener sesion y data del usuario
		// de momento lo paso en la ruta
		return ResponseEntity.ok("Success");
	}
	
	
	
	
	
	
}
