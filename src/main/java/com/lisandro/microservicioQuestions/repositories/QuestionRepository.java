package com.lisandro.microservicioQuestions.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lisandro.microservicioQuestions.models.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

	List<Optional<Question>> findByArticleId(String articleId);

}
