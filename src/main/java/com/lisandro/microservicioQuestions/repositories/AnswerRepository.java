package com.lisandro.microservicioQuestions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lisandro.microservicioQuestions.models.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

	
}
