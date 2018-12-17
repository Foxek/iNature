package com.foxek.inature.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.foxek.inature.data.database.model.Measure;

import java.util.List;

@Dao
public interface MeasureDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAllMeasure(List<Measure> measures);
}
