package com.foxek.inature.data.database;

import com.foxek.inature.data.database.model.Measure;
import com.foxek.inature.data.database.model.Sensor;
import com.foxek.inature.di.DatabaseHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class LocalRepository {

    private SensorDatabase      mDatabase;

    @Inject
    LocalRepository(@DatabaseHelper SensorDatabase database){
        mDatabase = database;
    }

    public Flowable<List<Sensor>> getAllSensors(){
        return mDatabase.getSensorDAO().getAllSensors();
    }

    public Completable createSensor(Sensor sensor) {
        return Completable.fromAction(() -> mDatabase.getSensorDAO().addSensor(sensor));
    }

    public Single<Integer> getMaxPrimaryKey(){
        return mDatabase.getSensorDAO().getMaxPrimaryKey();
    }

    public void deleteSensor(int id) {
        mDatabase.getSensorDAO().deleteSensor(id);
    }

    public void renameSensor(int id, String name) {
        mDatabase.getSensorDAO().renameSensor(id,name);
    }

    public Completable createMeasures(int sensorId, List<Measure> measures){
        for (Measure measure : measures){
            measure.setSensorId(sensorId);
        }
        return Completable.fromAction(() -> mDatabase.getMeasureDAO().addAllMeasure(measures));
    }

    public Single<List<Measure>> getAllMeasure(int sensorId){
        return mDatabase.getMeasureDAO().getAllMeasures(sensorId);
    }
}
