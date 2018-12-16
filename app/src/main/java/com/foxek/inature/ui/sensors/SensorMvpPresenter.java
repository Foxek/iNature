package com.foxek.inature.ui.sensors;

import com.foxek.inature.ui.base.MvpPresenter;

public interface SensorMvpPresenter extends MvpPresenter<SensorMvpView,SensorMvpInteractor> {

    void addButtonPressed(String name, String mac);

    void scanButtonPressed();

}
