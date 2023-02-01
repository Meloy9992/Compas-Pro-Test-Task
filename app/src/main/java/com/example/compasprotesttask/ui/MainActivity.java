package com.example.compasprotesttask.ui;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import com.example.compasprotesttask.R;
import com.example.compasprotesttask.abstracts.presenter.MainPresenter;
import com.example.compasprotesttask.abstracts.presenter.impl.MainPresenterImpl;
import com.example.compasprotesttask.abstracts.repository.models.Weather;
import com.example.compasprotesttask.abstracts.view.MainView;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import org.json.simple.parser.ParseException;

import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenterImpl mainPresenter;

    private TextView tvWeather;
    private TextView tvTemperature;
    private TextView tvHumidity;
    private TextView tvPressure;
    private TextView tvWindSpeed;

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenterImpl(this);
        init();
        try {
            mainPresenter.getWeather();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showWeather(Weather weather) {
        tvWeather.setText(weather.getWeather());
        tvTemperature.setText(String.valueOf(weather.getTemperature()));
        tvHumidity.setText(String.valueOf(weather.getHumidity()));
        tvPressure .setText(String.valueOf(weather.getPressure()));
        tvWindSpeed.setText(String.valueOf(weather.getWindSpeed()));
    }
    public void init(){
        tvWeather = findViewById(R.id.tvWeather);
        tvTemperature = findViewById(R.id.tvTemperature);
        tvHumidity = findViewById(R.id.tvHumidity);
        tvPressure = findViewById(R.id.tvPressure);
        tvWindSpeed = findViewById(R.id.tvWindSpeed);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy gfgPolicy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(gfgPolicy);
        }
    }

}