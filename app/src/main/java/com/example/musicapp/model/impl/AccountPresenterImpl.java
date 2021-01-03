package com.example.musicapp.model.impl;

import android.view.View;

import com.example.musicapp.model.AccountModel;
import com.example.musicapp.presenter.AccountPresenter;
import com.example.musicapp.view.AccountView;

public class AccountPresenterImpl implements AccountPresenter, AccountModel.Callback {
    AccountView mAccountView;
    AccountModel mAccountModel;

    public AccountPresenterImpl(AccountView mAccountView) {
        this.mAccountView = mAccountView;
        mAccountModel = new AccountModelImpl();
    }
    @Override
    public void onLogin() {
        mAccountView.navigateLogin();
    }

    @Override
    public void onRegister() {
        mAccountView.navigateRegister();
    }

    @Override
    public void onEmail() {

    }

    @Override
    public void onWechat() {

    }

    @Override
    public void onQQ() {

    }

    @Override
    public void onWeibo() {

    }

    @Override
    public void navigate(View view) {
        mAccountModel.navigate(view, this);
    }
}
