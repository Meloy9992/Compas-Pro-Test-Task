package com.example.compasprotesttask.abstracts.repository;

import android.location.Location;

import com.example.compasprotesttask.abstracts.repository.models.Weather;

import org.json.simple.parser.ParseException;

import java.net.MalformedURLException;
import java.util.List;

public interface MainRepository {
    String downloadWeather(double lat, double lon) throws MalformedURLException;

    Weather getWeather(String json) throws ParseException;

    float getCelsiusFromKelvin(float kelvin);
}
