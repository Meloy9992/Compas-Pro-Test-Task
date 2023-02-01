package com.example.compasprotesttask.abstracts.presenter;

import android.location.Location;

import com.example.compasprotesttask.abstracts.repository.models.Weather;

import org.json.simple.parser.ParseException;

import java.net.MalformedURLException;

public interface MainPresenter {
    void getWeather() throws MalformedURLException, ParseException;
    void onButtonWasClicked();
    void onDestroy();
}
