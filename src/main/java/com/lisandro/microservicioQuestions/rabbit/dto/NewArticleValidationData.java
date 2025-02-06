package com.lisandro.microservicioQuestions.rabbit.dto;

import com.google.gson.annotations.SerializedName;
import com.lisandro.microservicioQuestions.utils.validators.MinLen;
import com.lisandro.microservicioQuestions.utils.validators.Required;
import com.lisandro.microservicioQuestions.models.ArticleValidationEvent;
import com.lisandro.microservicioQuestions.utils.gson.GsonTools;

public class NewArticleValidationData {
	@SerializedName("referenceId")
    @Required
    public Long questionId;

    @SerializedName("articleId")
    @Required
    @MinLen(1)
    public String articleId;

    @SerializedName("valid")
    public boolean valid;

    @SerializedName("stock")
    public int stock;

    @SerializedName("price")
    public double price;

    public static NewArticleValidationData fromJson(String json) {
        return GsonTools.gson().fromJson(json, NewArticleValidationData.class);
    }

    public ArticleValidationEvent toArticleValidationEvent() {
        return new ArticleValidationEvent(this.articleId, this.valid, this.stock, this.price);
    }
}
