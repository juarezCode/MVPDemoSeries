package com.juarez.mvpdemoseries.interfaces;

import android.view.View;

import com.juarez.mvpdemoseries.model.entity.Actor;
import com.juarez.mvpdemoseries.model.entity.Episode;
import com.juarez.mvpdemoseries.model.entity.SerieDetail1;
import com.juarez.mvpdemoseries.model.entity.SerieDetail2;

import java.util.ArrayList;
import java.util.List;

public interface ISerieDetail {

    interface model {
        //service 1 get serie details 1
        void cancelService();

        void getSerieDetail1(String token, int id);

        //service 2 get serie details 2
        void getSerieDetail2(String token, String imdbId);

        //service 3, get episodes per season
        void getEpisodes(int id, int numberSeason, String token);

        //service 4, get actors
        void getActors(int id, String token);
    }

    interface presenter {
        //service get serie details 1
        void getSerieDetail1(String token, int id);

        void cancelService();

        void showErrorNotFound(String notFound);

        void showErrorNotAuthorized(String notAuthorized);

        void showErrorApi(String error);

        void showErrorImdbId(String error);

        void showDetail1(SerieDetail1 serieDetail1, String imdbId);

        void finishSerieActivity();

        //service 2 get serie details 2
        void getSerieDetail2(String token, String imdbId);

        void showDetail2(SerieDetail2 serie, int totalSeasons);

        //service 3, get episodes per season
        void getEpisodes(int id, int numberSeason, String token);

        void showEpisodes(ArrayList<Episode> listEpisodes);

        //service 4, get actors
        void getActors(int id, String token);

        void showActors(ArrayList<Actor> listActors);

    }

    interface view {

        //service 1 get serie details 1
        void getSerieDetail1(String token, int id);

        void showErrorNotFound(String notFound);

        void showErrorNotAuthorized(String notAuthorized);

        void showErrorApi(String error);

        void showErrorImdbId(String error);

        void showDetail1(SerieDetail1 serieDetail1, String imdbId);

        void finishSerieActivity();

        //service 2 get serie details 1
        void getSerieDetail2(String token, String imdbId);

        void showDetail2(SerieDetail2 serie, int totalSeasons);

        //service 3, get episodes per season
        void getEpisodes(int id, int numberSeason, String token);

        void showEpisodes(ArrayList<Episode> listEpisodes);

        //service 4, get actors
        void getActors(int id, String token);

        void showActors(ArrayList<Actor> listActors);

    }
}
