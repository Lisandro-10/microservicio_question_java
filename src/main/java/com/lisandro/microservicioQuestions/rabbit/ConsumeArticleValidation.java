package com.lisandro.microservicioQuestions.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisandro.microservicioQuestions.rabbit.dto.NewArticleValidationData;
import com.lisandro.microservicioQuestions.services.QuestionService;

@Service
public class ConsumeArticleValidation {
	 	@Autowired
	    DirectConsumer directConsumer;

	    @Autowired
	    QuestionService questionService;


	    public void init() {
	    	System.out.println("Consumidor de RabbitMQ en Questions iniciando...");
	        directConsumer.init("article_exist", "cart_article_exist");
	        directConsumer.addProcessor("article-data", this::articleValidation);
	        directConsumer.start();
	    }


	    public void articleValidation(RabbitEvent event) {
	    	NewArticleValidationData articleExist = NewArticleValidationData.fromJson(event.message.toString());
	        questionService.activateQuestion(articleExist.questionId, articleExist.articleId);
	    }
}
