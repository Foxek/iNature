package com.foxek.inature.ui.sensors;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.foxek.inature.R;
import com.foxek.inature.ui.base.BaseFragment;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StateDialog extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.dialog_logo)
    ImageView mDialogLogo;

    @BindView(R.id.dialog_title)
    TextView mDialogTitle;

    @BindView(R.id.dialog_description)
    TextView mDialogDesc;

    private Unbinder mBinder;

    public static StateDialog newInstance(boolean state) {
        StateDialog mStateDialog = new StateDialog();
        Bundle args = new Bundle();
        args.putBoolean("state", state);
        mStateDialog.setArguments(args);

        return mStateDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CustomInfoDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_state, container, false);
        mBinder = ButterKnife.bind(this,view);

        if (getArguments().getBoolean("state")){
            mDialogLogo.setImageResource(R.drawable.ic_sensor_no_exist_logo);
            mDialogTitle.setText(R.string.preview_success_title);
            mDialogDesc.setText(R.string.preview_success_desc);
        }else{
            mDialogLogo.setImageResource(R.drawable.ic_sensor_no_exist_logo);
            mDialogTitle.setText(R.string.preview_error_title);
            mDialogDesc.setText(R.string.preview_error_desc);
        }

        getDialog().setCanceledOnTouchOutside(false);

        new Handler().postDelayed(() -> {
            if (getDialog().isShowing()){
                getDialog().dismiss();
            }
        }, 2000);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinder.unbind();
    }

    @Override
    public void onClick(View v) {
    }
}