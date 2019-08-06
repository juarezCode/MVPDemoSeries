package com.juarez.mvpdemoseries.presenter;

import com.juarez.mvpdemoseries.interfaces.IDetail;
import com.juarez.mvpdemoseries.model.DetailInteractor;
import com.juarez.mvpdemoseries.model.entity.Actor;
import com.juarez.mvpdemoseries.model.entity.SerieDetail1;
import com.juarez.mvpdemoseries.model.entity.SerieDetail2;

import java.util.ArrayList;

public class DetailPresenter implements IDetail.presenter {

    private IDetail.view view;
    private IDetail.model interactor;
    public DetailPresenter(IDetail.view view){
        this.view = view;
        interactor = new DetailInteractor(this);
    }

    @Override
    public void getDetail1() {
        interactor.getDetail1();
    }

    @Override
    public void showDetail1(SerieDetail1 serieDetail1) {
        view.showDetail1(serieDetail1);
    }

    @Override
    public void getDetail2() {
        interactor.getDetail2();
    }

    @Override
    public void showDetail2(SerieDetail2 serieDetail2) {
        view.showDetail2(serieDetail2);
    }
}
