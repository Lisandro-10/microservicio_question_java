package com.lisandro.microservicioQuestions.rabbit;

@FunctionalInterface
public interface EventProcessor {
	void process(RabbitEvent event);
}
