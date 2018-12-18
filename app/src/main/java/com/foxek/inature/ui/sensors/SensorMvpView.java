package com.foxek.inature.ui.sensors;

import com.foxek.inature.ui.base.MvpView;

interface SensorMvpView extends MvpView {

    void setSensorList(SensorAdapter adapter);

    void startMeasureActivity(int uid, String name, String icon, String mac);

    void startPreviewActivity(String name, String mac);

    void startScanActivity();

    void showStateDialog(boolean state);
}
