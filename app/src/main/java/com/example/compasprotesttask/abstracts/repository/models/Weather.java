package com.example.compasprotesttask.abstracts.repository.models;

public class Weather {
    private String weather;
    private String weatherDescription;
    private float temperature;
    private int humidity;
    private int pressure;
    private double windSpeed;
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Weather(String weather, String weatherDescription, float temperature, int humidity, int pressure, double windSpeed, String icon) {
        this.weather = weather;
        this.weatherDescription = weatherDescription;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.icon = icon;
    }

    public Weather() {
    }

}
