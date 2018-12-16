package com.foxek.inature.ui.sensors;

import com.foxek.inature.ui.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class SensorPresenter extends BasePresenter<SensorMvpView,SensorMvpInteractor> implements SensorMvpPresenter {

    private CompositeDisposable     mDisposable;
    private SensorMvpInteractor     mInteractor;

    public SensorPresenter(SensorMvpInteractor interactor){
        super(interactor);
        mInteractor = interactor;
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void viewIsReady() {
        createSensorListAdapter();
    }

    @Override
    public void detachView() {
        super.detachView();
        mDisposable.dispose();
        mInteractor = null;
    }

    private void createSensorListAdapter (){
        getView().setSensorList(mInteractor.createSensorListAdapter());
        registerItemCallback();

        mDisposable.add(mInteractor.scheduleListChanged());
    }

    private void registerItemCallback() {
        mDisposable.add(mInteractor.onSensorItemClick()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sensor -> getView().startMeasureActivity(sensor.uid,sensor.getName(),sensor.getIcon()), throwable -> {}));

    }

    @Override
    public void addButtonPressed(String name, String mac) {
        getView().startPreviewActivity(name,mac);
    }

    @Override
    public void scanButtonPressed() {
        getView().startScanActivity();
    }
}
