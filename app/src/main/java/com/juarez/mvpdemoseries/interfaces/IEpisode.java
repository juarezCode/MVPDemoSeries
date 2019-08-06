package com.juarez.mvpdemoseries.interfaces;

import com.juarez.mvpdemoseries.model.entity.Episode;

import java.util.ArrayList;

public interface IEpisode {

    interface model {
        void getEpisodes(int id, int numberSeason, String token);
    }

    interface presenter {
        void getEpisodes(int id, int numberSeason, String token);

        void showEpisodes(ArrayList<Episode> listEpisodes);

    }

    interface view {
        void getEpisodes(int id, int numberSeason, String token);

        void showEpisodes(ArrayList<Episode> listEpisodes);
    }
}
