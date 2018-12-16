package com.foxek.inature.data.network;

import com.foxek.inature.di.NetworkHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class RemoteRepository {

    private ApiHelper mApiHelper;

    @Inject
    RemoteRepository(@NetworkHelper ApiHelper apiHelper){
        mApiHelper = apiHelper;
    }

    public Single<SensorResponse> getSensorInfo(String productId){
        return mApiHelper.getSensor(productId);
    }

    public Single<List<MeasureResponse>> getMeasureTemplate(String productId){
        return mApiHelper.getMeasure(productId);
    }

}
