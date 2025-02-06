package com.lisandro.microservicioQuestions.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.annotations.SerializedName;

@Service
public class EmitArticleValidation {
	
	@Autowired
	private DirectPublisher rabbitService;

    public void sendArticleValidation(String articleId, Long questionId) {
        ArticleValidationData data = new ArticleValidationData(articleId, questionId);

        RabbitEvent eventToSend = new RabbitEvent();
        eventToSend.type = "question-validation";
        eventToSend.exchange = "article_exist";
        eventToSend.queue = "cart_article_exist";
        eventToSend.message = data;
        

        rabbitService.publish("article_exist", "catalog_article_exist", eventToSend);
    }

    private static class ArticleValidationData {
        ArticleValidationData(String articleId, Long questionId) {
            this.articleId = articleId;
            this.questionId = questionId;
        }

        @SerializedName("articleId")
        public final String articleId;
        @SerializedName("questionId")
        public final Long questionId;
    }
}
