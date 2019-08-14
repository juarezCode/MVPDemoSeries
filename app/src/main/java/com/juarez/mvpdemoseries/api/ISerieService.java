package com.juarez.mvpdemoseries.api;

import com.juarez.mvpdemoseries.model.entity.SerieActorData;
import com.juarez.mvpdemoseries.model.entity.SerieData;
import com.juarez.mvpdemoseries.model.entity.SerieDetail1Data;
import com.juarez.mvpdemoseries.model.entity.SerieDetail2;
import com.juarez.mvpdemoseries.model.entity.SerieSeasonData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ISerieService {

    //servicio buscar series, por nombre
    //https://api.thetvdb.com/search/series?name=game
    @GET("search/series")
    Call<SerieData> getSeries(@Query("name") String searchValue, @Header("Authorization") String authToken);

    //servicio obtener una serie, por su id
    //https://api.thetvdb.com/series/121361
    @GET("series/{id}")
    Call<SerieDetail1Data> getDetailSerie(@Path("id") int idSerie, @Header("Authorization") String authToken);

    //servicio obtener una serie, por su imdbID
    //https://omdbapi.com/?i=tt0944947&apikey=2f1f55d7&plot=full
    @GET("/")
    Call<SerieDetail2> getDetailSerie2(@Query("i") String id, @Query("apikey") String apiKey, @Query("plot") String plot, @Header("Authorization") String authToken);

    //https://{{endpoint}}/series/:id/episodes/query?airedSeason=1
    @GET("series/{id}/episodes/query")
    Call<SerieSeasonData> getSeason(@Path("id") int id, @Query("airedSeason") String season, @Header("Authorization") String authToken);

    //servicio que obtiene los actores por serie
    //https://{{endpoint}}/series/:id/actors
    @GET("series/{id}/actors")
    Call<SerieActorData> getActors(@Path("id") int idSerie, @Header("Authorization") String authToken);


}
