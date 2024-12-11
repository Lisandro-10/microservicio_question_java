package com.lisandro.microservicioQuestions.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Questions")
public class Question {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLong;
	@Column
	private String customerName;
	@Column
	private String questionDescription;
	@Column
	private Date creationDate;
	
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
	
//	public Question(String customerName, String questionDescription, Date creationDate) {
//		super();
//		this.customerName = customerName;
//		this.questionDescription = questionDescription;
//		this.creationDate = creationDate;
//	}
	
	
}
