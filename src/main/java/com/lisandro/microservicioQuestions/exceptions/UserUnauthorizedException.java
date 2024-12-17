package com.lisandro.microservicioQuestions.exceptions;

public class UserUnauthorizedException extends Error{
    public UserUnauthorizedException(String message) {
        super(message);
    }
}
