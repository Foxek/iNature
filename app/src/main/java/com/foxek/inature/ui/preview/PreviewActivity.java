package com.foxek.inature.ui.preview;

import android.os.Bundle;

import com.foxek.inature.R;
import com.foxek.inature.ui.base.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class PreviewActivity extends BaseView implements PreviewMvpView {

    @Inject
    PreviewMvpPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        ButterKnife.bind(this);

        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.viewIsReady();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
