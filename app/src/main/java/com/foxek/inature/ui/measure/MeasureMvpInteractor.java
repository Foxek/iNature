package com.foxek.inature.ui.measure;


import android.net.wifi.ScanResult;

import com.foxek.inature.ui.base.MvpInteractor;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public interface MeasureMvpInteractor extends MvpInteractor {

    MeasureAdapter createSensorListAdapter();

    Disposable scheduleListChanged(int sensorId);

    void deleteSensor(int id);

    void renameSensor(int id, String name);

    boolean prepareBluetooth();

    void bluetoothStartScanning(String type, String mac);

    void bluetoothStopScanning();

    Observable<Boolean> onBluetoothDataChanged();
}
