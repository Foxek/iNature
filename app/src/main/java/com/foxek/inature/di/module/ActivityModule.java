package com.foxek.inature.di.module;

import com.foxek.inature.di.PerActivity;
import com.foxek.inature.ui.sensors.SensorInteractor;
import com.foxek.inature.ui.sensors.SensorMvpPresenter;
import com.foxek.inature.ui.sensors.SensorPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    @Provides
    @PerActivity
    SensorMvpPresenter provideSensorPresenter(SensorInteractor interactor){
        return new SensorPresenter(interactor);
    }
}
