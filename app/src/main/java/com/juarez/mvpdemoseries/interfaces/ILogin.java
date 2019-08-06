package com.juarez.mvpdemoseries.interfaces;

import android.content.Context;

public interface ILogin {
    interface model {
        void getToken();

        void setContext(Context context);

    }

    interface presenter {
        void getToken();

        void setContext(Context context);

        void showErrorApi(String error);

        void showTokenUser(String token);
    }

    interface view {
        void showErrorApi(String error);

        void showTokenUser(String token);
    }
}
