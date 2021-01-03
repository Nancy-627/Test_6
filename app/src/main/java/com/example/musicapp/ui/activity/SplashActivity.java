package com.example.musicapp.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.musicapp.R;
import com.example.musicapp.presenter.SplashPresenter;
import com.example.musicapp.presenter.impl.SplashPresenterImpl;
import com.example.musicapp.util.ActivityUtils;
import com.example.musicapp.view.SplashView;

public class SplashActivity extends AppCompatActivity implements SplashView {

    SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.requestFullScreen(this, false);
        setContentView(R.layout.activity_splash);
        setUpMVP();

        new Handler().postDelayed(()->{
            mSplashPresenter.navigate();
        } , 500);
    }

    @Override
    public void setUpMVP() {
        mSplashPresenter = new SplashPresenterImpl(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void navigateMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void navigateLogin() {
        //todo
    }

    @Override
    public void navigateAccount() {
        startActivity(new Intent(this, AccountActivity.class));
        finish();
    }
}
