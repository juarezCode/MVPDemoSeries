package com.juarez.mvpdemoseries.interfaces;

import com.juarez.mvpdemoseries.model.entity.Serie;

import java.util.ArrayList;

public interface ISerie {
    interface IRepository {
        void cleanAdapter();

        void getSeriesApi(String token, String search);
    }

    interface IInteractor {
        void cleanAdapter();

        void getSeries(String token, String search);
    }

    interface IPresenter {
        void getSeries(String token, String search);

        void cleanAdapter();

        void showErrorApi(String error);

        void showErrorNotFound(String notFound);

        void showErrorNotAuthorized(String notAuthorized);

        void showSeries(ArrayList<Serie> listSeries);
    }

    interface IView {
        void showErrorNotFound(String notFound);

        void showErrorNotAuthorized(String notAuthorized);

        void showErrorApi(String error);

        void showSeries(ArrayList<Serie> listSeries);
    }
}
