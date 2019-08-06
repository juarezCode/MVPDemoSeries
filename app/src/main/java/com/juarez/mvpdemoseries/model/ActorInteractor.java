package com.juarez.mvpdemoseries.model;

import android.widget.Toast;

import com.juarez.mvpdemoseries.interfaces.IActor;
import com.juarez.mvpdemoseries.model.entity.Actor;
import com.juarez.mvpdemoseries.view.activity.DetailActivity;

import java.util.ArrayList;

public class ActorInteractor implements IActor.model {

    private IActor.presenter presenter;
    private ArrayList<Actor> listActors;

    public ActorInteractor(IActor.presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getActors() {
        listActors = DetailActivity.listActors;

        if (listActors.size() == 0)
            presenter.showErrorNotFound("no se encontraron actores");
        else
            presenter.showActors(listActors);

    }
}
