package com.example.compasprotesttask.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.compasprotesttask.R;
import com.example.compasprotesttask.abstracts.view.SettingsView;

public class SettingsActivity extends AppCompatActivity implements SettingsView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    public void showText() {

    }
}