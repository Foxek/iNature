package com.foxek.inature.ui.preview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.foxek.inature.R;
import com.foxek.inature.common.Constants;
import com.foxek.inature.ui.base.BaseView;
import com.foxek.inature.ui.sensors.SensorActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PreviewActivity extends BaseView implements PreviewMvpView, View.OnClickListener {

    @Inject
    PreviewMvpPresenter mPresenter;

    @BindView(R.id.sensor_name)
    TextView mSensorType;

    @BindView(R.id.app_bar_title)
    TextView mAppBarTitle;

    @BindView(R.id.sensor_image)
    ImageView mSensorLogo;

    @BindView(R.id.sensor_desc)
    TextView mSensorDesc;

    @BindView(R.id.sensor_edit_text)
    EditText mSensorEditText;

    @BindView(R.id.add_button)
    Button mAddButton;

    @BindView(R.id.progress_background)
    View mProgressBackground;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @BindView(R.id.error_hint)
    TextView mErrorHint;

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

    @Override
    public void setSensorPreview(String type, String desc, String icon) {
        mSensorType.setText(type);
        mSensorDesc.setText(desc);
        mSensorLogo.setImageResource(getResources()
                .getIdentifier("com.foxek.inature:drawable/"+ icon,null,null));
    }

    @Override
    public void hideProgressBackground() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mProgressBackground.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgressBackground() {
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBackground.setVisibility(View.VISIBLE);
    }

    @Override
    public void startSensorActivity(boolean state) {
        Intent intent = new Intent(this, SensorActivity.class);
        intent.putExtra(Constants.CREATE_STATE, state);
        startActivity(intent);
    }

    @Override
    public void showErrorHint(int message) {
        mErrorHint.setVisibility(View.VISIBLE);
        mErrorHint.setText(message);
    }

    @OnClick(R.id.add_button)
    @Override
    public void onClick(View v) {
        if (!mSensorEditText.getText().toString().isEmpty()) {
            mPresenter.createNewSensor(mSensorEditText.getText().toString());
            mErrorHint.setVisibility(View.GONE);
        }
        else
            showErrorHint(R.string.sensor_edit_dialog_error);

    }
}
