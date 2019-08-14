package com.juarez.mvpdemoseries.interfaces;

import android.content.Context;

public interface ILogin {
    interface IModel {
        void getToken();

        void setContext(Context context);

    }

    interface IPresenter {
        void getToken();

        void setContext(Context context);

        void showErrorApi(String error);

        void showTokenUser(String token);
    }

    interface IView {
        void showErrorApi(String error);

        void showTokenUser(String token);
    }
}
