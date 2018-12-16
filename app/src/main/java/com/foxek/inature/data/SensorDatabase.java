package com.foxek.inature.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.foxek.inature.data.model.Sensor;

@Database(entities = {Sensor.class},version = 1)
public abstract class SensorDatabase extends RoomDatabase {

    public abstract SensorDAO getSensorDAO();

}