package com.foxek.inature.ui.base;

public abstract class BasePresenter <V extends MvpView,I extends MvpInteractor> implements MvpPresenter<V,I> {

    private V view;
    private I interactor;

    public BasePresenter(I mvpInteractor) {
        interactor = mvpInteractor;
    }

    @Override
    public void attachView(V mvpView) {
        view = mvpView;
    }

    @Override
    public void detachView() {
        view = null;
        interactor = null;
    }

    @Override
    public V getView() {
        return view;
    }

    @Override
    public I getInteractor() {
        return interactor;
    }

}
