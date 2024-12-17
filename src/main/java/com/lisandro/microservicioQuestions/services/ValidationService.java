package com.lisandro.microservicioQuestions.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisandro.microservicioQuestions.security.TokenService;
import com.lisandro.microservicioQuestions.security.User;

@Service
public class ValidationService {
	
    @Autowired
    TokenService tokenService;

    public void validateUser(String authHeader){
//        if (authHeader == null) throw new UnauthorizedError();
        tokenService.validateAdmin(authHeader);
    }

    public User currentUser(String authHeader) {
        return tokenService.getUser(authHeader);
    }
}
