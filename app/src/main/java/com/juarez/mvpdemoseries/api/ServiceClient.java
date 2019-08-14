package com.juarez.mvpdemoseries.api;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceClient {

    private ServiceClient() {
    }

    public static ILoginService createLoginService() {
        return getSerieClient().create(ILoginService.class);
    }

    public static ISerieService createSerieService() {
        return getSerieClient().create(ISerieService.class);
    }

    public static ISerieService createSerieService2() {
        return getSerieClient2().create(ISerieService.class);
    }


    private static final String BASE_URL = "https://api.thetvdb.com/";
    private static final String BASE_URL_OMDB = "https://omdbapi.com";


    public static Retrofit getSerieClient() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkHttp())
                .build();
    }

    public static Retrofit getSerieClient2() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL_OMDB)
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkHttp())
                .build();
    }


    private static Interceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        return logInterceptor;
    }

    private static OkHttpClient provideOkHttp() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(provideLoggingInterceptor())
                .build();
    }
}
