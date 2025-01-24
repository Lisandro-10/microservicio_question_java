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
import com.lisandro.microservicioQuestions.security.ValidateAdminUser;
import com.lisandro.microservicioQuestions.services.QuestionService;


//Chequear bien tema rutas
@RestController
@RequestMapping(path = "/v1")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
//	@Autowired
//	private TokenService tokenService;

	// Post question
	@PostMapping(value = "/{articleId}/questions")
	public ResponseEntity<Question> createQuestion(@ValidateAdminUser @RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @PathVariable Long articleId, @RequestBody QuestionDto questionDto) throws Exception{
		Question newQuestion = questionService.createQuestion(questionDto, articleId);
		System.out.println(newQuestion.getCreationDate());
		return ResponseEntity.status(HttpStatus.CREATED).body(newQuestion);
	}
	
	
//	@GetMapping("/{articleId}/questions")
//	public ResponseEntity<?> getQuestion(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth ,@PathVariable Long articleId){
//		String[] userPermissions = tokenService.getUser(auth.substring(7)).getPermissions();
//		for (String role : userPermissions) {
//			if(role.equals("admin")) return ResponseEntity.ok(questionService.getQuestionsByIdAdmin(articleId));
//		}
//		return ResponseEntity.ok(questionService.getQuestionsByIdClient(articleId));
//		
//	}
	
	//Get question for admin
	@GetMapping("/{articleId}/admin/questions")
	public ResponseEntity<?> getQuestions(@ValidateAdminUser @RequestHeader(HttpHeaders.AUTHORIZATION) String auth ,@PathVariable Long articleId){
		return ResponseEntity.ok(questionService.getQuestionsByIdAdmin(articleId));
	}
	
	//Get question for client
	@GetMapping("/{articleId}/questions")
	public ResponseEntity<?> getQuestions(@PathVariable Long articleId){
		return ResponseEntity.ok(questionService.getQuestionsByIdClient(articleId));
	}
	
	
	
	
	
	
}
