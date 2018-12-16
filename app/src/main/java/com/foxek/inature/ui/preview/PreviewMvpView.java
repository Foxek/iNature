package com.foxek.inature.ui.preview;

import com.foxek.inature.ui.base.MvpView;

public interface PreviewMvpView extends MvpView {

    void setSensorPreview(String type, String desc, String icon);
}
