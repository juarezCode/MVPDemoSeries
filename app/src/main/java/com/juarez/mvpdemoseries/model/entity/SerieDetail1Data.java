package com.juarez.mvpdemoseries.model.entity;

import com.google.gson.annotations.SerializedName;

public class SerieDetail1Data {
    @SerializedName("data")
    private SerieDetail1 data;

    public SerieDetail1 getSerie() {
        return data;
    }
}
