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
    }

    public void init() throws MalformedURLException, ParseException {
        tvWeather = findViewById(R.id.tvWeather);
        tvTemperature = findViewById(R.id.tvTemperature);
        tvHumidity = findViewById(R.id.tvHumidity);
        tvPressure = findViewById(R.id.tvPressure);
        tvWindSpeed = findViewById(R.id.tvWindSpeed);
        btnSettings = findViewById(R.id.btnSettings);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy gfgPolicy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(gfgPolicy);
        }

        mainPresenter = new MainPresenterImpl(this);

        mainPresenter.getWeather(lat, lon);


        btnSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                double [] coordinates = {lat, lon};
                Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(myIntent, REQUEST_CODE_CITY);
                MainActivity.this.startActivity(myIntent);
            }
        });


/*        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(GPS_PROVIDER,
                1000 * 10, 10, locationListener);
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 1000 * 10, 10,
                locationListener);
        try {
            mainPresenter.getWeather();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
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
            // если вернулось не ОК
        } else {
            Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show();
        }
    }

/*    @Override
    public void onResume(){
        super.onResume();
        Intent intent = getIntent();
        city = intent.getParcelableExtra("city");
        System.out.println(city.getCountry());

    }*/
 /*   @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(GPS_PROVIDER,
                1000 * 10, 10, locationListener);
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 1000 * 10, 10,
                locationListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            try {
                if (location.getProvider().equals(GPS_PROVIDER)) {
                    mainPresenter.getWeather(location.getLatitude(), location.getLongitude());
                } else if (location.getProvider().equals(
                        LocationManager.NETWORK_PROVIDER)) {
                    mainPresenter.getWeather(location.getLatitude(), location.getLongitude());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    };*/

}