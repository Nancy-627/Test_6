package com.example.musicapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.musicapp.R;
import com.example.musicapp.model.impl.AccountPresenterImpl;
import com.example.musicapp.presenter.AccountPresenter;
import com.example.musicapp.view.AccountView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountActivity extends AppCompatActivity implements AccountView {


    AccountPresenter mAccountPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);
        ButterKnife.bind(this);
        setUpMVP();
    }

    @OnClick ({R.id.btn_mobile_login, R.id.btn_register})
    public void loginClick(View view) {mAccountPresenter.navigate(view);}

    @Override
    public void navigateLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void navigateRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void setUpMVP() {
        mAccountPresenter = new AccountPresenterImpl(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}