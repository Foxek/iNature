package com.foxek.inature.data.model;

import com.foxek.inature.data.SensorDatabase;
import com.foxek.inature.di.DatabaseHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class SensorRepository {

    private SensorDatabase      mDatabase;

    @Inject
    SensorRepository(@DatabaseHelper SensorDatabase database){
        mDatabase = database;
    }

    public Flowable<List<Sensor>> getAllSensors(){
        return mDatabase.getSensorDAO().getAllSensors();
    }

    public void createSensor(Sensor sensor){
        mDatabase.getSensorDAO().addSensor(sensor);
    }
}
