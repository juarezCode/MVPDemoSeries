package com.juarez.mvpdemoseries.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.juarez.mvpdemoseries.R;
import com.juarez.mvpdemoseries.interfaces.ILogin;
import com.juarez.mvpdemoseries.presenter.LoginPresenter;
import com.juarez.mvpdemoseries.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements ILogin.IView, View.OnClickListener {
    private ILogin.IPresenter presenter;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
        btnLogin.setOnClickListener(this);

        //consulta si ya existe algun token
        String tokenCargado = loadToken();
        if (tokenCargado.length() > 0) {
            Intent intent = new Intent(this, SeriesActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            Log.e(TAG, "vista ->");
            progressBar.setVisibility(View.VISIBLE);
            btnLogin.setEnabled(false);

            presenter.setContext(getApplicationContext());
            presenter.getToken();
        }

    }

    @Override
    public void showErrorApi(String error) {
        btnLogin.setEnabled(true);
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTokenUser(String token) {
        Log.e(TAG, "<- vista");
        progressBar.setVisibility(View.GONE);
        btnLogin.setEnabled(true);

        Toast.makeText(this, "Se obtuvo token: " + token.substring(0, 5) + "...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SeriesActivity.class);
        startActivity(intent);

    }

    public String loadToken() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFS, MODE_PRIVATE);
        String token = sharedPreferences.getString(Constants.TOKEN, "");
        Log.e(TAG, "loadtoken: " + token);
        return token;
    }
}
