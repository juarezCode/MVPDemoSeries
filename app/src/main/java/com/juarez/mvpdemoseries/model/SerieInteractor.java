package com.juarez.mvpdemoseries.model;

import com.juarez.mvpdemoseries.interfaces.ISerie;

public class SerieInteractor implements ISerie.interactor {
      private ISerie.presenter presenter;
    private ISerie.repository repository;

    public SerieInteractor(ISerie.presenter presenter) {
        this.presenter = presenter;
        repository = new SerieRepository(presenter);
    }

    @Override
    public void getSeries(String token, String search) {
        repository.getSeriesApi(token,search);
    }

    @Override
    public void cleanAdapter() {
        repository.cleanAdapter();
    }
}
