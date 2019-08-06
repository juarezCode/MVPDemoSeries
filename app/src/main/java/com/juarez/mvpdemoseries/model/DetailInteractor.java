package com.juarez.mvpdemoseries.model;

import com.juarez.mvpdemoseries.interfaces.IDetail;
import com.juarez.mvpdemoseries.model.entity.SerieDetail1;
import com.juarez.mvpdemoseries.model.entity.SerieDetail2;
import com.juarez.mvpdemoseries.view.activity.DetailActivity;

public class DetailInteractor implements  IDetail.model{

    private IDetail.presenter presenter;
    private SerieDetail1 serieDetail1;
    private SerieDetail2 serieDetail2;

    public DetailInteractor(IDetail.presenter presenter){
        this.presenter = presenter;

    }

    @Override
    public void getDetail1() {
        serieDetail1 = DetailActivity.serieDetail1;

        presenter.showDetail1(serieDetail1);

    }

    @Override
    public void getDetail2() {

        serieDetail2 = DetailActivity.serieDetail2;
        presenter.showDetail2(serieDetail2);

    }
}
