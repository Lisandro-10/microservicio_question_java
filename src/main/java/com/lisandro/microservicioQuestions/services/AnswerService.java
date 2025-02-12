package com.lisandro.microservicioQuestions.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisandro.microservicioQuestions.dtos.AnswerDto;
import com.lisandro.microservicioQuestions.enums.QuestionStatus;
import com.lisandro.microservicioQuestions.models.Answer;
import com.lisandro.microservicioQuestions.models.Question;
import com.lisandro.microservicioQuestions.repositories.AnswerRepository;
import com.lisandro.microservicioQuestions.repositories.QuestionRepository;

@Service
public class AnswerService {
	
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;

	public AnswerDto createAnswer(Answer answer, Long questionId) throws Exception {
		try {
			Optional<Question> questionDb = questionRepository.findById(questionId);
			if(questionDb != null && questionDb.get().getStatus() == QuestionStatus.VALID) {
				answer.setQuestion(questionDb.get());
			} else {
				throw new Exception("Error: La pregunta no existe o no fue validada todavia, intente de nuevo mas tarde.");
			}
			answer.setCreationDate(new Date());
			return  convertAnswerData(answerRepository.save(answer), questionId);
		} catch (Exception e) {
			throw new Exception("Error: " + e.getMessage());
		}
	}
	
	public AnswerDto convertAnswerData(Answer newAnswer, Long questionId) {
		AnswerDto answerDto = new AnswerDto();
		answerDto.setId(newAnswer.getId());
		answerDto.setAnswerDescription(newAnswer.getAnswerDescription());
		answerDto.setCreationDate(newAnswer.getCreationDate());
		answerDto.setOwnerName(newAnswer.getOwnerName());
		answerDto.setQuestionId(questionId);
		return answerDto;
	}
}
