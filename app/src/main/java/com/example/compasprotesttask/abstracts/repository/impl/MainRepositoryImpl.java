package com.example.compasprotesttask.abstracts.repository.impl;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.compasprotesttask.abstracts.repository.MainRepository;
import com.example.compasprotesttask.abstracts.repository.models.Api;
import com.example.compasprotesttask.abstracts.repository.models.Weather;
import com.example.compasprotesttask.ui.MainActivity;

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

import static android.content.Context.LOCATION_SERVICE;

public class MainRepositoryImpl implements MainRepository {


    @Override
    public String downloadWeather(double lat, double lon) throws MalformedURLException {
        String input;
        URL url = new URL("http://" + "api.openweathermap.org/data/2.5/weather?lat="
                + lat + "&lon=" +
                lon + "&appid=" + new Api().getAPI_KEY());

        try {
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            input = reader.readLine();
            Log.d("[INFO]", "Ответ по запросу: " + input); //Ответ от openweathermap.org
            reader.close();
            return input;
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    public Weather getWeather(String json) throws ParseException {
        Weather weather = new Weather();
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);

        JSONArray arrayWeather = (JSONArray) jsonObject.get("weather"); //Получение массива weather из полученного Json
        JSONObject objectWeather = (JSONObject) arrayWeather.iterator().next();//Получение объекта weather

        weather.setWeatherDescription(
                objectWeather.
                        get("description").toString());//Получение и присваивание описания погоды
        weather.setWeather(objectWeather.
                get("main").toString());//Получение и присваивание погоды

        JSONObject main = (JSONObject) jsonObject.get("main"); //Получение объекта main

        weather.setTemperature(
                getCelsiusFromKelvin(
                        Float.parseFloat(
                                main.get("temp").toString())));// Получение из объекта main поле temp
        weather.setPressure(
                Integer.parseInt(
                        main.get("pressure").toString()));// Получение и присваивание давления
        weather.setHumidity(
                Integer.parseInt(
                        main.get("humidity").toString()));// Получение и присваивание влажности

        JSONObject wind = (JSONObject) jsonObject.get("wind");// Получение объекта wind

        weather.setWindSpeed(
                Double.parseDouble(
                        wind.get("speed").toString()));// Получение и присваивание скорости ветра
        return weather;
    }

    @Override
    public float getCelsiusFromKelvin(float kelvin) {
        return (float) (kelvin - 273.15);
    }


}
