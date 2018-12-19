package com.foxek.inature.ui.measure;

import android.os.Bundle;

import com.foxek.inature.R;

import com.foxek.inature.ui.base.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;

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

        if (getInteractor().prepareBluetooth())
            bluetoothEnabled();
        else
            getView().bluetoothEnableRequest();
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
        getInteractor().bluetoothStartScanning("Pixel");
        getDisposable().add(getInteractor().onBluetoothDataChanged(args.getString("mac")));
    }

    @Override
    public void finishBluetooth() {
        getInteractor().bluetoothStopScanning();
    }
}
