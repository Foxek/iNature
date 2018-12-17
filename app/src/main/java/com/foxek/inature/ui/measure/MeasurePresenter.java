package com.foxek.inature.ui.measure;

import android.os.Bundle;

import com.foxek.inature.R;
import com.foxek.inature.ui.base.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;

public class MeasurePresenter extends BasePresenter<MeasureMvpView,MeasureMvpInteractor> implements MeasureMvpPresenter {

    private CompositeDisposable mDisposable;
    private Bundle args;

    public MeasurePresenter(MeasureMvpInteractor interactor, Bundle args){
        super(interactor);
        mDisposable = new CompositeDisposable();
        this.args = args;
    }

    @Override
    public void detachView() {
        super.detachView();
        mDisposable.dispose();
    }

    @Override
    public void viewIsReady() {
        createMeasureListAdapter();
        getView().showSensorPreview(args.getString("name"), args.getString("icon"));
        getView().setSearchStatus(R.string.bluetooth_enabled,R.string.bluetooth_enabled_desc);
    }

    private void createMeasureListAdapter (){
        getView().setMeasureList(getInteractor().createSensorListAdapter());
        mDisposable.add(getInteractor().scheduleListChanged(args.getInt("uid")));
    }


    @Override
    public void deleteSensor() {
        getInteractor().deleteSensor(args.getInt("uid"));
    }

    @Override
    public void renameSensor(String name) {
        getInteractor().renameSensor(args.getInt("uid"),name);
        getView().setSensorName(name);
    }

    @Override
    public void editButtonPressed() {
        getView().showEditDialog(args.getString("name"));
    }
}
