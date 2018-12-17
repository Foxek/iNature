package com.foxek.inature.di.component;

import android.content.Context;

import com.foxek.inature.common.BaseApplication;
import com.foxek.inature.data.database.SensorDatabase;
import com.foxek.inature.data.network.ApiHelper;
import com.foxek.inature.di.ApplicationContext;
import com.foxek.inature.di.DatabaseHelper;
import com.foxek.inature.di.NetworkHelper;
import com.foxek.inature.di.module.ApplicationModule;
import com.foxek.inature.di.module.DatabaseModule;
import com.foxek.inature.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, DatabaseModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(BaseApplication app);

    @ApplicationContext
    Context context();

    @DatabaseHelper
    SensorDatabase database();

    @NetworkHelper
    ApiHelper apiHelper();


}