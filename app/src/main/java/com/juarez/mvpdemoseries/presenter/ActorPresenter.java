package com.juarez.mvpdemoseries.presenter;

import com.juarez.mvpdemoseries.interfaces.IActor;
import com.juarez.mvpdemoseries.model.ActorInteractor;
import com.juarez.mvpdemoseries.model.entity.Actor;

import java.util.List;

public class ActorPresenter implements IActor.IPresenter {

    private IActor.IView view;
    private IActor.IModel interactor;

    public ActorPresenter(IActor.IView view) {
        this.view = view;
        interactor = new ActorInteractor(this);
    }

    @Override
    public void getActors(List<Actor> listActors) {
        interactor.getActors(listActors);
    }

    @Override
    public void showErrorNotFound(String error) {
        view.showErrorNotFound(error);
    }

    @Override
    public void showActors(List<Actor> listActors) {
        view.showActors(listActors);
    }
}
