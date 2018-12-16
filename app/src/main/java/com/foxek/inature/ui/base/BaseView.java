package com.foxek.inature.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.foxek.inature.common.BaseApplication;
import com.foxek.inature.di.component.ActivityComponent;
import com.foxek.inature.di.component.DaggerActivityComponent;
import com.foxek.inature.di.module.ActivityModule;

import static com.foxek.inature.common.Constants.EXTRA_ARGS;

public abstract class BaseView extends AppCompatActivity implements MvpView {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getIntent().getExtras();

        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(args))
                .applicationComponent(((BaseApplication) getApplication()).getComponent())
                .build();

    }

    protected ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }
}
