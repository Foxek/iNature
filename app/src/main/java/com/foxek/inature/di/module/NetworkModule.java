package com.foxek.inature.di.module;

import com.foxek.inature.data.network.ApiHelper;
import com.foxek.inature.di.NetworkHelper;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.foxek.inature.common.Constants.DATABASE_URL;

@Module
public class NetworkModule {

    Retrofit    mRetrofit;

    public NetworkModule(){
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(DATABASE_URL)
                .build();
    }

    @Provides
    @NetworkHelper
    ApiHelper provideApiHelper(){
        return mRetrofit.create(ApiHelper.class);
    }

}
