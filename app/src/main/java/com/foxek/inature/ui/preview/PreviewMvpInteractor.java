package com.foxek.inature.ui.preview;

import com.foxek.inature.data.network.model.SensorResponse;
import com.foxek.inature.ui.base.MvpInteractor;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface PreviewMvpInteractor extends MvpInteractor {

    Single<SensorResponse> getDefaultSensorInfo(String productId,String macAddress);

    Completable createNewSensor(String name);
}
