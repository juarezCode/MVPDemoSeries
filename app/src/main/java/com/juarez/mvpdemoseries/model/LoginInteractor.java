package com.juarez.mvpdemoseries.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.juarez.mvpdemoseries.api.ILoginService;
import com.juarez.mvpdemoseries.api.ServiceClient;
import com.juarez.mvpdemoseries.interfaces.ILogin;
import com.juarez.mvpdemoseries.model.entity.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class LoginInteractor implements ILogin.model {
    private Context context;
    private ILogin.presenter presenter;
    ILoginService service;
    private String endpoint = "api.thetvdb.com";
    private String apikey = "PPDZ39EGKOEHNR3R";
    private String userkey = "JOEZYXMFGR0RDBXA";
    private String username = "tavromero2yu";
    private String SHARED_PREFS = "sharedPrefs";
    private String TOKEN = "token";
    private String token;
    private String TAG = "LoginInteractor";


    public LoginInteractor(ILogin.presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getToken() {
        User user = new User(apikey, userkey, username);
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
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN, token).commit();
        Log.e(TAG, "savetoken: " + token.substring(0, 5));

    }
}
