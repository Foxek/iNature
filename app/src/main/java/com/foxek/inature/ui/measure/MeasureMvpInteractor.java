package com.foxek.inature.ui.measure;


import com.foxek.inature.ui.base.MvpInteractor;

import io.reactivex.Completable;
import io.reactivex.disposables.Disposable;

public interface MeasureMvpInteractor extends MvpInteractor {

    MeasureAdapter createSensorListAdapter();

    Disposable scheduleListChanged(int sensorId);

    void deleteSensor(int id);

    void renameSensor(int id, String name);

    boolean prepareBluetooth();

    void bluetoothStartScanning(String type);

    void bluetoothStopScanning();

    Disposable onBluetoothDataChanged(String mac);
}
