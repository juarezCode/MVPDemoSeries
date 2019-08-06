package com.juarez.mvpdemoseries.presenter;

import com.juarez.mvpdemoseries.interfaces.ISerie;
import com.juarez.mvpdemoseries.model.entity.Serie;
import com.juarez.mvpdemoseries.model.SerieInteractor;

import java.util.ArrayList;

public class SeriePresenter implements ISerie.presenter {
    private ISerie.view view;
    private ISerie.interactor interactor;

    public SeriePresenter(ISerie.view view) {
        this.view = view;
        interactor = new SerieInteractor(this);
    }

    @Override
    public void getSeries(String token, String search) {
        interactor.getSeries(token, search);
    }

    @Override
    public void cleanAdapter() {
        interactor.cleanAdapter();
    }

    @Override
    public void showErrorApi(String error) {
        view.showErrorApi(error);
    }

    @Override
    public void showErrorNotAuthorized(String notAuthorized) {
        view.showErrorNotAuthorized(notAuthorized);
    }

    @Override
    public void showErrorNotFound(String notFound) {
        view.showErrorNotFound(notFound);
    }

    @Override
    public void showSeries(ArrayList<Serie> listSeries) {
        view.showSeries(listSeries);
    }
}
