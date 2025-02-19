package com.lisandro.microservicioQuestions.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisandro.microservicioQuestions.exceptions.QuestionIdNull;
import com.lisandro.microservicioQuestions.rabbit.dto.NewArticleValidationData;
import com.lisandro.microservicioQuestions.services.QuestionService;

@Service
public class ConsumeArticleValidation {
	 	@Autowired
	    DirectConsumer directConsumer;

	    @Autowired
	    QuestionService questionService;


	    public void init() {
	        directConsumer.init("question", "question_article_exist");
	        directConsumer.addProcessor("question_article_exist", this::articleValidation);
	        directConsumer.start();
	    }


	    public void articleValidation(RabbitEvent event) {
    	    try {
    	        NewArticleValidationData articleExist = NewArticleValidationData.fromJson(event.message.toString());
    	        Long questionId = articleExist.questionId;
    	        String articleId = articleExist.articleId;
    	        if(questionId == 0) {
    	        	throw new QuestionIdNull("Question id null");
    	        }
    	        if(!articleExist.valid) {
    	        	System.out.println("Articulo con id: " + articleId + " NO existe!");
    	        	questionService.disableQuestion(questionId);
    	        }else {
    	        	questionService.activateQuestion(questionId, articleId);
    	        }
    	        
    	    } catch (Exception e) {
    	        System.err.println("Error al procesar el mensaje: " + e.getMessage());
    	        e.printStackTrace();
    	    }
	    }
}
