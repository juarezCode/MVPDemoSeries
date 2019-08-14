package com.juarez.mvpdemoseries.model.entity;

public class User {
    private String apikey;
    private String userkey;
    private String username;
    private String token;

    public User() {
        //empty contructor
    }

    public String getToken() {
        return token;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApikey() {
        return apikey;
    }

    public String getUserkey() {
        return userkey;
    }

    public String getUsername() {
        return username;
    }
}
