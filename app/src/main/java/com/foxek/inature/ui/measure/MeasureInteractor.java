package com.foxek.inature.ui.measure;

import com.foxek.inature.data.database.LocalRepository;
import com.foxek.inature.data.database.model.Measure;
import com.foxek.inature.data.database.model.Sensor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MeasureInteractor implements MeasureMvpInteractor{

    private LocalRepository     mRepository;
    private MeasureAdapter      mMeasureAdapter;

    @Inject
    MeasureInteractor(LocalRepository localRepository){
        mRepository = localRepository;
    }

    @Override
    public MeasureAdapter createSensorListAdapter(){
        List<Measure> measures = new ArrayList<>();
        mMeasureAdapter = new MeasureAdapter(measures);
        return mMeasureAdapter;
    }

    @Override
    public Disposable scheduleListChanged(int sensorId){
        return mRepository.getAllMeasure(sensorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(measures -> mMeasureAdapter.addAllMeasures(measures), throwable -> {});
    }

    @Override
    public void deleteSensor(int id){
        mRepository.deleteSensor(id);
    }

    @Override
    public void renameSensor(int id, String name) {
        mRepository.renameSensor(id,name);
    }
}
