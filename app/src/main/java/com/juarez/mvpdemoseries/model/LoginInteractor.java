package com.juarez.mvpdemoseries.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.juarez.mvpdemoseries.api.ServiceClient;
import com.juarez.mvpdemoseries.interfaces.ILogin;
import com.juarez.mvpdemoseries.model.entity.User;
import com.juarez.mvpdemoseries.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class LoginInteractor implements ILogin.IModel {
    private Context context;
    private ILogin.IPresenter presenter;
    private String apikey = "PPDZ39EGKOEHNR3R";
    private String userkey = "JOEZYXMFGR0RDBXA";
    private String username = "tavromero2yu";
    private String token;
    private static final String TAG = "LoginInteractor";


    public LoginInteractor(ILogin.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getToken() {
        User user = new User();
        user.setApikey(apikey);
        user.setUserkey(userkey);
        user.setUsername(username);

        getTokenApi(user);
    }

    @Override
    public void setContext(Context context) {
        this.context = context;

    }

    private void getTokenApi(User user) {
        Log.e(TAG, "model ->");


        Call<User> call = ServiceClient.createLoginService().getToken(user);


        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                Log.e(TAG, response.body().getToken());
                token = response.body().getToken();

                saveToken(token);
                presenter.showTokenUser(token);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                presenter.showErrorApi(t.toString());

            }
        });
    }

    private void saveToken(String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.TOKEN, token).commit();
        Log.e(TAG, "savetoken: " + token.substring(0, 5));

    }
}
