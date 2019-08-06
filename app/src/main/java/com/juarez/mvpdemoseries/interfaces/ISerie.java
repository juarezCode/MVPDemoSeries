package com.juarez.mvpdemoseries.interfaces;

import com.juarez.mvpdemoseries.model.entity.Serie;

import java.util.ArrayList;

public interface ISerie {
    interface  repository{
        void cleanAdapter();

        void getSeriesApi(String token, String search);
    }

    interface interactor {
        void cleanAdapter();

        void getSeries(String token, String search);
    }

    interface presenter {
        void getSeries(String token, String search);

        void cleanAdapter();

        void showErrorApi(String error);

        void showErrorNotFound(String notFound);

        void showErrorNotAuthorized(String notAuthorized);

        void showSeries(ArrayList<Serie> listSeries);
    }

    interface view {
        void showErrorNotFound(String notFound);

        void showErrorNotAuthorized(String notAuthorized);

        void showErrorApi(String error);

        void showSeries(ArrayList<Serie> listSeries);
    }
}
