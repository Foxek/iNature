package com.foxek.inature.ui.measure;

import android.bluetooth.le.ScanResult;
import android.os.Handler;
import android.os.ParcelUuid;

import com.foxek.inature.data.BluetoothHelper;
import com.foxek.inature.data.database.LocalRepository;
import com.foxek.inature.data.database.model.Measure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MeasureInteractor implements MeasureMvpInteractor{

    private LocalRepository     mRepository;
    private MeasureAdapter      mMeasureAdapter;
    private BluetoothHelper     mBluetoothHelper;
    private Handler             mAvailableHandler;

    @Inject
    MeasureInteractor(LocalRepository localRepository, BluetoothHelper bluetoothHelper){
        mRepository = localRepository;
        mBluetoothHelper = bluetoothHelper;

        mAvailableHandler = new Handler();
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
    public void bluetoothStartScanning(String type) {
        mBluetoothHelper.startScanning(type);
    }

    @Override
    public void bluetoothStopScanning() {
        mBluetoothHelper.stopScanning();
        mBluetoothHelper.close();

        if (mAvailableHandler != null) {
            mAvailableHandler.removeCallbacksAndMessages(null);
            mAvailableHandler = null;
        }
    }

    @Override
    public Disposable onBluetoothDataChanged(String mac) {
        return mBluetoothHelper.getScanResult()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result.getDevice().getAddress().equals("79:17:A6:C5:CD:49")) {
                        mAvailableHandler.removeCallbacksAndMessages(null);
                        Map<ParcelUuid, byte[]> map = result.getScanRecord().getServiceData();
                        if (map != null) {
                            Set<Map.Entry<ParcelUuid, byte[]>> set = map.entrySet();
                            for (Map.Entry<ParcelUuid, byte[]> aSet : set)
                                mMeasureAdapter.parseAdvertisingData(aSet.getValue());
                        }
                        mAvailableHandler.postDelayed(()->{
//                            getView().setSearchStatus(R.string.bluetooth_search,R.string.bluetooth_search_desc);
                            mAvailableHandler.removeCallbacksAndMessages(null);
                        }, 10000);
                    }
                }, throwable -> {});
    }


}
