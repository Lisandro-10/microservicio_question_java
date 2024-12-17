package com.lisandro.microservicioQuestions.security;

import com.google.gson.annotations.SerializedName;
import com.lisandro.microservicioQuestions.utils.Builder;

public class User {

	   @SerializedName("id")
	    public String id;
	    @SerializedName("name")
	    public String name;
	    @SerializedName("login")
	    public String login;
	    @SerializedName("permissions")
	    public String[] permissions;

	    public static User fromJson(String json) {
	        return Builder.gson().fromJson(json, User.class);
	    }

}
