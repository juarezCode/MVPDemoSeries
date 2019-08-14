package com.juarez.mvpdemoseries.model;

import com.juarez.mvpdemoseries.interfaces.IActor;
import com.juarez.mvpdemoseries.model.entity.Actor;

import java.util.List;

public class ActorInteractor implements IActor.IModel {

    private IActor.IPresenter presenter;

    public ActorInteractor(IActor.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getActors(List<Actor> listActors) {
        List<Actor> listActor = listActors;

        if (listActor.isEmpty())
            presenter.showErrorNotFound("no se encontraron actores");
        else
            presenter.showActors(listActor);

    }
}
