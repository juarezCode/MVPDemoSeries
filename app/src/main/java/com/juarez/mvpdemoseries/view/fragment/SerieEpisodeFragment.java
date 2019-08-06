package com.juarez.mvpdemoseries.view.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.juarez.mvpdemoseries.R;
import com.juarez.mvpdemoseries.adapter.EpisodeAdapter;
import com.juarez.mvpdemoseries.api.ServiceClient;
import com.juarez.mvpdemoseries.interfaces.IEpisode;
import com.juarez.mvpdemoseries.model.entity.Episode;
import com.juarez.mvpdemoseries.model.entity.SerieSeasonData;
import com.juarez.mvpdemoseries.pagination.AdapterTemporada;
import com.juarez.mvpdemoseries.pagination.RecyclerViewItemClickListener;
import com.juarez.mvpdemoseries.pagination.Temporada;
import com.juarez.mvpdemoseries.presenter.EpisodePresenter;
import com.juarez.mvpdemoseries.view.activity.DetailActivity;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SerieEpisodeFragment extends Fragment implements IEpisode.view {
    private IEpisode.presenter presenter;
    private String token;
    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String TOKEN = "token";
    private String TAG = "SerieEpisodeFragment";

    private EpisodeAdapter mAdapter;
    private AdapterTemporada adapterTemporada;
    private ArrayList<Episode> listEpisodes;
    private ArrayList<Temporada> listaTemporada;
    @BindView(R.id.recyclerChapter)
    RecyclerView recyclerChapter;
    @BindView(R.id.recyclerTemporada)
    RecyclerView recyclerTemporada;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    //@BindView(R.id.txtNumerSeason)
    TextView numberTemporada;
    private int serieId;
    int totalSeasons;


    public SerieEpisodeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_serie_episode, container, false);
        ButterKnife.bind(this, view);
        presenter = new EpisodePresenter(this);
        //datos necesarios id de la serie y total de temporadas

        serieId = DetailActivity.idSerie;
        totalSeasons = DetailActivity.totalSeasons;
        Log.e(TAG, "id que necesito: " + serieId);
        Log.e(TAG, "total de temporadas: " + totalSeasons);
        numberTemporada = view.findViewById(R.id.txtNumerSeason);

        //recycler Episodes
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerChapter.setLayoutManager(linearLayoutManager);

        //validacion, si la serie seleccionada tiene temporadas
        if (totalSeasons <= 0) {
            listEpisodes = new ArrayList<>();//crear ArrayList Vacio
            listEpisodes.clear();
            Toast.makeText(getContext(), "No se encontraron temporadas", Toast.LENGTH_SHORT).show();
        } else {

            listEpisodes = DetailActivity.listEpisodes;
            Log.e(TAG, String.valueOf(listEpisodes));
            if (listEpisodes.size() <= 0 || listEpisodes == null) {
                Toast.makeText(getContext(), "no se encotraron temporadas", Toast.LENGTH_SHORT).show();
            } else {
                mAdapter = new EpisodeAdapter(getContext(), listEpisodes);
                recyclerChapter.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

        }

        //recycler temporada
        LinearLayoutManager temporada = new LinearLayoutManager(getContext());
        temporada.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerTemporada.setLayoutManager(temporada);

        //crea arreglo de temporadas
        listaTemporada = new ArrayList<Temporada>();

        for (int i = 1; i <= totalSeasons; i++) {
            Log.e(TAG, "episodio: " + i);
            listaTemporada.add(new Temporada(i));
        }
        adapterTemporada = new AdapterTemporada(getContext(), listaTemporada);
        recyclerTemporada.setAdapter(adapterTemporada);

        //onClick  de temporadas, llama al servicio de busqueda de episodios
        adapterTemporada.setOnItemClickListener((view1, position) -> {

            listEpisodes = new ArrayList<>();//crear ArrayList Vacio
            listEpisodes.clear();//limpio el Arralist
            loadToken();
            getEpisodes(serieId, position + 1, token);

        });

        return view;
    }


    public void loadToken() {
        SharedPreferences sharedPreferences = this.getContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        token = sharedPreferences.getString(TOKEN, "");
        Log.e(TAG, "loadtoken: " + token);

    }

    @Override
    public void getEpisodes(int id, int numberSeason, String token) {
        progressBar.setVisibility(View.VISIBLE);
        presenter.getEpisodes(id, numberSeason, token);
    }

    @Override
    public void showEpisodes(ArrayList<Episode> listEpisodes) {
        progressBar.setVisibility(View.GONE);

        mAdapter = new EpisodeAdapter(getContext(), listEpisodes);
        recyclerChapter.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}

