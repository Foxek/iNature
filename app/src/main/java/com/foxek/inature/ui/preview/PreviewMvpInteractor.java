package com.foxek.inature.ui.preview;

import com.foxek.inature.data.network.SensorResponse;
import com.foxek.inature.ui.base.MvpInteractor;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

public interface PreviewMvpInteractor extends MvpInteractor {

    Single<SensorResponse> getDefaultSensorInfo(String productId,String macAddress);

    Disposable getMeasureTemplate(String productId);
}
