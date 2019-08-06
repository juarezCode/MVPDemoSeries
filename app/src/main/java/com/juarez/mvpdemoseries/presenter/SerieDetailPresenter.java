package com.juarez.mvpdemoseries.presenter;

import com.juarez.mvpdemoseries.interfaces.IActor;
import com.juarez.mvpdemoseries.interfaces.ISerieDetail;
import com.juarez.mvpdemoseries.model.SerieDetailInteractor;
import com.juarez.mvpdemoseries.model.entity.Actor;
import com.juarez.mvpdemoseries.model.entity.Episode;
import com.juarez.mvpdemoseries.model.entity.SerieDetail1;
import com.juarez.mvpdemoseries.model.entity.SerieDetail2;

import java.util.ArrayList;

public class SerieDetailPresenter implements ISerieDetail.presenter {

    private ISerieDetail.view view;
    private ISerieDetail.model interactor;

    public SerieDetailPresenter(ISerieDetail.view view) {
        this.view = view;
        interactor = new SerieDetailInteractor(this);
    }

    @Override
    public void getSerieDetail1(String token, int id) {
        interactor.getSerieDetail1(token, id);
    }

    @Override
    public void getSerieDetail2(String token, String imdbId) {
        interactor.getSerieDetail2(token, imdbId);
    }

    @Override
    public void getEpisodes(int id, int numberSeason, String token) {
        interactor.getEpisodes(id, numberSeason, token);
    }

    @Override
    public void showDetail1(SerieDetail1 serieDetail1, String imdbId) {
        view.showDetail1(serieDetail1, imdbId);
    }

    @Override
    public void showDetail2(SerieDetail2 serie, int totalSeasons) {
        view.showDetail2(serie, totalSeasons);

    }

    @Override
    public void showEpisodes(ArrayList<Episode> listEpisodes) {
        view.showEpisodes(listEpisodes);
    }

    @Override
    public void getActors(int id, String token) {
        interactor.getActors(id, token);
    }

    @Override
    public void showActors(ArrayList<Actor> listActors) {
        view.showActors(listActors);
    }

    @Override
    public void cancelService() {
        interactor.cancelService();
    }

    @Override
    public void showErrorNotFound(String notFound) {
        view.showErrorNotFound(notFound);
    }

    @Override
    public void showErrorNotAuthorized(String notAuthorized) {
        view.showErrorNotAuthorized(notAuthorized);
    }

    @Override
    public void showErrorApi(String error) {
        view.showErrorApi(error);
    }

    @Override
    public void showErrorImdbId(String error) {
        view.showErrorImdbId(error);
    }

    @Override
    public void finishSerieActivity() {
        view.finishSerieActivity();
    }

}
