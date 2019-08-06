package com.juarez.mvpdemoseries.presenter;

import com.juarez.mvpdemoseries.interfaces.IEpisode;
import com.juarez.mvpdemoseries.model.EpisodeInteractor;
import com.juarez.mvpdemoseries.model.entity.Episode;

import java.util.ArrayList;

public class EpisodePresenter implements IEpisode.presenter {
    private IEpisode.view view;
    private IEpisode.model interactor;

    public EpisodePresenter(IEpisode.view view){
        this.view = view;
        interactor = new EpisodeInteractor(this);
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
