package com.foxek.inature.ui.preview;

import android.os.Bundle;

import com.foxek.inature.ui.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class PreviewPresenter extends BasePresenter<PreviewMvpView,PreviewMvpInteractor> implements PreviewMvpPresenter {

    private CompositeDisposable mDisposable;
    private Bundle args;

    public PreviewPresenter(PreviewMvpInteractor interactor, Bundle args){
        super(interactor);
        mDisposable = new CompositeDisposable();
        this.args = args;
    }

    @Override
    public void viewIsReady() {
        getSensorInfo(args.getString("product_id"),args.getString("mac_address"));
    }

    @Override
    public void detachView() {
        super.detachView();
        mDisposable.dispose();
    }

    private void getSensorInfo(String productId, String macAddress){
        mDisposable.add(getInteractor().getDefaultSensorInfo(productId,macAddress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sensorResponse ->{
                    getView().setSensorPreview(sensorResponse.getType(),sensorResponse.getDescription(),sensorResponse.getIcon());
                    getView().hideProgressBackground();
                    }, throwable -> getView().showError()));
    }

    @Override
    public void createNewSensor(String name){
        getView().showProgressBackground();
        mDisposable.add(getInteractor().createNewSensor(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getView().startSensorActivity(), throwable -> getView().showError()));
    }

}
