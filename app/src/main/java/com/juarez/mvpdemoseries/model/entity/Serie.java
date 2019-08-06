package com.juarez.mvpdemoseries.model.entity;

import java.io.Serializable;

public class Serie implements Serializable {
    private String banner;
    private int id;
    private String seriesName;

    public Serie(String banner, int id, String seriesName) {
        this.banner = banner;
        this.id = id;
        this.seriesName = seriesName;
    }

    public Serie() {
    }

    public String getBanner() {
        return banner;
    }

    public int getId() {
        return id;
    }

    public String getSeriesName() {
        return seriesName;
    }
}
