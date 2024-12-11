package com.lisandro.microservicioQuestions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lisandro.microservicioQuestions.models.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

}
