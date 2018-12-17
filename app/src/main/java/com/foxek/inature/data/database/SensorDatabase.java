package com.foxek.inature.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.foxek.inature.data.database.model.Measure;
import com.foxek.inature.data.database.model.Sensor;

@Database(entities = {Sensor.class, Measure.class},version = 1)
public abstract class SensorDatabase extends RoomDatabase {

    public abstract SensorDAO getSensorDAO();
    public abstract MeasureDAO getMeasureDAO();
}