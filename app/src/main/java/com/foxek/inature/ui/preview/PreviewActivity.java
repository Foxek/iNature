package com.foxek.inature.ui.preview;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.foxek.inature.R;
import com.foxek.inature.ui.base.BaseView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreviewActivity extends BaseView implements PreviewMvpView {

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

    @BindView(R.id.sensor_edit_layout)
    EditText mSensorEditText;

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
}
