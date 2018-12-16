package com.foxek.inature.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import com.foxek.inature.data.model.Sensor;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface SensorDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addSensor(Sensor sensor);

    @Query("SELECT * FROM sensor")
    Flowable<List<Sensor>> getAllSensors();
}
