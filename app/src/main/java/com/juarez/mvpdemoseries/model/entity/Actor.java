package com.juarez.mvpdemoseries.model.entity;

public class Actor {
    private String name;
    private String role;
    private String image;

    public Actor(String name, String role, String image) {
        this.name = name;
        this.role = role;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getImage() {
        return image;
    }
}
