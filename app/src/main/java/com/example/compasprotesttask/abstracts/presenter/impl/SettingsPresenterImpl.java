package com.example.compasprotesttask.abstracts.presenter.impl;

import com.example.compasprotesttask.abstracts.presenter.SettingsPresenter;
import com.example.compasprotesttask.abstracts.repository.SettingsRepository;
import com.example.compasprotesttask.abstracts.repository.impl.SettingsRepositoryImpl;
import com.example.compasprotesttask.abstracts.repository.models.City;
import com.example.compasprotesttask.abstracts.repository.models.Weather;
import com.example.compasprotesttask.ui.SettingsActivity;

import org.json.simple.parser.ParseException;

import java.net.MalformedURLException;

public class SettingsPresenterImpl implements SettingsPresenter {

    private SettingsActivity settingsActivity;
    private SettingsRepository settingsRepository;

    private String jsonCity;
    private City city;

    public SettingsPresenterImpl(SettingsActivity settingsActivity) {
        this.settingsActivity = settingsActivity;
        this.settingsRepository = new SettingsRepositoryImpl();
    }

    @Override
    public City getCity(String nameCity) throws MalformedURLException, ParseException {
        jsonCity = settingsRepository.downloadCity(nameCity);
        city = settingsRepository.loadCity(jsonCity);
        return city;
    }

    @Override
    public void onDestroy() {

    }
}
