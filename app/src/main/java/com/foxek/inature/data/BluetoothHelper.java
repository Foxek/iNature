package com.foxek.inature.data;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;

import com.foxek.inature.di.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class BluetoothHelper {

    private Context                     mContext;
    private BluetoothAdapter            mBluetoothAdapter;
    private BluetoothGatt               mBluetoothGatt;
    private BluetoothLeScanner          mBluetoothScanner;
    public boolean                      mScanState = false;
    private String                      mMacAddress;

    private PublishSubject<ScanResult> scanResultSubject;

    @Inject
    BluetoothHelper(@ApplicationContext Context context){
        mContext = context;
        scanResultSubject = PublishSubject.create();
    }

    public Observable<ScanResult> getScanResult(){
        return scanResultSubject;
    }

    public boolean initialize() {
        BluetoothManager mBluetoothManager = (BluetoothManager) mContext.getSystemService(Context.BLUETOOTH_SERVICE);
        if (mBluetoothManager == null) {
            return false;
        }
        mBluetoothAdapter = mBluetoothManager.getAdapter();

        return checkBluetoothEnabled();
    }

    private boolean checkBluetoothEnabled(){
        return mBluetoothAdapter != null && mBluetoothAdapter.isEnabled();
    }

    public void close() {
        if (mBluetoothGatt == null) {
            return;
        }
        mBluetoothGatt.close();
        mBluetoothGatt = null;
    }

    public void startScanning(String device, String mac) {
        mMacAddress = mac;
        mBluetoothScanner = mBluetoothAdapter.getBluetoothLeScanner();
        ScanFilter scanFilter = new ScanFilter.Builder()
                .setDeviceName(device)
                .build();
        List<ScanFilter> filters = new ArrayList<>();
        filters.add(scanFilter);
        ScanSettings settings = new ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                .build();
        mBluetoothScanner.startScan(filters,settings,mScanCallback);
        mScanState = true;
    }

    public void stopScanning() {
        if (mScanState) {
            mBluetoothScanner.stopScan(mScanCallback);
            mBluetoothScanner.flushPendingScanResults(mScanCallback);
            mScanState = false;
        }

    }

    private ScanCallback mScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            if (result.getDevice().getAddress().equals(mMacAddress))
                scanResultSubject.onNext(result);
        }
    };
}
//7b e8 01 01 ad cf