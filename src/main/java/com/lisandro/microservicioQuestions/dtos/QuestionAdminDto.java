package com.lisandro.microservicioQuestions.dtos;

import java.util.Date;

/* Response
 * {
 *  "id": {"id"},
            "articleId": {"articleId"},
            "questionId": {"questionId"},
            "customerName": {"customerName"},
            "description": {"pregunta"},
            "creationDate": {"creationDate"},
            "response": {"responseInfo"},
            "responseDate":{"responseDate"}
     }
 * */
public class QuestionAdminDto {

	private Long questionId;
	private String customerName;
	private String questionDescription;
	private Long articleId;
	private Date creationDate;
	private String response;
	private Date responseDate;
	
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getQuestionDescription() {
		return questionDescription;
	}
	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public Date getResponseDate() {
		return responseDate;
	}
	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}
	
}
