package com.foxek.inature.di.module;

import android.os.Bundle;

import com.foxek.inature.di.PerActivity;
import com.foxek.inature.ui.measure.MeasureInteractor;
import com.foxek.inature.ui.measure.MeasureMvpPresenter;
import com.foxek.inature.ui.measure.MeasurePresenter;
import com.foxek.inature.ui.preview.PreviewInteractor;
import com.foxek.inature.ui.preview.PreviewMvpPresenter;
import com.foxek.inature.ui.preview.PreviewPresenter;
import com.foxek.inature.ui.sensors.SensorInteractor;
import com.foxek.inature.ui.sensors.SensorMvpPresenter;
import com.foxek.inature.ui.sensors.SensorPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private final Bundle args;

    public ActivityModule(Bundle args) {
        this.args = args;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    SensorMvpPresenter provideSensorPresenter(SensorInteractor interactor, CompositeDisposable disposable){
        return new SensorPresenter(interactor,disposable,args);
    }

    @Provides
    @PerActivity
    PreviewMvpPresenter providePreviewPresenter(PreviewInteractor interactor, CompositeDisposable disposable){
        return new PreviewPresenter(interactor,disposable,args);
    }

    @Provides
    @PerActivity
    MeasureMvpPresenter provideMeasurePresenter(MeasureInteractor interactor, CompositeDisposable disposable){
        return new MeasurePresenter(interactor,disposable,args);
    }
}
