package com.foxek.inature.ui.base;

import android.content.Context;
import androidx.core.app.DialogFragment;

import com.foxek.inature.di.component.ActivityComponent;

public class BaseFragment extends DialogFragment implements MvpView {

    private BaseView    mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseView) {
            mActivity = (BaseView) context;
        }
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }
    public BaseView getBaseActivity() {
        return mActivity;
    }

    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }
}
