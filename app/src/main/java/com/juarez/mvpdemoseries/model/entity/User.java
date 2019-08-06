package com.juarez.mvpdemoseries.model.entity;

public class User {
    private String apikey;
    private String userkey;
    private String username;
    private String token;

    public User(String apikey, String userkey, String username) {
        this.apikey = apikey;
        this.userkey = userkey;
        this.username = username;
    }

    public String getToken() {
        return token;
    }
}
