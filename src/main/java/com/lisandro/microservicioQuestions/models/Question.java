package com.lisandro.microservicioQuestions.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lisandro.microservicioQuestions.enums.QuestionStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Questions")
public class Question {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String customerName;
	@Column
	private String questionDescription;
	@Column
	private Date creationDate;
	@Column
	private String articleId;
	@Enumerated(EnumType.STRING)
	private QuestionStatus status;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
	private List<Answer> answers = new ArrayList<Answer>();
	
	
	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getQuestionDescription() {
		return questionDescription;
	}
	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	public QuestionStatus getStatus() {
		return status;
	}

	public void setStatus(QuestionStatus status) {
		this.status = status;
	}

	
	
	
	
	
}
