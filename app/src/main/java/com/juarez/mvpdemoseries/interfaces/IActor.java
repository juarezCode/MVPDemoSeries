package com.juarez.mvpdemoseries.interfaces;

import com.juarez.mvpdemoseries.model.entity.Actor;

import java.util.List;

public interface IActor {

    interface IModel {
        void getActors(List<Actor> listActors);
    }

    interface IPresenter {
        void getActors(List<Actor> listActors);

        void showErrorNotFound(String error);

        void showActors(List<Actor> listActors);
    }

    interface IView {
        void getActors(List<Actor> listActors);

        void showErrorNotFound(String error);

        void showActors(List<Actor> listActors);
    }

}
