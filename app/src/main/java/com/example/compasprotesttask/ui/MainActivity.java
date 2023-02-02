package com.example.compasprotesttask.ui;


import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.compasprotesttask.R;
import com.example.compasprotesttask.abstracts.presenter.impl.MainPresenterImpl;
import com.example.compasprotesttask.abstracts.repository.models.City;
import com.example.compasprotesttask.abstracts.repository.models.Weather;
import com.example.compasprotesttask.abstracts.view.MainView;

import org.json.simple.parser.ParseException;

import java.net.MalformedURLException;


public class MainActivity extends AppCompatActivity implements MainView {

    final int REQUEST_CODE_CITY = 1;

    private MainPresenterImpl mainPresenter;

    private TextView tvWeather;
    private TextView tvTemperature;
    private TextView tvHumidity;
    private TextView tvPressure;
    private TextView tvWindSpeed;

    private ImageView imgView;

    private Button btnSettings;

    private double lat = 48.7194;
    private double lon = 44.5018;

    private City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            init();
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
        tvPressure.setText(String.valueOf(weather.getPressure()));
        tvWindSpeed.setText(String.valueOf(weather.getWindSpeed()));
        getImage(weather);
    }

    public void init() throws MalformedURLException, ParseException {
        tvWeather = findViewById(R.id.tvWeather);
        tvTemperature = findViewById(R.id.tvTemperature);
        tvHumidity = findViewById(R.id.tvHumidity);
        tvPressure = findViewById(R.id.tvPressure);
        tvWindSpeed = findViewById(R.id.tvWindSpeed);
        imgView = findViewById(R.id.imgView);
        btnSettings = findViewById(R.id.btnSettings);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy gfgPolicy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(gfgPolicy);
        }

        mainPresenter = new MainPresenterImpl(this);

        mainPresenter.getWeather(lat, lon);

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(myIntent, REQUEST_CODE_CITY);
                MainActivity.this.startActivity(myIntent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_CITY:
                    City city = (City) data.getSerializableExtra("city");
                    try {
                        mainPresenter.getWeather(city.getLat(), city.getLon());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        } else {
            Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getImage(Weather weather) {
        switch (weather.getWeatherDescription()) {
            case "clear sky":
                imgView.setImageResource(R.drawable.sunny);
                break;
            case "few clouds":
                imgView.setImageResource(R.drawable.partly_cloudy);
                break;
            case "scattered clouds":
                imgView.setImageResource(R.drawable.cloudy);
                break;
            case "broken clouds":
                imgView.setImageResource(R.drawable.mainly_cloudy);
                break;
            case "shower rain":
                imgView.setImageResource(R.drawable.heavy_rain);
                break;
            case "rain":
                imgView.setImageResource(R.drawable.drizzle);
                break;
            case "thunderstorm":
                imgView.setImageResource(R.drawable.storm);
                break;
            case "snow":
                imgView.setImageResource(R.drawable.snow);
                break;
            case "mist":
                imgView.setImageResource(R.drawable.fog);
                break;
        }
    }


}