package com.foxek.inature.di.component;

import com.foxek.inature.di.PerActivity;
import com.foxek.inature.di.module.ActivityModule;
import com.foxek.inature.ui.preview.PreviewActivity;
import com.foxek.inature.ui.sensors.CreateDialog;
import com.foxek.inature.ui.sensors.SensorActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SensorActivity activity);

    void inject(PreviewActivity activity);

    void inject(CreateDialog dialog);
}