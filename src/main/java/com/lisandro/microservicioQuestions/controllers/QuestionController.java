package com.lisandro.microservicioQuestions.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lisandro.microservicioQuestions.dtos.QuestionDto;
import com.lisandro.microservicioQuestions.models.Question;
import com.lisandro.microservicioQuestions.services.QuestionService;


//Chequear bien tema rutas
@RestController
@RequestMapping(value ={"/", ""})
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;

	// Post question
	@PostMapping(value = "/{articleId}/questions")
	public ResponseEntity<Question> createQuestion(@PathVariable Long articleId, @RequestBody QuestionDto questionDto) throws Exception{
		return ResponseEntity.status(HttpStatus.CREATED).body(questionService.createQuestion(questionDto, articleId));
	}
	
	//Get question
	@GetMapping("/{articleId}/questions/questionId")
	public ResponseEntity<List<Question>> getQuestion(){
		//dependiendo el user (client or owner) sera la data que traiga de question
		//traer las respuestas asociadas
		
		return ResponseEntity.ok(null);
	}
	
	
	
	
	
	
}
