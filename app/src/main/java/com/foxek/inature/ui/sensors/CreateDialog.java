package com.foxek.inature.ui.sensors;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.foxek.inature.R;
import com.foxek.inature.di.component.ActivityComponent;
import com.foxek.inature.ui.base.BaseFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.foxek.inature.common.Constants.MAC_PATTERN;

public class CreateDialog extends BaseFragment implements View.OnClickListener {

    @Inject
    SensorMvpPresenter mPresenter;

    @BindView(R.id.sensor_mac_edit_text)
    EditText mMacEditText;

    @BindView(R.id.sensor_name_edit_text)
    EditText mNameEditText;

    @BindView(R.id.error_hint)
    TextView mErrorHint;

    private Unbinder mBinder;

    public static CreateDialog newInstance() {
        return new CreateDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_create, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            mBinder = ButterKnife.bind(this,view);
        }

        mNameEditText.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (keyEvent != null&& (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) || (i == EditorInfo.IME_ACTION_DONE)) {
                mMacEditText.requestFocus();
            }
            return false;
        });

        getDialog().setCanceledOnTouchOutside(false);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinder.unbind();
    }

    private boolean validateMacAddress(String address){
        if (address.isEmpty()) return false;
        Pattern pattern = Pattern.compile(MAC_PATTERN);
        Matcher matcher = pattern.matcher(address);
        return matcher.find();
    }

    private void validateFiled(){
        if (mNameEditText.getText().toString().isEmpty()){
            mErrorHint.setVisibility(View.VISIBLE);
            mErrorHint.setText(R.string.sensor_edit_dialog_error);
        }else if (!validateMacAddress(mMacEditText.getText().toString())){
            mErrorHint.setVisibility(View.VISIBLE);
            mErrorHint.setText(R.string.sensor_mac_format);
            mMacEditText.setText(null);
        }else{
            mNameEditText.setCursorVisible(false);
            mMacEditText.setCursorVisible(false);
            dismiss();
            mPresenter.addButtonPressed(mNameEditText.getText().toString(),mMacEditText.getText().toString());
        }
    }

    @OnClick({R.id.scan_button,R.id.add_button})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scan_button:
                mPresenter.scanButtonPressed();
                dismiss();
                break;
            case R.id.add_button:
                validateFiled();
                break;
        }
    }
}
