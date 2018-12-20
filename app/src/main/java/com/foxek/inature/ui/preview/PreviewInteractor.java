package com.foxek.inature.ui.preview;

import com.foxek.inature.data.database.LocalRepository;
import com.foxek.inature.data.database.model.Measure;
import com.foxek.inature.data.database.model.Sensor;
import com.foxek.inature.data.network.RemoteRepository;
import com.foxek.inature.data.network.model.MeasureMeta;
import com.foxek.inature.data.network.model.SensorResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class PreviewInteractor implements PreviewMvpInteractor {

    private LocalRepository         mLocalRepository;
    private RemoteRepository        mRemoteRepository;
    private List<Measure>           mMeasures;
    private Sensor                  mSensor;

    @Inject
    PreviewInteractor(RemoteRepository remoteRepository, LocalRepository localRepository){
        mLocalRepository = localRepository;
        mRemoteRepository = remoteRepository;
        mSensor = new Sensor();
        mMeasures = new ArrayList<>();
    }

    @Override
    public Single<SensorResponse> getDefaultSensorInfo(String productId, String macAddress){
        mSensor.setAddress(macAddress);
        mSensor.setType(productId);
        return mRemoteRepository.getSensorInfo(productId + ".json")
                .map(sensorResponse -> {
                    mSensor.setIcon(sensorResponse.getIcon());
                    mSensor.setDescription(sensorResponse.getDescription());
                    for (MeasureMeta metaInfo : sensorResponse.getMeasureMetaInfo()){
                        mMeasures.add(new Measure(metaInfo.getName(),metaInfo.getIcon()));
                    }
                    return sensorResponse;
                });
    }

    @Override
    public Completable createNewSensor(String name){
        mSensor.setName(name);

        return mLocalRepository.getMaxPrimaryKey()
                .map(primaryKey -> {
                    mSensor.uid = primaryKey + 1;
                    return primaryKey;
                })
                .toCompletable()
                .andThen(Completable.defer (() -> mLocalRepository.createSensor(mSensor)))
                .andThen(Completable.defer (() -> mLocalRepository.createMeasures(mSensor.uid,mMeasures)));

    }
}
