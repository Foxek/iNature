package com.foxek.inature.ui.measure;

import android.net.wifi.ScanResult;
import android.os.Handler;
import android.os.ParcelUuid;

import com.foxek.inature.data.BluetoothHelper;
import com.foxek.inature.data.database.LocalRepository;
import com.foxek.inature.data.database.model.Measure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.foxek.inature.common.Constants.SENSOR_DATA_UUID;

public class MeasureInteractor implements MeasureMvpInteractor{

    private LocalRepository     mRepository;
    private MeasureAdapter      mMeasureAdapter;
    private BluetoothHelper     mBluetoothHelper;

    @Inject
    MeasureInteractor(LocalRepository localRepository, BluetoothHelper bluetoothHelper){
        mRepository = localRepository;
        mBluetoothHelper = bluetoothHelper;
    }

    @Override
    public MeasureAdapter createSensorListAdapter(){
        List<Measure> measures = new ArrayList<>();
        mMeasureAdapter = new MeasureAdapter(measures);
        return mMeasureAdapter;
    }

    @Override
    public Disposable scheduleListChanged(int sensorId){
        return mRepository.getAllMeasure(sensorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(measures -> mMeasureAdapter.addAllMeasures(measures), throwable -> {});
    }

    @Override
    public void deleteSensor(int id){
        mRepository.deleteSensor(id);
    }

    @Override
    public void renameSensor(int id, String name) {
        mRepository.renameSensor(id,name);
    }

    @Override
    public boolean prepareBluetooth() {
        return mBluetoothHelper.initialize();
    }

    @Override
    public void bluetoothStartScanning(String type, String mac) {
        mBluetoothHelper.startScanning(type,mac);
    }

    @Override
    public void bluetoothStopScanning() {
        mBluetoothHelper.stopScanning();
        mBluetoothHelper.close();

    }

    @Override
    public Observable<Boolean> onBluetoothDataChanged() {
        return mBluetoothHelper.getScanResult()
                .map(result -> {
                    byte[] serviceData =  result.getScanRecord().getServiceData(SENSOR_DATA_UUID);
                    if (serviceData != null)
                        mMeasureAdapter.parseAdvertisingData(serviceData);
                    return true;
                });
    }


}
