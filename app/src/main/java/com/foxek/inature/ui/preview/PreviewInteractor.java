package com.foxek.inature.ui.preview;

import com.foxek.inature.data.model.Sensor;
import com.foxek.inature.data.model.SensorRepository;
import com.foxek.inature.data.network.MeasureResponse;
import com.foxek.inature.data.network.RemoteRepository;
import com.foxek.inature.data.network.SensorResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PreviewInteractor implements PreviewMvpInteractor {

    private SensorRepository        mSensorRepository;
    private RemoteRepository        mRemoteRepository;
    private SensorResponse          mSensorInfo;
    private List<MeasureResponse>   mMeasureTemplate;
    private Sensor                  mSensor;

    @Inject
    PreviewInteractor(RemoteRepository remoteRepository, SensorRepository sensorRepository){
        mSensorRepository = sensorRepository;
        mRemoteRepository = remoteRepository;
        mSensor = new Sensor();
    }

    public Single<SensorResponse> getDefaultSensorInfo(String productId, String macAddress){
        mSensor.setAddress(macAddress);
        return mRemoteRepository.getSensorInfo(productId + ".json")
                .map(sensorInfo -> {
                    mSensor.setIcon(sensorInfo.getIcon());
                    mSensor.setType(sensorInfo.getType());
                    return sensorInfo;
                });
    }

    @Override
    public Disposable getMeasureTemplate(String productId) {
        return mRemoteRepository.getMeasureTemplate(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(measures -> mMeasureTemplate = measures, throwable -> {});
    }
}
