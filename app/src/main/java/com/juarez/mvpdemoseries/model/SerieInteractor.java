package com.juarez.mvpdemoseries.model;

import com.juarez.mvpdemoseries.interfaces.ISerie;

public class SerieInteractor implements ISerie.IInteractor {
    private ISerie.IRepository repository;

    public SerieInteractor(ISerie.IPresenter presenter) {
        repository = new SerieRepository(presenter);
    }

    @Override
    public void getSeries(String token, String search) {
        repository.getSeriesApi(token, search);
    }

    @Override
    public void cleanAdapter() {
        repository.cleanAdapter();
    }
}
