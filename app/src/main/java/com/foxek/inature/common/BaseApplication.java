package com.foxek.inature.common;

import android.app.Application;

import com.foxek.inature.di.component.ApplicationComponent;
import com.foxek.inature.di.component.DaggerApplicationComponent;
import com.foxek.inature.di.module.ApplicationModule;
import com.foxek.inature.di.module.DatabaseModule;

public class BaseApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .databaseModule(new DatabaseModule(this))
                .build();

        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

}
