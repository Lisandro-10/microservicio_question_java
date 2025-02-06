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
	        directConsumer.init("article_exist", "catalog_article_exist");
	        directConsumer.addProcessor("article-data", this::articleData);
	        directConsumer.start();
	    }

	    /**
	     * @api {direct} order/article-data Validar Artículos
	     * @apiGroup RabbitMQ GET
	     * @apiDescription Antes de iniciar las operaciones se validan los artículos contra el catalogo.
	     * @apiExample {json} Mensaje
	     * {
	     * "type": "article-data",
	     * "message" : {
	     * "cartId": "{cartId}",
	     * "articleId": "{articleId}",
	     * "valid": True|False
	     * }
	     * }
	     */
	    public void articleData(RabbitEvent event) {
	    	NewArticleValidationData articleExist = NewArticleValidationData.fromJson(event.message.toString());
	        questionService.activateQuestion(articleExist.questionId, articleExist.articleId);
	    }
}
