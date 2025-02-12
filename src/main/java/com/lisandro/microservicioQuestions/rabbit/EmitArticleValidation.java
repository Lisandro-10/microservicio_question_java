package com.lisandro.microservicioQuestions.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.annotations.SerializedName;

@Service
public class EmitArticleValidation {
	
	@Autowired
	private DirectPublisher rabbitService;

    public void sendArticleValidation(String articleId, Long referenceId) {
        ArticleValidationData data = new ArticleValidationData(articleId, referenceId+"");

        RabbitEvent eventToSend = new RabbitEvent();
        eventToSend.type = "question_article_exist";
        eventToSend.exchange = "question";
        eventToSend.queue = "question_article_exist";
        eventToSend.routing_key= "question_article_exist";
        eventToSend.message = data;
        
        rabbitService.publish("article_exist", "catalog_article_exist", eventToSend);
    }

    private static class ArticleValidationData {
        ArticleValidationData(String articleId, String referenceId) {
            this.articleId = articleId;
            this.referenceId = referenceId;
        }

        @SerializedName("articleId")
        public final String articleId;
        @SerializedName("referenceId")
        public final String referenceId;
    }
}
