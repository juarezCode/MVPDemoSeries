package com.juarez.mvpdemoseries.model.entity;

public class Episode {

    private String episodeName;
    private int airedEpisodeNumber;
    private String firstAired;
    private String overview;
    private String filename;
    private double siteRating;

    public Episode(String episodeName, int airedEpisodeNumber, String firstAired, String overview, String filename, double siteRating) {
        this.episodeName = episodeName;
        this.airedEpisodeNumber = airedEpisodeNumber;
        this.firstAired = firstAired;
        this.overview = overview;
        this.filename = filename;
        this.siteRating = siteRating;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public int getAiredEpisodeNumber() {
        return airedEpisodeNumber;
    }

    public String getFirstAired() {
        return firstAired;
    }

    public String getOverview() {
        return overview;
    }

    public String getFilename() {
        return filename;
    }

    public double getSiteRating() {
        return siteRating;
    }
}
