package com.juarez.mvpdemoseries.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.juarez.mvpdemoseries.R;
import com.juarez.mvpdemoseries.interfaces.IDetail;
import com.juarez.mvpdemoseries.model.entity.SerieDetail1;
import com.juarez.mvpdemoseries.model.entity.SerieDetail2;
import com.juarez.mvpdemoseries.presenter.DetailPresenter;
import com.squareup.picasso.Picasso;


import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class SerieDetailFragment extends Fragment implements IDetail.view {
    private IDetail.presenter presenter;
    @BindView(R.id.txtFragDetailFirstAired)
    TextView txtFisrtAired;
    @BindView(R.id.txtFragDetailDateHour)
    TextView txtDateHour;
    @BindView(R.id.txtFragDetailOverview)
    TextView txtOverview;
    @BindView(R.id.imgFragDetail)
    ImageView imageSerie;
    @BindView(R.id.txtFragDetailGenre)
    TextView txtGenre;
    @BindView(R.id.txtFragDetailRating)
    TextView txtRating;
    @BindView(R.id.txtFragDetailSeason)
    TextView txtSeason;

    public static String TAG = "SerieDetailFragment";


    public SerieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_serie_detail, container, false);
        ButterKnife.bind(this, view);
        presenter = new DetailPresenter(this);

        getDetail1();

        getDetail2();

        return view;
    }

    @Override
    public void getDetail1() {
        presenter.getDetail1();
    }

    @Override
    public void showDetail1(SerieDetail1 serieDetail1) {
        if (serieDetail1.getOverview() == null)
            txtOverview.setText("No disponible");
        else
            txtOverview.setText("" + serieDetail1.getOverview());

        if (serieDetail1.getAirsTime().length() == 0 && serieDetail1.getAirsTime().length() == 0)
            txtDateHour.setText("no disponible");
        else
            txtDateHour.setText(serieDetail1.getAirsTime() + " " + serieDetail1.getAirsDayOfWeek());

        if (serieDetail1.getFirstAired() == null || serieDetail1.getFirstAired().length() == 0)
            txtFisrtAired.setText("no disponible");
        else
            txtFisrtAired.setText("" + serieDetail1.getFirstAired());


        Log.e(TAG, "Servicio 1, Carga de datos correcto");
    }

    @Override
    public void getDetail2() {
        presenter.getDetail2();
    }

    @Override
    public void showDetail2(SerieDetail2 serieDetail2) {
        if (serieDetail2.getGenre() == null)
            txtGenre.setText("No disponible");
        else
            txtGenre.setText("" + serieDetail2.getGenre());

        if (serieDetail2.getTotalSeasons() == null)
            txtSeason.setText("No disponible");
        else
            txtSeason.setText("" + serieDetail2.getTotalSeasons());

        if (serieDetail2.getImdbRating() == null)
            txtRating.setText("0");
        else
            txtRating.setText(serieDetail2.getImdbRating() + "/10");

        if (serieDetail2.getPoster() == null) {

        } else {
            Picasso.get()
                .load(serieDetail2.getPoster())
                .into(imageSerie);

        }

        Log.e(TAG, "servicio 2, carga de datos correcto");
    }
}
