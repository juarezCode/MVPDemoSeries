package com.juarez.mvpdemoseries.api;


import com.juarez.mvpdemoseries.model.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ILoginService {

    //servicio buscar series, por nombre
    //https://api.thetvdb.com/search/series?name=x
    @POST("login")
    Call<User> getToken(@Body User user);
}
