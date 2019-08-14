package com.juarez.mvpdemoseries.interfaces;

import com.juarez.mvpdemoseries.model.entity.SerieDetail1;
import com.juarez.mvpdemoseries.model.entity.SerieDetail2;

public interface IDetail {
    interface IModel {
        void getDetail1(SerieDetail1 serieDetail1);

        void getDetail2(SerieDetail2 serieDetail2);

    }

    interface IPresenter {
        void getDetail1(SerieDetail1 serieDetail1);

        void showDetail1(SerieDetail1 serieDetail1);

        void getDetail2(SerieDetail2 serieDetail2);

        void showDetail2(SerieDetail2 serieDetail2);
    }

    interface IView {
        void getDetail1(SerieDetail1 serieDetail1);

        void showDetail1(SerieDetail1 serieDetail1);

        void getDetail2(SerieDetail2 serieDetail2);

        void showDetail2(SerieDetail2 serieDetail2);
    }
}
