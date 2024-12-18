package com.lisandro.microservicioQuestions.server;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

@Service
@Validated
public class ValidatorService {
    public void validate(@Valid Object data) {
    }
}
