package com.example.compasprotesttask.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.compasprotesttask.R;
import com.example.compasprotesttask.abstracts.presenter.MainPresenter;
import com.example.compasprotesttask.abstracts.presenter.impl.MainPresenterImpl;
import com.example.compasprotesttask.abstracts.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenterImpl mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenterImpl(this);
    }

    @Override
    public void showWeather() {

    }
}