package com.foxek.inature.ui.measure;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.foxek.inature.R;
import com.foxek.inature.ui.base.BaseView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeasureActivity extends BaseView implements MeasureMvpView, View.OnClickListener{

    @Inject
    MeasureMvpPresenter mPresenter;

    @BindView(R.id.app_bar_title)
    TextView mAppTitle;

    @BindView(R.id.available_value)
    TextView mAvailableValue;

    @BindView(R.id.available_desc)
    TextView mAvailableDesc;

    @BindView(R.id.sensor_logo)
    ImageView mSensorLogo;

    @BindView(R.id.measure_list)
    RecyclerView mMeasureList;

    @BindView(R.id.measure_container)
    NestedScrollView mMeasureContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);
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

    @Override
    public void setMeasureList(MeasureAdapter adapter) {
        mMeasureList.setLayoutManager(new LinearLayoutManager(this));
        mMeasureList.setAdapter(adapter);
        mMeasureList.setNestedScrollingEnabled(false);
    }

    @Override
    public void setSearchStatus(int name, int desc) {
        mAvailableValue.setText(name);
        mAvailableDesc.setText(desc);
    }

    @Override
    public void setSensorName(String name) {
        mAppTitle.setText(name);
    }

    @Override
    public void showEditDialog(String name) {
        EditDialog mEditDialog = EditDialog.newInstance(name);
        mEditDialog.show(getSupportFragmentManager(), "edit_dialog");
    }

    @Override
    public void showSensorPreview(String name, String icon) {
        mSensorLogo.setImageResource(getResources()
                .getIdentifier("com.foxek.inature:drawable/" + icon,null,null));
        mAppTitle.setText(name);
    }

    @OnClick({R.id.app_bar_back_button, R.id.app_bar_edit_button})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.app_bar_back_button:
                onBackPressed();
                break;
            case R.id.app_bar_edit_button:
                mPresenter.editButtonPressed();
                break;
        }
    }
}
