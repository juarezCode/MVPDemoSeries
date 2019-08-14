package com.juarez.mvpdemoseries.model;

import android.util.Log;

import com.juarez.mvpdemoseries.api.ServiceClient;
import com.juarez.mvpdemoseries.interfaces.ISerie;
import com.juarez.mvpdemoseries.model.entity.Serie;
import com.juarez.mvpdemoseries.model.entity.SerieData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SerieRepository implements ISerie.IRepository {
    private ISerie.IPresenter presenter;
    private static final String TAG = "SerieInteractor";
    private ArrayList<Serie> listSeries;

    public SerieRepository(ISerie.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void cleanAdapter() {
        listSeries.clear();
        presenter.showSeries(listSeries);
    }

    @Override
    public void getSeriesApi(String token, String search) {

        Call<SerieData> call = ServiceClient.createSerieService().getSeries(search, "Bearer " + token);

        Log.e(TAG, "search: " + search);
        Log.e(TAG, "tokenAuth: " + token);

        call.enqueue(new Callback<SerieData>() {
            @Override
            public void onResponse(Call<SerieData> call, Response<SerieData> response) {

                Log.e(TAG, "response code: " + response.code());

                if (response.isSuccessful()) {
                    listSeries = new ArrayList<>();
                    listSeries.clear();
                    Log.e(TAG, String.valueOf(response.body().getSeries()));
                    for (Serie serie : response.body().getSeries()) {

                        Log.e(TAG, "nombre de la serie: " + serie.getSeriesName());
                        listSeries.add(serie);
                    }
                    presenter.showSeries(listSeries);
                } else if (response.code() == 401) {
                    presenter.showErrorNotAuthorized("Error de autenticación");
                } else if (response.code() == 404) {
                    presenter.showErrorNotFound("No se encontraron coincidencias con: " + search);
                } else {
                    Log.e(TAG, "algo ha salido muy mal");

                }

            }

            @Override
            public void onFailure(Call<SerieData> call, Throwable t) {
                presenter.showErrorApi(t.toString());
            }
        });

    }
}
