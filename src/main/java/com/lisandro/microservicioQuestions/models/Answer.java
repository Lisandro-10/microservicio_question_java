package com.lisandro.microservicioQuestions.models;

import java.util.Date;

import com.google.gson.annotations.Expose;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Answers")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String ownerName;
	@Column
	private String answerDescription;
	@Column
	private Date creationDate;
	
	@ManyToOne
	@JoinColumn(name = "id_question")
	@Expose(serialize = false)
	private Question question;
	
	public String getAnswerDescription() {
		return answerDescription;
	}
	public void setAnswerDescription(String answerDescription) {
		this.answerDescription = answerDescription;
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
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
		
	public Answer(Long id, String ownerName, String answerDescription, Date creationDate, Question question) {
		super();
		this.id = id;
		this.ownerName = ownerName;
		this.answerDescription = answerDescription;
		this.creationDate = creationDate;
		this.question = question;
	}
	public Answer() {
		 
	}
	
}
