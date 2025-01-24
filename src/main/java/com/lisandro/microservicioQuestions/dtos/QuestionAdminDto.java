package com.lisandro.microservicioQuestions.dtos;

import java.util.List;

import com.lisandro.microservicioQuestions.models.Question;

public class QuestionAdminDto {

	private Question question;
	private List<AnswerDto> answersDto;
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public List<AnswerDto> getAnswersDto() {
		return answersDto;
	}
	public void setAnswersDto(List<AnswerDto> answersDto) {
		this.answersDto = answersDto;
	}
	public QuestionAdminDto(Question question, List<AnswerDto> answersDto) {
		super();
		this.question = question;
		this.answersDto = answersDto;
	}
	
	
}
