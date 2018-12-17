package com.foxek.inature.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.foxek.inature.data.database.model.Measure;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface MeasureDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAllMeasure(List<Measure> measures);

    @Query("SELECT * FROM Measure WHERE sensorId IS :Id")
    Single<List<Measure>> getAllMeasures(int Id);
}
