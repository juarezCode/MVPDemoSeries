package com.juarez.mvpdemoseries.model;

import com.juarez.mvpdemoseries.interfaces.IDetail;
import com.juarez.mvpdemoseries.model.entity.SerieDetail1;
import com.juarez.mvpdemoseries.model.entity.SerieDetail2;

public class DetailInteractor implements IDetail.IModel {

    private IDetail.IPresenter presenter;

    public DetailInteractor(IDetail.IPresenter presenter) {
        this.presenter = presenter;

    }

    @Override
    public void getDetail1(SerieDetail1 serieDetail1) {
        SerieDetail1 detail1 = serieDetail1;
        presenter.showDetail1(detail1);
    }

    @Override
    public void getDetail2(SerieDetail2 serieDetail2) {

        SerieDetail2 detail2 = serieDetail2;
        presenter.showDetail2(detail2);
    }
}
