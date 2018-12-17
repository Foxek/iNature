package com.foxek.inature.ui.sensors;

import com.foxek.inature.data.database.LocalRepository;
import com.foxek.inature.data.database.model.Sensor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SensorInteractor implements SensorMvpInteractor{

    private LocalRepository mLocalRepository;
    private SensorAdapter       mSensorAdapter;

    @Inject
    SensorInteractor(LocalRepository localRepository){
        mLocalRepository = localRepository;
    }

    @Override
    public SensorAdapter createSensorListAdapter(){
        List<Sensor> sensors = new ArrayList<>();
        mSensorAdapter = new SensorAdapter(sensors);
        return mSensorAdapter;
    }

    @Override
    public Disposable scheduleListChanged(){
        return mLocalRepository.getAllSensors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sensors -> mSensorAdapter.addAllSensors(sensors), throwable -> {});
    }

    @Override
    public Observable<Sensor> onSensorItemClick() {
        return mSensorAdapter.getPositionClicks();
    }
}
