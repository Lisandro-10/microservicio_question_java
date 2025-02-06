package com.lisandro.microservicioQuestions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lisandro.microservicioQuestions.rabbit.RabbitController;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class MicroservicioQuestionsApplication {
	
	@Autowired
    RabbitController rabbitController;
	
	public static void main(String[] args) {
		SpringApplication.run(MicroservicioQuestionsApplication.class, args);
	}
	
	@PostConstruct
    void init() {
        rabbitController.init();
    }

}
