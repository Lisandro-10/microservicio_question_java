package com.lisandro.microservicioQuestions.security;

import com.google.gson.annotations.SerializedName;
import com.lisandro.microservicioQuestions.utils.gson.GsonTools;


public class User {
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("login")
    public String login;
    @SerializedName("permissions")
    public String[] permissions;
    
    

    public String[] getPermissions() {
		return permissions;
	}



	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	}



	public static User fromJson(String json) {
        return GsonTools.gson().fromJson(json, User.class);
    }
}