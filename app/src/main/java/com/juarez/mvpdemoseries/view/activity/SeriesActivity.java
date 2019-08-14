package com.juarez.mvpdemoseries.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.juarez.mvpdemoseries.R;
import com.juarez.mvpdemoseries.adapter.SerieAdapter;
import com.juarez.mvpdemoseries.interfaces.ISerie;
import com.juarez.mvpdemoseries.model.entity.Serie;
import com.juarez.mvpdemoseries.presenter.SeriePresenter;
import com.juarez.mvpdemoseries.util.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeriesActivity extends AppCompatActivity implements ISerie.IView, View.OnClickListener {
    private ISerie.IPresenter presenter;
    @BindView(R.id.recyclerSeries)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar2)
    ProgressBar progressBar;
    @BindView(R.id.edtSearchview)
    EditText edtSearch;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    @BindView(R.id.btnErase)
    Button btnErase;
    private String token;
    private static final String TAG = "SeriesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        ButterKnife.bind(this);
        presenter = new SeriePresenter(this);
        btnSearch.setOnClickListener(this);
        btnErase.setOnClickListener(this);

        //recycler Series
        loadRecyclerSeries();
    }

    @Override
    public void onClick(View v) {
        //Button - method that search series depending of searching
        if (v.getId() == R.id.btnSearch) {
            String searchValue = edtSearch.getText().toString();

            if (searchValue.length() == 0) {
                Toast.makeText(this, "Debes ingresar algo", Toast.LENGTH_SHORT).show();
            } else {
                progressBar.setVisibility(View.VISIBLE);
                loadToken();
                presenter.getSeries(token, searchValue);
            }

        }
        //Button - method that erase the edittext of search
        if (v.getId() == R.id.btnErase) {
            edtSearch.setText("");
            btnSearch.setVisibility(View.VISIBLE);
            btnErase.setVisibility(View.GONE);
            presenter.cleanAdapter();
        }

    }

    @Override
    public void showErrorNotFound(String notFound) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, notFound, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorNotAuthorized(String notAuthorized) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, notAuthorized, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorApi(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        btnSearch.setVisibility(View.GONE);
        btnErase.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSeries(ArrayList<Serie> listSeries) {
        if (!listSeries.isEmpty()) {
            progressBar.setVisibility(View.GONE);
            btnSearch.setVisibility(View.GONE);
            btnErase.setVisibility(View.VISIBLE);
            hideSoftKeyboard();
        }
        SerieAdapter serieAdapter = new SerieAdapter(this, listSeries);
        recyclerView.setAdapter(serieAdapter);
        serieAdapter.notifyDataSetChanged();
    }

    //load token from device
    public String loadToken() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFS, MODE_PRIVATE);
        token = sharedPreferences.getString(Constants.TOKEN, "");
        Log.e(TAG, "loadtoken: " + token);
        return token;
    }

    //load basic recyclerView components
    private void loadRecyclerSeries() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    //hide Keyboard
    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
