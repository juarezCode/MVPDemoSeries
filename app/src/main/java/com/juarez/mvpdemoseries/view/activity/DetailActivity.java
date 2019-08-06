package com.juarez.mvpdemoseries.view.activity;

import android.content.SharedPreferences;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.juarez.mvpdemoseries.R;
import com.juarez.mvpdemoseries.interfaces.IActor;
import com.juarez.mvpdemoseries.interfaces.ISerieDetail;
import com.juarez.mvpdemoseries.model.entity.Actor;
import com.juarez.mvpdemoseries.model.entity.Episode;
import com.juarez.mvpdemoseries.model.entity.Serie;
import com.juarez.mvpdemoseries.model.entity.SerieDetail1;
import com.juarez.mvpdemoseries.model.entity.SerieDetail2;
import com.juarez.mvpdemoseries.presenter.SerieDetailPresenter;
import com.juarez.mvpdemoseries.view.fragment.SerieActorFragment;
import com.juarez.mvpdemoseries.view.fragment.SerieDetailFragment;
import com.juarez.mvpdemoseries.view.fragment.SerieEpisodeFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements ISerieDetail.view {
    private ISerieDetail.presenter presenter;

    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String TOKEN = "token";
    private static final String PLOT = "full";
    private final String apikey = "2f1f55d7";
    public static final String endpointSeason = "api.thetvdb.com";
    private String token;
    private String imdbId;
    public static int totalSeasons;
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
    private String TAG = "DetailActivity";
    public static String dataSeriesName;
    public static int idSerie;
    private Serie serie;
    public static SerieDetail1 serieDetail1;
    public static SerieDetail2 serieDetail2;
    public static ArrayList<Episode> listEpisodes;
    public static ArrayList<Actor> listActors;


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
        serie = (Serie) getIntent().getExtras().getSerializable("serie");
        dataSeriesName = serie.getSeriesName();
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
            if(!imdbId.isEmpty())
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

        getEpisodes(idSerie, 1 , token);
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
        //Toast.makeText(this, notFound, Toast.LENGTH_SHORT).show();
        Log.e(TAG,notFound);
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
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        token = sharedPreferences.getString(TOKEN, "");
        Log.e(TAG, "loadtoken: " + token);

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        presenter.cancelService();
        //Toast.makeText(getApplicationContext(), "Usa el boton de arriba jeje", Toast.LENGTH_SHORT).show();

    }

}
