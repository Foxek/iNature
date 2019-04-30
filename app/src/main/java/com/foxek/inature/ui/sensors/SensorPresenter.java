package com.foxek.inature.ui.sensors;

import android.os.Bundle;

import com.foxek.inature.common.Constants;
import com.foxek.inature.ui.base.BasePresenter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class SensorPresenter extends BasePresenter<SensorMvpView,SensorMvpInteractor> implements SensorMvpPresenter {

    private Bundle args;

    public SensorPresenter(SensorMvpInteractor interactor, CompositeDisposable disposable, Bundle args){
        super(interactor,disposable);
        this.args = args;
    }

    @Override
    public void viewIsReady() {
        if ((args != null))
            if (args.containsKey(Constants.CREATE_STATE))
                getView().showStateDialog(args.getBoolean(Constants.CREATE_STATE));

        createSensorListAdapter();
    }

    private void createSensorListAdapter (){
        getView().setSensorList(getInteractor().createSensorListAdapter());
            registerItemCallback();
        getDisposable().add(getInteractor().scheduleListChanged());

    }

    private void registerItemCallback() {
        getDisposable().add(getInteractor().onSensorItemClick()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sensor ->
                        getView().startMeasureActivity(sensor.uid,sensor.getName(),sensor.getIcon(),
                                sensor.getAddress(),sensor.getType())
                        , throwable -> {}));

    }

    @Override
    public void addButtonPressed(String name, String mac) {
        getView().startPreviewActivity(name,mac);
    }

    @Override
    public void scanButtonPressed() {
        getView().startScanActivity();
    }
}
