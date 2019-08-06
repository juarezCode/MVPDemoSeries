package com.juarez.mvpdemoseries.interfaces;

import com.juarez.mvpdemoseries.model.entity.Actor;
import com.juarez.mvpdemoseries.model.entity.SerieDetail1;
import com.juarez.mvpdemoseries.model.entity.SerieDetail2;

import java.util.ArrayList;

public interface IDetail {
    interface model{
        void getDetail1();
        void getDetail2();

    }
    interface presenter{
        void getDetail1();
        void showDetail1(SerieDetail1 serieDetail1);

        void getDetail2();
        void showDetail2(SerieDetail2 serieDetail2);
    }
    interface view{
        void getDetail1();
        void showDetail1(SerieDetail1 serieDetail1);

        void getDetail2();
        void showDetail2(SerieDetail2 serieDetail2);
    }
}
