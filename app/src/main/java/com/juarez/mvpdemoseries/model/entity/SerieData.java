package com.juarez.mvpdemoseries.model.entity;

import com.google.gson.annotations.SerializedName;
import com.juarez.mvpdemoseries.model.entity.Serie;

import java.util.List;

public class SerieData {
    @SerializedName("data")
    private List<Serie> data;

    public List<Serie> getSeries() {
        return data;
    }
}
