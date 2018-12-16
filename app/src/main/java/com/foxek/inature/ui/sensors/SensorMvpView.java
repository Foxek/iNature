package com.foxek.inature.ui.sensors;

import com.foxek.inature.ui.base.MvpView;

interface SensorMvpView extends MvpView {

    void setSensorList(SensorAdapter adapter);

    void startMeasureActivity(int uid, String name, String icon);

    void startPreviewActivity(String name, String mac);

    void startScanActivity();


}
