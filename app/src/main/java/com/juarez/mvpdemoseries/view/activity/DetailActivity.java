package com.juarez.mvpdemoseries.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.juarez.mvpdemoseries.R;
import com.juarez.mvpdemoseries.interfaces.ISerieDetail;
import com.juarez.mvpdemoseries.model.entity.Actor;
import com.juarez.mvpdemoseries.model.entity.Episode;
import com.juarez.mvpdemoseries.model.entity.Serie;
import com.juarez.mvpdemoseries.model.entity.SerieDetail1;
import com.juarez.mvpdemoseries.model.entity.SerieDetail2;
import com.juarez.mvpdemoseries.presenter.SerieDetailPresenter;
import com.juarez.mvpdemoseries.util.Constants;
import com.juarez.mvpdemoseries.view.fragment.SerieActorFragment;
import com.juarez.mvpdemoseries.view.fragment.SerieDetailFragment;
import com.juarez.mvpdemoseries.view.fragment.SerieEpisodeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements ISerieDetail.IView {
    private ISerieDetail.IPresenter presenter;


    private String token;
    @BindView(R.id.detailSeriesName)
    TextView detailSeriesName;
    private BottomNavigationView bottomNavigation;
    @BindView(R.id.tootlbar_arrow)
    Button back;
    @BindView(R.id.tootlbar_image)
    Button melon;
    @BindView(R.id.tootlbar_image_rigth)
    Button rigth;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private static final String TAG = "DetailActivity";

    //vaiables publicas y estaticas
    private int totalSeasons;
    private int idSerie;
    private SerieDetail1 serieDetail1;
    private SerieDetail2 serieDetail2;
    private List<Episode> listEpisodes;
    private List<Actor> listActors;


    public int getId() {
        return idSerie;
    }

    public int getTotalSeasons() {
        return totalSeasons;
    }

    public SerieDetail1 getSerieDetail1() {
        return serieDetail1;
    }

    public SerieDetail2 getSerieDetail2() {
        return serieDetail2;
    }

    public List<Episode> getListEpisodes() {
        return listEpisodes;
    }

public List<Actor> getListActors() {
        return listActors;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        presenter = new SerieDetailPresenter(this);
        melon.setVisibility(View.GONE);
        back.setVisibility(View.VISIBLE);
        rigth.setVisibility(View.VISIBLE);

        //recibir  datos
        Serie serie = (Serie) getIntent().getExtras().getSerializable("serie");
        String dataSeriesName = serie.getSeriesName();
        idSerie = serie.getId();
        Log.e(TAG, "id de la serie: " + idSerie);

        //show serie name in the activity
        detailSeriesName.setText(dataSeriesName);

        //BottomNavigationView onclick
        bottomNavigation = findViewById(R.id.bottomnavigationview);
        fragmentManager = getSupportFragmentManager();
        bottomNavigation.setOnNavigationItemSelectedListener(item -> {

            int id = item.getItemId();
            switch (id) {
                case R.id.seriesDetailItem:
                    fragment = new SerieDetailFragment();
                    break;
                case R.id.seriesEpisodeItem:
                    fragment = new SerieEpisodeFragment();
                    break;
                case R.id.seriesActorItem:
                    fragment = new SerieActorFragment();
                    break;
                default:
                    fragment = new SerieDetailFragment();
                    break;
            }
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container, fragment).commit();
            return true;
        });
        //cancelar servicio
        back.setOnClickListener(v -> presenter.cancelService());

        loadToken();
        getSerieDetail1(token, idSerie);
        getActors(idSerie, token);
    }

    @Override
    public void getSerieDetail1(String token, int id) {
        presenter.getSerieDetail1(token, id);
    }

    @Override
    public void showDetail1(SerieDetail1 serieDetail1, String imdbId) {
        if (serieDetail1 != null) {
            this.serieDetail1 = serieDetail1;

            //si el imdbId es vacio (no hay imdbID) aqui se termina los servicios
            if (!imdbId.isEmpty())
                getSerieDetail2(token, imdbId);
        }
    }

    @Override
    public void getSerieDetail2(String token, String imdbId) {
        presenter.getSerieDetail2(token, imdbId);
    }

    @Override
    public void showDetail2(SerieDetail2 serie, int totalSeasons) {
        serieDetail2 = serie;
        this.totalSeasons = totalSeasons;

        getEpisodes(idSerie, 1, token);
    }

    @Override
    public void getEpisodes(int id, int numberSeason, String token) {
        presenter.getEpisodes(id, numberSeason, token);
    }

    @Override
    public void showEpisodes(ArrayList<Episode> listEpisodes) {
        firstFragment();
        bottomNavigation.setVisibility(View.VISIBLE);
        this.listEpisodes = listEpisodes;
    }

    @Override
    public void getActors(int id, String token) {
        presenter.getActors(id, token);
    }

    @Override
    public void showActors(ArrayList<Actor> listActors) {
        this.listActors = listActors;
    }

    @Override
    public void finishSerieActivity() {
        finish();
    }

    @Override
    public void showErrorNotFound(String notFound) {
        Log.e(TAG, notFound);
    }

    @Override
    public void showErrorNotAuthorized(String notAuthorized) {
        Toast.makeText(this, notAuthorized, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorApi(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorImdbId(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        serieDetail2 = new SerieDetail2();
        listEpisodes = new ArrayList<>();
        totalSeasons = 0;
        firstFragment();
        bottomNavigation.setVisibility(View.VISIBLE);
    }

    //primer fragmento que se cargara
    private void firstFragment() {
        fragmentManager = getSupportFragmentManager();
        fragment = new SerieDetailFragment();//default
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment).commit();
    }

    //carga el token guardado, para usarlo con los servicios
    private void loadToken() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFS, MODE_PRIVATE);
        token = sharedPreferences.getString(Constants.TOKEN, "");
        Log.e(TAG, "loadtoken: " + token);

    }

    @Override
    public void onBackPressed() {
        presenter.cancelService();
    }

}
