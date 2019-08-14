package com.juarez.mvpdemoseries.model.entity;

import com.google.gson.annotations.SerializedName;

public class SerieDetail2 {
    @SerializedName("Genre")
    private String genre;
    @SerializedName("Poster")
    private String poster;
    private String imdbRating;
    private String totalSeasons;

    public SerieDetail2(String genre, String poster, String imdbRating, String totalSeasons) {
        this.genre = genre;
        this.poster = poster;
        this.imdbRating = imdbRating;
        this.totalSeasons = totalSeasons;
    }
    public SerieDetail2(){

    }

    public String getGenre() {
        return genre;
    }

    public String getPoster() {
        return poster;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getTotalSeasons() {
        return totalSeasons;
    }
}
