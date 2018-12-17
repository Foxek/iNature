package com.foxek.inature.ui.preview;

import com.foxek.inature.ui.base.MvpView;

public interface PreviewMvpView extends MvpView {

    void setSensorPreview(String type, String desc, String icon);

    void startSensorActivity();

    void showError();

    void showErrorHint(int message);

    void hideProgressBackground();

    void showProgressBackground();
}
