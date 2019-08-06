package com.juarez.mvpdemoseries.model;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.juarez.mvpdemoseries.api.ServiceClient;
import com.juarez.mvpdemoseries.interfaces.IActor;
import com.juarez.mvpdemoseries.interfaces.ISerieDetail;
import com.juarez.mvpdemoseries.model.entity.Actor;
import com.juarez.mvpdemoseries.model.entity.Episode;
import com.juarez.mvpdemoseries.model.entity.SerieActorData;
import com.juarez.mvpdemoseries.model.entity.SerieDetail1;
import com.juarez.mvpdemoseries.model.entity.SerieDetail1Data;
import com.juarez.mvpdemoseries.model.entity.SerieDetail2;
import com.juarez.mvpdemoseries.model.entity.SerieSeasonData;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SerieDetailInteractor implements ISerieDetail.model {

    private ISerieDetail.presenter presenter;
    private String TAG = "DetailActivity";
    public static SerieDetail1 ObjSerie;
    private String imdbId;
    Call<SerieDetail1Data> call1;
    Call<SerieDetail2> call2;
    Call<SerieSeasonData> call3;
    Call<SerieActorData> call4;
    private ArrayList<Episode> listEpisodes;
    private ArrayList<Actor> listActors;
    private static final String PLOT = "full";
    private final String apikey = "2f1f55d7";
    public int totalSeasons;

    public SerieDetailInteractor(ISerieDetail.presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void cancelService() {
        call1.cancel();
        if(call2 != null)
            call2.cancel();
        if(call3 != null)
            call3.cancel();
        if(call4 != null)
            call4.cancel();
        Log.e(TAG,"servicio cancelado");
        presenter.finishSerieActivity();
    }

    @Override
    public void getSerieDetail1(String token, int id) {

        Log.e(TAG,"token "+token+" id: "+id);
        //comfig Retrofit
        SerieDetail1Data data = new SerieDetail1Data();

        call1 = ServiceClient.createSerieService().getDetailSerie(id, "Bearer " + token);
        call1.enqueue(new Callback<SerieDetail1Data>() {
            @Override
            public void onResponse(Call<SerieDetail1Data> call, Response<SerieDetail1Data> response) {

                if (response.isSuccessful()) {

                    //mostrar propiedades a usar
                    Log.e(TAG, String.valueOf(response.body().getSerie().getAirsDayOfWeek()));
                    Log.e(TAG, String.valueOf(response.body().getSerie().getOverview()));
                    Log.e(TAG, String.valueOf(response.body().getSerie().getAirsTime()));
                    Log.e(TAG, String.valueOf(response.body().getSerie().getFirstAired()));
                    Log.e(TAG, String.valueOf(response.body().getSerie().getImdbId()));

                    imdbId = String.valueOf(response.body().getSerie().getImdbId());
                    Log.e(TAG, "imdbId: " + imdbId);


                    //comprobar que la propiedad imdbId no venga vacia
                    if (imdbId.length() == 0)
                        presenter.showErrorImdbId("algunos datos no fueron encontrados en el servidor");

                    presenter.showDetail1(response.body().getSerie(), imdbId);


                } else if (response.code() == 401) {
                    presenter.showErrorNotAuthorized("Error de autenticación");
                } else if (response.code() == 404) {
                    presenter.showErrorNotFound("No se encontraron coincidencias con: ");
                } else {
                    Log.e(TAG, "algo ha salido muy mal");

                }
            }

            @Override
            public void onFailure(Call<SerieDetail1Data> call, Throwable t) {
                Log.e(TAG, t.toString());
                presenter.showErrorApi(t.toString());

            }
        });


    }

    @Override
    public void getSerieDetail2(String token, String imdbId) {
        SerieDetail1Data data = new SerieDetail1Data();

        call2 = ServiceClient.createSerieService2().getDetailSerie2(imdbId, apikey, PLOT, "Bearer " + token);
        call2.enqueue(new Callback<SerieDetail2>() {
            @Override
            public void onResponse(Call<SerieDetail2> call, Response<SerieDetail2> response) {
                if (response.isSuccessful()) {
                    //ObjSerieDetalle = response.body();
                    //mostrar propiedades a usar
                    Log.e(TAG, "" + response.body().getGenre());
                    Log.e(TAG, "" + response.body().getTotalSeasons());
                    Log.e(TAG, "" + response.body().getImdbRating());
                    Log.e(TAG, "" + response.body().getPoster());

                    try {
                        totalSeasons = Integer.parseInt(response.body().getTotalSeasons());
                    } catch (NumberFormatException e) {
                        totalSeasons = 0;
                    }
                    Log.e(TAG, "totalSeasons: " + totalSeasons);

                    presenter.showDetail2(response.body(), totalSeasons);
                    Log.e(TAG, "servicio detalles 2 correcto");
                } else if (response.code() == 401) {
                    presenter.showErrorNotAuthorized("Error de autenticación");
                } else if (response.code() == 404) {
                    presenter.showErrorNotFound("No se encontraron mas detalles ");
                } else {
                    Log.e(TAG, "algo ha salido muy mal");
                }
            }

            @Override
            public void onFailure(Call<SerieDetail2> call, Throwable t) {
                Log.e(TAG, t.toString());
                presenter.showErrorApi(t.toString());
            }
        });
    }

    @Override
    public void getEpisodes(int id, int numberSeason, String token) {
        call3 = ServiceClient.createSerieService().getSeason(id, String.valueOf(numberSeason), "Bearer " + token);

        call3.enqueue(new Callback<SerieSeasonData>() {
            @Override
            public void onResponse(Call<SerieSeasonData> call, Response<SerieSeasonData> response) {

                if (response.isSuccessful()) {

                    listEpisodes = new ArrayList<Episode>();//crear ArrayList Vacio
                    listEpisodes.clear();//limpio el Arralist

                    Log.e(TAG, String.valueOf(response.body().getEpisodes()));
                    Log.e(TAG, "Temporada: " + numberSeason);
                    for (Episode episode : response.body().getEpisodes()) {

                        Log.e(TAG, episode.getAiredEpisodeNumber() + " nombre " + episode.getEpisodeName());

                    }
                    listEpisodes = new ArrayList<>(response.body().getEpisodes());
                    presenter.showEpisodes(listEpisodes);
                    Log.e(TAG, "servicio 3 correcto");

                }else if (response.code() == 401) {
                    presenter.showErrorNotAuthorized("Error de autenticación");
                } else if (response.code() == 404) {
                    presenter.showErrorNotFound("No se encontraron episodios ");
                } else {
                    Log.e(TAG, "algo ha salido muy mal");
                }

            }

            @Override
            public void onFailure(Call<SerieSeasonData> call, Throwable t) {
                Log.e(TAG, t.toString());
                presenter.showErrorApi(t.toString());
            }
        });
    }

    @Override
    public void getActors(int id, String token) {
        call4 = ServiceClient.createSerieService().getActors(id, "Bearer " + token);

        call4.enqueue(new Callback<SerieActorData>() {
            @Override
            public void onResponse(Call<SerieActorData> call, Response<SerieActorData> response) {
                listActors = new ArrayList<Actor>();//crear ArrayList Vacio
                listActors.clear();//limpio el Arralist

                if(response.isSuccessful()){

                    Log.e(TAG,String.valueOf(response.body().getActors()));
                    for (Actor actors : response.body().getActors()) {
                        Log.e(TAG, " actor: " + actors.getName());

                        listActors.add(actors);
                    }

                }else if (response.code() == 401) {
                    presenter.showErrorNotAuthorized("Error de autenticación");
                } else if (response.code() == 404) {
                    presenter.showErrorNotFound("No se encontraron actores ");
                } else {
                    Log.e(TAG, "algo ha salido muy mal");
                }

                presenter.showActors(listActors);
            }

            @Override
            public void onFailure(Call<SerieActorData> call, Throwable t) {
                Log.e(TAG,t.toString());
                presenter.showErrorApi(t.toString());

            }
        });

    }
}
