package com.juarez.mvpdemoseries.model;

import android.util.Log;

import com.juarez.mvpdemoseries.api.ServiceClient;
import com.juarez.mvpdemoseries.interfaces.IEpisode;
import com.juarez.mvpdemoseries.model.entity.Episode;
import com.juarez.mvpdemoseries.model.entity.SerieSeasonData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeInteractor implements IEpisode.model {

    private IEpisode.presenter presenter;
    Call<SerieSeasonData> call3;
    private String TAG = "SerieEpisodeFragment";
    private ArrayList<Episode> listEpisodes;

    public EpisodeInteractor(IEpisode.presenter presenter){
        this.presenter = presenter;
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
                    //presenter.showErrorNotAuthorized("Error de autenticación");
                } else if (response.code() == 404) {
                    //presenter.showErrorNotFound("No se encontraron episodios ");
                } else {
                    Log.e(TAG, "algo ha salido muy mal");
                }

            }

            @Override
            public void onFailure(Call<SerieSeasonData> call, Throwable t) {
                Log.e(TAG, t.toString());
                //presenter.showErrorApi(t.toString());
            }
        });
    }
}
