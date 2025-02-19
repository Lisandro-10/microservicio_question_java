package com.lisandro.microservicioQuestions.security;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisandro.microservicioQuestions.server.EnvironmentVars;

@Service
public class TokenDao {

    @Autowired
    EnvironmentVars environmentVars;

    public User retrieveUser(String token) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(environmentVars.envData.securityServerUrl + "/users/current");
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
}
