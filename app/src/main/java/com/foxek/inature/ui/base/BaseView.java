package com.foxek.inature.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.foxek.inature.common.BaseApplication;
import com.foxek.inature.di.component.ActivityComponent;
import com.foxek.inature.di.component.DaggerActivityComponent;
import com.foxek.inature.di.module.ActivityModule;

public abstract class BaseView extends AppCompatActivity implements MvpView {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule())
                .applicationComponent(((BaseApplication) getApplication()).getComponent())
                .build();

    }

    protected ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }
}
