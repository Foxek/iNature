package com.foxek.inature.ui.measure;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.foxek.inature.R;
import com.foxek.inature.di.component.ActivityComponent;
import com.foxek.inature.ui.base.BaseFragment;
import com.foxek.inature.ui.sensors.CreateDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class EditDialog extends BaseFragment implements View.OnClickListener {

    @Inject
    MeasureMvpPresenter mPresenter;

    @BindView(R.id.name_edit_text)
    EditText mNameEditText;

    @BindView(R.id.error_hint)
    TextView mErrorHint;

    private Unbinder mBinder;

    public static EditDialog newInstance(String lastName) {
        EditDialog mEditDialog = new EditDialog();
        Bundle args = new Bundle();
        args.putString("last_name", lastName);
        mEditDialog.setArguments(args);
        return mEditDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            mBinder = ButterKnife.bind(this,view);
        }

        mNameEditText.setText(getArguments().getString("last_name"));
        getDialog().setCanceledOnTouchOutside(false);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinder.unbind();
    }

    @OnClick({R.id.delete_button,R.id.save_button})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_button:
                if (mNameEditText.getText().toString().isEmpty())
                    mErrorHint.setVisibility(View.VISIBLE);
                else {
                    mPresenter.renameSensor(mNameEditText.getText().toString());
                    dismiss();
                }
                break;
            case R.id.delete_button:
                dismiss();
                getBaseActivity().onBackPressed();
                mPresenter.deleteSensor();
                break;
        }
    }
}
