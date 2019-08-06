package com.juarez.mvpdemoseries.presenter;

import com.juarez.mvpdemoseries.interfaces.IActor;
import com.juarez.mvpdemoseries.model.ActorInteractor;
import com.juarez.mvpdemoseries.model.entity.Actor;

import java.util.ArrayList;

public class ActorPresenter implements IActor.presenter {

    private IActor.view view;
    private IActor.model interactor;

    public ActorPresenter(IActor.view view) {
        this.view = view;
        interactor = new ActorInteractor(this);
    }

    @Override
    public void getActors() {
        interactor.getActors();
    }

    @Override
    public void showErrorNotFound(String error) {
        view.showErrorNotFound(error);
    }

    @Override
    public void showActors(ArrayList<Actor> listActors) {
        view.showActors(listActors);
    }
}
