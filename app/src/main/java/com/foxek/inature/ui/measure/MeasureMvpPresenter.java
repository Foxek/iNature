package com.foxek.inature.ui.measure;

import com.foxek.inature.ui.base.MvpPresenter;

public interface MeasureMvpPresenter extends MvpPresenter<MeasureMvpView,MeasureMvpInteractor>{

    void deleteSensor();

    void renameSensor(String name);

    void editButtonPressed();
}
