package com.foxek.inature.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import com.foxek.inature.data.database.model.Measure;
import com.foxek.inature.data.database.model.Sensor;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface SensorDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addSensor(Sensor sensor);

    @Query("SELECT * FROM sensor")
    Flowable<List<Sensor>> getAllSensors();

    @Query("SELECT COALESCE(MAX(uid), 0) FROM sensor")
    Single<Integer> getMaxPrimaryKey();

}
