package com.example.musicapp.presenter.impl;

import com.example.musicapp.model.SplashModel;
import com.example.musicapp.model.impl.SplashModelImpl;
import com.example.musicapp.presenter.SplashPresenter;
import com.example.musicapp.view.SplashView;

public class SplashPresenterImpl implements SplashPresenter, SplashModel.SplashCallback {
    private SplashView mSplashView;
    private SplashModel mSplashModel;
    public SplashPresenterImpl(SplashView mSplashView) {
        this.mSplashView = mSplashView;
        mSplashModel = new SplashModelImpl();
    }

    @Override
    public void navigate() {
        mSplashModel.checkLogin(this);
    }

    @Override
    public void navigateMain() {
        mSplashView.navigateMain();
    }

    @Override
    public void navigateLogin() {
        mSplashView.navigateLogin();
    }

    @Override
    public void navigateAccount() {
        mSplashView.navigateAccount();
    }
}
