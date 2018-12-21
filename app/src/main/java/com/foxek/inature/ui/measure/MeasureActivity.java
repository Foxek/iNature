package com.foxek.inature.ui.measure;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.foxek.inature.R;
import com.foxek.inature.ui.base.BaseView;
import com.foxek.inature.ui.sensors.SensorActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.foxek.inature.common.Constants.MY_CAMERA_REQUEST_CODE;
import static com.foxek.inature.common.Constants.REQUEST_CODE_ASK_PERMISSIONS;
import static com.foxek.inature.common.Constants.REQUEST_ENABLE_BT;

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mPresenter.finishBluetooth();
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
    @Override
    protected void onStart() {
        super.onStart();
        requestPermission();
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat
                    .requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_ASK_PERMISSIONS);
        }else{
            mPresenter.startBluetooth();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (!(grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    finish();
                }else
                    mPresenter.startBluetooth();
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void bluetoothEnableRequest() {
        Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBTIntent, REQUEST_ENABLE_BT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_CANCELED) {
            mPresenter.bluetoothNotEnabled();
            return;
        }else{
            mPresenter.bluetoothEnabled();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showSnackBar(int message) {
        Snackbar.make(mMeasureContainer, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.bluetooth_request_button, snackbarOnClickListener)
                .show();
    }

    View.OnClickListener snackbarOnClickListener = view ->
            bluetoothEnableRequest();
}
