package com.juarez.mvpdemoseries.model.entity;

import com.juarez.mvpdemoseries.model.entity.Actor;

import java.util.List;

public class SerieActorData {

    List<Actor> data;

    public List<Actor> getActors() {
        return data;
    }

    public void setActors(List<Actor> data) {
        this.data = data;
    }
}
