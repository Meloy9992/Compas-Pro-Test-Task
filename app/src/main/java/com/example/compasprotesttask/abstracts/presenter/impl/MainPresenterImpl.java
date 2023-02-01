package com.example.compasprotesttask.abstracts.presenter.impl;

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
    public void getWeather(double lat, double lon) throws MalformedURLException, ParseException {
        jsonWeather = mainRepository.downloadWeather(lat, lon);
        weather = mainRepository.getWeather(jsonWeather);
        mainActivity.showWeather(weather);
    }

    @Override
    public void onDestroy() {
    }
}
