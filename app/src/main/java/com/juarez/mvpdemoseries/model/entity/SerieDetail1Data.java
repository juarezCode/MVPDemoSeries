package com.juarez.mvpdemoseries.model.entity;

import com.google.gson.annotations.SerializedName;
import com.juarez.mvpdemoseries.model.entity.SerieDetail1;

public class SerieDetail1Data {
    @SerializedName("data")
    private SerieDetail1 data;

    public SerieDetail1 getSerie() {
        return data;
    }
}
