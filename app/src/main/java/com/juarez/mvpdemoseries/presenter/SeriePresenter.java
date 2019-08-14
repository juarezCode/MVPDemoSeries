package com.juarez.mvpdemoseries.presenter;

import com.juarez.mvpdemoseries.interfaces.ISerie;
import com.juarez.mvpdemoseries.model.SerieInteractor;
import com.juarez.mvpdemoseries.model.entity.Serie;

import java.util.ArrayList;

public class SeriePresenter implements ISerie.IPresenter {
    private ISerie.IView view;
    private ISerie.IInteractor interactor;

    public SeriePresenter(ISerie.IView view) {
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
