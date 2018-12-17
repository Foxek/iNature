package com.foxek.inature.data.network;

import com.foxek.inature.data.network.model.SensorResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiHelper {

    @GET("{product_id}")
    Single<SensorResponse> getSensor(@Path("product_id")String id);

//    @GET("{product_id}/Measure.json")
//    Single<List<MeasureResponse>> getMeasure(@Path("product_id") String id);
}
