package com.foxek.inature.data.network;

import com.foxek.inature.data.network.model.SensorResponse;
import com.foxek.inature.di.NetworkHelper;

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
}
