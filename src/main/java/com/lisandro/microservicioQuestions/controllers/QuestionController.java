package com.lisandro.microservicioQuestions.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lisandro.microservicioQuestions.models.Question;


//Chequear bien tema rutas
@RestController
@RequestMapping(value ={"/", ""})
public class QuestionController {

	// Post question
	@PostMapping(value = "/{articleId}/questions")
	public ResponseEntity<Question> createQuestion(){
		return ResponseEntity.ok(null);
	}
	
	//Get question
	@GetMapping("/{articleId}/questions/questionId")
	public ResponseEntity<List<Question>> getQuestion(){
		//dependiendo el user (client or owner) sera la data que traiga de question
		//traer las respuestas asociadas
		
		return ResponseEntity.ok(null);
	}
	
	
	
	
	
	
}
