package com.example.compasprotesttask.abstracts.repository;

import com.example.compasprotesttask.abstracts.repository.models.City;

import org.json.simple.parser.ParseException;

import java.net.MalformedURLException;

public interface SettingsRepository {
    City loadCity(String json) throws ParseException;

    String downloadCity(String nameCity) throws MalformedURLException;
}
