package com.example.compasprotesttask.abstracts.presenter.impl;

import android.location.Location;

import com.example.compasprotesttask.abstracts.presenter.MainPresenter;
import com.example.compasprotesttask.abstracts.repository.MainRepository;
import com.example.compasprotesttask.abstracts.repository.impl.MainRepositoryImpl;
import com.example.compasprotesttask.abstracts.repository.models.Weather;
import com.example.compasprotesttask.ui.MainActivity;

import org.json.simple.parser.ParseException;

import java.net.MalformedURLException;

public class MainPresenterImpl implements MainPresenter {
    private MainActivity mainActivity;
    private MainRepository mainRepository;

    private String jsonWeather;
    private Weather weather;

    public MainPresenterImpl(MainActivity mainActivity) {
    this.mainActivity = mainActivity;
    this.mainRepository = new MainRepositoryImpl();
    }


    @Override
    public void getWeather() throws MalformedURLException, ParseException {
        jsonWeather = mainRepository.downloadWeather("48.7194", "44.5018");
        weather = mainRepository.getWeather(jsonWeather);
        mainActivity.showWeather(weather);
    }

    @Override
    public void onButtonWasClicked() {
    }

    @Override
    public void onDestroy() {
    }
}
