package com.foxek.inature.ui.sensors;

import com.foxek.inature.ui.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class SensorPresenter extends BasePresenter<SensorMvpView,SensorMvpInteractor> implements SensorMvpPresenter {

    public SensorPresenter(SensorMvpInteractor interactor,CompositeDisposable disposable){
        super(interactor,disposable);
    }

    @Override
    public void viewIsReady() {
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
                        getView().startMeasureActivity(sensor.uid,sensor.getName(),sensor.getIcon(),sensor.getAddress())
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
