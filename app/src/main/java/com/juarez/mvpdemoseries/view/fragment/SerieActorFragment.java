package com.juarez.mvpdemoseries.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.juarez.mvpdemoseries.R;
import com.juarez.mvpdemoseries.adapter.ActorAdapter;
import com.juarez.mvpdemoseries.interfaces.IActor;
import com.juarez.mvpdemoseries.model.entity.Actor;
import com.juarez.mvpdemoseries.presenter.ActorPresenter;
import com.juarez.mvpdemoseries.view.activity.DetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class SerieActorFragment extends Fragment implements IActor.IView {
    private IActor.IPresenter presenter;
    @BindView(R.id.recyclerActor)
    RecyclerView recyclerActor;
    @BindView(R.id.progressBar4)
    ProgressBar progressBar;

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
        DetailActivity activity = (DetailActivity) getActivity();
        List<Actor> listActors = activity.getListActors();



        //recyclerActor
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerActor.setLayoutManager(gridLayoutManager);

        getActors(listActors);

        return view;
    }

    @Override
    public void getActors(List<Actor> listActors) {
        presenter.getActors(listActors);
    }

    @Override
    public void showErrorNotFound(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showActors(List<Actor> listActors) {

        ActorAdapter actorAdapter = new ActorAdapter(listActors);
        recyclerActor.setAdapter(actorAdapter);
        actorAdapter.notifyDataSetChanged();
    }
}
