package com.foxek.inature.ui.sensors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.foxek.inature.R;
import com.foxek.inature.ui.base.BaseView;
import com.foxek.inature.ui.measure.MeasureActivity;
import com.foxek.inature.ui.preview.PreviewActivity;
import com.foxek.inature.ui.sensors.dialogs.CreateDialog;
import com.foxek.inature.ui.sensors.dialogs.StateDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SensorActivity extends BaseView implements SensorMvpView, View.OnClickListener {

    @Inject
    SensorMvpPresenter mPresenter;

    @BindView(R.id.sensors_list)
    RecyclerView mSensorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        ButterKnife.bind(this);

        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.viewIsReady();
    }

    @Override
    public void setSensorList(SensorAdapter adapter) {
        mSensorList.setLayoutManager(new LinearLayoutManager(this));
        mSensorList.setAdapter(adapter);
    }

    @Override
    public void startMeasureActivity(int uid, String name, String icon, String mac, String type)  {
        Intent intent = new Intent(this, MeasureActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("uid", uid);
        intent.putExtra("name", name);
        intent.putExtra("icon", icon);
        intent.putExtra("mac", mac);
        startActivity(intent);
    }

    @Override
    public void startPreviewActivity(String name, String mac) {
        Intent intent = new Intent(this, PreviewActivity.class);
        intent.putExtra("product_id", name);
        intent.putExtra("mac_address", mac);
        startActivity(intent);
        finish();
    }

    @Override
    public void startScanActivity() {
        startActivity(new Intent(this, ScanActivity.class));
        finish();
    }

    @Override
    public void showStateDialog(boolean state) {
        StateDialog mStateDialog = StateDialog.newInstance(state);
        mStateDialog.show(getSupportFragmentManager(), "stateDialog");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @OnClick(R.id.app_bar_add_button)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.app_bar_add_button:
                CreateDialog mCreateDialog = CreateDialog.newInstance();
                mCreateDialog.show(getSupportFragmentManager(), "createDialog");
                break;
        }
    }
}
