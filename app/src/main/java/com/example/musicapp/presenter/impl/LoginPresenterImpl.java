package com.example.musicapp.presenter.impl;

import com.example.musicapp.model.LoginModel;
import com.example.musicapp.model.entities.User;
import com.example.musicapp.model.impl.LoginModelImpl;
import com.example.musicapp.network.MusicAppService;
import com.example.musicapp.presenter.LoginPresenter;
import com.example.musicapp.response.login.LoginResponse;
import com.example.musicapp.util.TagUtils;
import com.example.musicapp.view.LoginVIew;

import rx.subscriptions.CompositeSubscription;

public class LoginPresenterImpl implements LoginPresenter, LoginModel.LoginCallback {
    private String TAG = TagUtils.getTag(this.getClass());
    private LoginVIew mLoginView;
    private LoginModel mLoginModel;
    //    private NetEaseMusicService mNetEaseMusicService;
    private CompositeSubscription subscriptions;


    public LoginPresenterImpl(MusicAppService netEaseMusicService, LoginVIew mLoginView) {
//        this.mNetEaseMusicService = netEaseMusicService;
        this.mLoginView = mLoginView;
        this.mLoginModel = new LoginModelImpl(netEaseMusicService);
    }

    @Override
    public void validateCredentials(User user) {
        if (mLoginView != null) {
            mLoginView.showProgress();
            mLoginView.hideKeyboard();
        }
        mLoginModel.login(user, this );
    }

    @Override
    public void onUsernameError(String errorMsg) {
        mLoginView.hideProgress();
        mLoginView.usernameError(errorMsg);
    }

    @Override
    public void onPasswordError(String errorMsg) {
        mLoginView.hideProgress();
        mLoginView.passwordError(errorMsg);
    }

    @Override
    public void onSuccess(LoginResponse response) {
        mLoginView.hideProgress();
        mLoginView.loginSuccess(response);
    }

}
