package com.juarez.mvpdemoseries.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.juarez.mvpdemoseries.R;
import com.juarez.mvpdemoseries.adapter.ActorAdapter;
import com.juarez.mvpdemoseries.interfaces.IActor;
import com.juarez.mvpdemoseries.model.entity.Actor;
import com.juarez.mvpdemoseries.presenter.ActorPresenter;
import com.juarez.mvpdemoseries.view.activity.DetailActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class SerieActorFragment extends Fragment implements IActor.view {
    private IActor.presenter presenter;
    @BindView(R.id.recyclerActor)
    RecyclerView recyclerActor;
    @BindView(R.id.progressBar4)
    ProgressBar progressBar;
    private TextView txtActorsNotFound;
    private ActorAdapter actorAdapter;
    private ArrayList<Actor> listActors;

    public SerieActorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_serie_actor, container, false);
        ButterKnife.bind(this, view);
        presenter = new ActorPresenter(this);

        //recyclerActor
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerActor.setLayoutManager(gridLayoutManager);

        getActors();

        return view;
    }

    @Override
    public void getActors() {
        presenter.getActors();
    }

    @Override
    public void showErrorNotFound(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showActors(ArrayList<Actor> listActors) {

        actorAdapter = new ActorAdapter(getContext(), listActors);
        recyclerActor.setAdapter(actorAdapter);
        actorAdapter.notifyDataSetChanged();
    }
}
