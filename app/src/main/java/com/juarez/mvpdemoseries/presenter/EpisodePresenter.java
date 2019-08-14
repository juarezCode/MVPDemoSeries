package com.juarez.mvpdemoseries.presenter;

import com.juarez.mvpdemoseries.interfaces.IEpisode;
import com.juarez.mvpdemoseries.model.EpisodeInteractor;
import com.juarez.mvpdemoseries.model.entity.Episode;

import java.util.ArrayList;

public class EpisodePresenter implements IEpisode.IPresenter {
    private IEpisode.IView view;
    private IEpisode.IModel interactor;

    public EpisodePresenter(IEpisode.IView view){
        this.view = view;
        interactor = new EpisodeInteractor(this);
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
    public void getEpisodes(int id, int numberSeason, String token) {
        interactor.getEpisodes(id, numberSeason, token);
    }

    @Override
    public void showEpisodes(ArrayList<Episode> listEpisodes) {
        view.showEpisodes(listEpisodes);
    }
}
