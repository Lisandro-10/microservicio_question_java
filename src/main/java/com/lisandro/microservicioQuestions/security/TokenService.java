package com.lisandro.microservicioQuestions.security;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.lisandro.microservicioQuestions.utils.ExpiringMap;


@Service
public class TokenService {
	final ExpiringMap<String, User> map = new ExpiringMap<>(60 * 60, 60 * 5);
	
	@Autowired
	Environment env;

    public void validateAdmin(String token) {
        validate(token);
        User cachedUser = map.get(token);
        if (cachedUser == null) {
//            throw new SimpleError(401, "Unauthorized");
        }
//        if (!contains(cachedUser.permissions, "admin")) {
//            throw new SimpleError(401, "Unauthorized");
//        }
    }
    
    public void validate(String token) {
        if (token.isEmpty()) {
//            throw new SimpleError(401, "Unauthorized");
        }

        User cachedUser = map.get(token);
        if (cachedUser != null) {
            return;
        }

        User user = retrieveUser(token);
        if (user == null) {
//            throw new SimpleError(401, "Unauthorized");
        }
        map.put(token, user);
    }
    
    private User retrieveUser(String token) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(env.getProperty("security.securityServerUrl") + "/v1/users/current");
        request.addHeader("Authorization", token);
        HttpResponse response;
        try {
            response = client.execute(request);

            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }

            HttpEntity responseEntity = response.getEntity();
            if (responseEntity == null) {
                return null;
            }
            String body = EntityUtils.toString(responseEntity);
            return User.fromJson(body);
        } catch (Exception e) {
            return null;
        }
    }
    
    // Devuelve un usuario logueado
    public User getUser(String token){
        if (token.isEmpty()) {
//            throw new SimpleError(401, "Unauthorized");
        }

        User cachedUser = map.get(token);
        if (cachedUser != null) {
            return cachedUser;
        }

        User user = retrieveUser(token);
        if (user == null) {
//            throw new SimpleError(401, "Unauthorized");
        }
        map.put(token, user);
        return user;
    }
}
