package com.foxek.inature.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.foxek.inature.data.SensorDatabase;
import com.foxek.inature.di.DatabaseHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    private SensorDatabase mDatabase;

    public DatabaseModule(Application application){
        mDatabase = Room.databaseBuilder(application, SensorDatabase.class, "sensor.db")
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @DatabaseHelper
    SensorDatabase provideSensorDatabase(){
        return mDatabase;
    }

}
