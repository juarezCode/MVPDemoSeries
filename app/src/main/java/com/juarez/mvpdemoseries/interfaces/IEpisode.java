package com.juarez.mvpdemoseries.interfaces;

import com.juarez.mvpdemoseries.model.entity.Episode;

import java.util.ArrayList;

public interface IEpisode {

    interface IModel {
        void getEpisodes(int id, int numberSeason, String token);
    }

    interface IPresenter {
        void showErrorNotFound(String notFound);

        void showErrorNotAuthorized(String notAuthorized);

        void showErrorApi(String error);

        void getEpisodes(int id, int numberSeason, String token);

        void showEpisodes(ArrayList<Episode> listEpisodes);

    }

    interface IView {
        void showErrorNotFound(String notFound);

        void showErrorNotAuthorized(String notAuthorized);

        void showErrorApi(String error);

        void getEpisodes(int id, int numberSeason, String token);

        void showEpisodes(ArrayList<Episode> listEpisodes);
    }
}
