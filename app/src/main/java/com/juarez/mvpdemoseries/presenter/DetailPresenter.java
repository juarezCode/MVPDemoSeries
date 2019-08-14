package com.juarez.mvpdemoseries.presenter;

import com.juarez.mvpdemoseries.interfaces.IDetail;
import com.juarez.mvpdemoseries.model.DetailInteractor;
import com.juarez.mvpdemoseries.model.entity.SerieDetail1;
import com.juarez.mvpdemoseries.model.entity.SerieDetail2;

public class DetailPresenter implements IDetail.IPresenter {

    private IDetail.IView view;
    private IDetail.IModel interactor;

    public DetailPresenter(IDetail.IView view) {
        this.view = view;
        interactor = new DetailInteractor(this);
    }

    @Override
    public void getDetail1(SerieDetail1 serieDetail1) {
        interactor.getDetail1(serieDetail1);
    }

    @Override
    public void showDetail1(SerieDetail1 serieDetail1) {
        view.showDetail1(serieDetail1);
    }

    @Override
    public void getDetail2(SerieDetail2 serieDetail2) {
        interactor.getDetail2(serieDetail2);
    }

    @Override
    public void showDetail2(SerieDetail2 serieDetail2) {
        view.showDetail2(serieDetail2);
    }
}
