package com.lisandro.microservicioQuestions.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.annotations.SerializedName;

@Service
public class EmitArticleValidation {
	
	@Autowired
	private RabbitService rabbitService;

    public void sendArticleValidation(String articleId) {
        ArticleValidationData data = new ArticleValidationData(articleId);

        RabbitEvent eventToSend = new RabbitEvent();
        eventToSend.type = "article-data";
        eventToSend.exchange = "question";
        eventToSend.queue = "question";
        eventToSend.message = data;

        rabbitService.publish("catalog", "catalog", eventToSend);
    }

    private static class ArticleValidationData {
        ArticleValidationData(String articleId) {
            this.articleId = articleId;
        }

        @SerializedName("articleId")
        public final String articleId;
    }
}
