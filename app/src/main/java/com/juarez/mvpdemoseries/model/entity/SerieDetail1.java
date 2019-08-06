package com.juarez.mvpdemoseries.model.entity;

public class SerieDetail1 {

    private String firstAired;
    private String overview;
    private String airsDayOfWeek;
    private String airsTime;
    private String imdbId;

    public SerieDetail1(String firstAired, String overview, String airsDayOfWeek, String airsTime, String imdbId) {
        this.firstAired = firstAired;
        this.overview = overview;
        this.airsDayOfWeek = airsDayOfWeek;
        this.airsTime = airsTime;
        this.imdbId = imdbId;
    }

    public SerieDetail1() {
    }

    public String getFirstAired() {
        return firstAired;
    }

    public String getOverview() {
        return overview;
    }

    public String getAirsDayOfWeek() {
        return airsDayOfWeek;
    }

    public String getAirsTime() {
        return airsTime;
    }

    public String getImdbId() {
        return imdbId;
    }
}
