package com.lisandro.microservicioQuestions.models;

import java.util.Date;

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
	public Answer(String ownerName, String answerDescription, Date creationDate) {
		super();
		this.ownerName = ownerName;
		this.answerDescription = answerDescription;
		this.creationDate = creationDate;
	}
	
	public Answer() {
		 
	}
	
}
