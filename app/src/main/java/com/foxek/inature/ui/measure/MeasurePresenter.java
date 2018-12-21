package com.foxek.inature.ui.measure;

import android.os.Bundle;

import com.foxek.inature.R;

import com.foxek.inature.ui.base.BasePresenter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MeasurePresenter extends BasePresenter<MeasureMvpView,MeasureMvpInteractor> implements MeasureMvpPresenter {

    private Bundle args;

    public MeasurePresenter(MeasureMvpInteractor interactor, CompositeDisposable disposable, Bundle args){
        super(interactor,disposable);
        this.args = args;
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void viewIsReady() {
        createMeasureListAdapter();
        getView().showSensorPreview(args.getString("name"), args.getString("icon"));
    }

    private void createMeasureListAdapter (){
        getView().setMeasureList(getInteractor().createSensorListAdapter());
        getDisposable().add(getInteractor().scheduleListChanged(args.getInt("uid")));
    }

    @Override
    public void deleteSensor() {
        getInteractor().deleteSensor(args.getInt("uid"));
    }

    @Override
    public void renameSensor(String name) {
        getInteractor().renameSensor(args.getInt("uid"),name);
        getView().setSensorName(name);
    }

    @Override
    public void editButtonPressed() {
        getView().showEditDialog(args.getString("name"));
    }

    @Override
    public void bluetoothNotEnabled(){
        getView().setSearchStatus(R.string.bluetooth_enabled,R.string.bluetooth_enabled_desc);
        getView().showSnackBar(R.string.bluetooth_request);
    }

    @Override
    public void bluetoothEnabled(){
        getView().setSearchStatus(R.string.bluetooth_search,R.string.bluetooth_search_desc);
        getInteractor().bluetoothStartScanning(args.getString("type"),args.getString("mac"));
        controlBluetoothConnection();
    }

    private void controlBluetoothConnection(){
        getDisposable().add(getInteractor().onBluetoothDataChanged()
                .timeout(15, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(error -> getView().setSearchStatus(R.string.bluetooth_search,R.string.bluetooth_search_desc))
                .retry()
                .subscribe(result -> getView().setSearchStatus(R.string.bluetooth_available,R.string.bluetooth_available_desc)
                        , throwable -> {}));
    }

    @Override
    public void finishBluetooth() {
        getInteractor().bluetoothStopScanning();
    }

    @Override
    public void startBluetooth() {
        if (getInteractor().prepareBluetooth())
            bluetoothEnabled();
        else
            getView().bluetoothEnableRequest();
    }
}
