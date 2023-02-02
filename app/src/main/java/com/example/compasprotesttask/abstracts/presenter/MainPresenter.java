package com.example.compasprotesttask.abstracts.presenter;

import org.json.simple.parser.ParseException;

import java.net.MalformedURLException;

public interface MainPresenter {
    void getWeather(double lat, double lon) throws MalformedURLException, ParseException;
}
