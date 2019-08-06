package com.juarez.mvpdemoseries.model.entity;

public class SerieDetail2 {
    private String Genre;
    private String Poster;
    private String imdbRating;
    private String totalSeasons;

    public SerieDetail2(String genre, String poster, String imdbRating, String totalSeasons) {
        Genre = genre;
        Poster = poster;
        this.imdbRating = imdbRating;
        this.totalSeasons = totalSeasons;
    }
    public SerieDetail2(){

    }

    public String getGenre() {
        return Genre;
    }

    public String getPoster() {
        return Poster;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getTotalSeasons() {
        return totalSeasons;
    }
}
