package com.example.compasprotesttask.abstracts.presenter;

import com.example.compasprotesttask.abstracts.repository.models.City;

import org.json.simple.parser.ParseException;

import java.net.MalformedURLException;

public interface SettingsPresenter {
    City getCity(String nameCity) throws MalformedURLException, ParseException;
}
