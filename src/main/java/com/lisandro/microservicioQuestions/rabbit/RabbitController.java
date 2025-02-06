package com.lisandro.microservicioQuestions.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitController {
	@Autowired
    ConsumeArticleValidation consumeArticleValidation;
	
	public void init() {
		consumeArticleValidation.init();
	}
}
