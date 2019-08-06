package com.juarez.mvpdemoseries.interfaces;

import com.juarez.mvpdemoseries.model.entity.Actor;

import java.util.ArrayList;

public interface IActor {

    interface model {
        void getActors();
    }

    interface presenter {
        void getActors();

        void showErrorNotFound(String error);

        void showActors(ArrayList<Actor> listActors);
    }

    interface view {
        void getActors();

        void showErrorNotFound(String error);

        void showActors(ArrayList<Actor> listActors);
    }

}
