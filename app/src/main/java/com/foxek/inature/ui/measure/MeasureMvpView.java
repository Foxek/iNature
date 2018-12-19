package com.foxek.inature.ui.measure;

import com.foxek.inature.ui.base.MvpView;

public interface MeasureMvpView extends MvpView {

    void setMeasureList(MeasureAdapter adapter);

    void showSensorPreview(String name, String icon);

    void setSearchStatus(int name, int desc);

    void setSensorName(String name);

    void showEditDialog(String name);

    void bluetoothEnableRequest();

    void showSnackBar(int message);
}
