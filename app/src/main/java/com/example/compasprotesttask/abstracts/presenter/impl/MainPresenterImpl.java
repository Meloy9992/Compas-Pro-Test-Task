package com.example.compasprotesttask.abstracts.presenter.impl;

import com.example.compasprotesttask.abstracts.presenter.MainPresenter;
import com.example.compasprotesttask.abstracts.repository.MainRepository;
import com.example.compasprotesttask.abstracts.repository.impl.MainRepositoryImpl;
import com.example.compasprotesttask.ui.MainActivity;

public class MainPresenterImpl implements MainPresenter {
    private MainActivity mainActivity;
    private MainRepository mainRepository;

    public MainPresenterImpl(MainActivity mainActivity) {
    this.mainActivity = mainActivity;
    this.mainRepository = new MainRepositoryImpl();
    }

    @Override
    public void onButtonWasClicked() {

    }

    @Override
    public void onDestroy() {

    }
}
