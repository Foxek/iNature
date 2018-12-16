package com.foxek.inature.ui.preview;

import com.foxek.inature.ui.base.BasePresenter;


public class PreviewPresenter extends BasePresenter<PreviewMvpView,PreviewMvpInteractor> implements PreviewMvpPresenter {

    public PreviewPresenter(PreviewMvpInteractor interactor){
        super(interactor);
    }

    @Override
    public void viewIsReady() {

    }
}
