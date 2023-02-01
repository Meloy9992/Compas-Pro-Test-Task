package com.example.compasprotesttask.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.compasprotesttask.R;
import com.example.compasprotesttask.abstracts.presenter.SettingsPresenter;
import com.example.compasprotesttask.abstracts.presenter.impl.SettingsPresenterImpl;
import com.example.compasprotesttask.abstracts.repository.models.City;
import com.example.compasprotesttask.abstracts.view.SettingsView;

import org.json.simple.parser.ParseException;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity implements SettingsView {
    private SettingsPresenter settingsPresenter;
    private double [] coordinates = new double[2];
    List<String> data = new ArrayList<>();
    Spinner cities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        init();
    }

    public void init(){
        Intent intent = getIntent();
        coordinates = intent.getDoubleArrayExtra("key");

        settingsPresenter = new SettingsPresenterImpl(this);

        data.add("Волгоград");
        data.add("Москва");
        data.add("Владивосток");
        data.add("Архангельск");
        //TODO Если будет время добавить API с городами

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cities = (Spinner) findViewById(R.id.spinner);

        cities.setAdapter(adapter);


        cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i == 0){

                    }else {
                        City city = new City();
                        city = settingsPresenter.getCity(data.get(i));
                        Intent myIntent = new Intent(SettingsActivity.this, MainActivity.class);
                        myIntent.putExtra("city",(Serializable) city); //Optional parameters
                        setResult(RESULT_OK, myIntent);
                        finish();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    @Override
    public void showText() {

    }
}