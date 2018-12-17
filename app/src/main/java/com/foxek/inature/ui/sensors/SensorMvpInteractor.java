package com.foxek.inature.ui.sensors;

import com.foxek.inature.data.database.model.Sensor;
import com.foxek.inature.ui.base.MvpInteractor;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

interface SensorMvpInteractor extends MvpInteractor {

    SensorAdapter createSensorListAdapter();

    Disposable scheduleListChanged();

    Observable<Sensor> onSensorItemClick();
}
