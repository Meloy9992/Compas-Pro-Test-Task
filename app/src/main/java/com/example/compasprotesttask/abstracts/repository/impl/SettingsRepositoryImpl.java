package com.example.compasprotesttask.abstracts.repository.impl;

import com.example.compasprotesttask.abstracts.repository.SettingsRepository;
import com.example.compasprotesttask.abstracts.repository.models.City;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SettingsRepositoryImpl implements SettingsRepository {
    private final String API_KEY = "21d93414a9c73d83ab440d2f8b4d3c79"; //Ваш API ключ
    private final int limit = 1;

    @Override
    public String downloadCity(String nameCity) throws MalformedURLException {
        String input;
        URL url = new URL("http://" + "api.openweathermap.org/geo/1.0/direct?q=" + nameCity + "&limit=" + limit + "&appid=" + API_KEY);

        try{
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            input = reader.readLine();
            System.out.println("Ответ по запросу: " + input); //Ответ от openweathermap.org
            reader.close();
            return input;
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    public City loadCity(String json) throws ParseException {
        City city = new City();

        JSONArray jsonArray = (JSONArray) new JSONParser().parse(json);

        JSONObject name = (JSONObject) jsonArray.iterator().next();//Получение объекта weather
        city.setLat(
                Double.valueOf(
                        name.get("lat").toString()));// Получение Широты
        city.setLon(
                Double.valueOf(
                        name.get("lon").toString()));// Получение Долготы
        city.setCountry(
                name.get("country").toString());// Получение страны
        city.setState(
                name.get("state").toString());// Получение штата/области

        return city;
    }
}
