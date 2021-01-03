package com.example.musicapp.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.ToastUtils;
import com.example.musicapp.BaseApp;
import com.example.musicapp.R;
import com.example.musicapp.model.entities.NavProfile;
import com.example.musicapp.model.entities.User;
import com.example.musicapp.network.MusicAppService;
import com.example.musicapp.presenter.LoginPresenter;
import com.example.musicapp.presenter.impl.LoginPresenterImpl;
import com.example.musicapp.response.login.LoginResponse;
import com.example.musicapp.util.ActivityUtils;
import com.example.musicapp.util.ConstantUtils;
import com.example.musicapp.view.LoginVIew;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseApp implements LoginVIew {
    private LoginPresenter loginPresenter;

    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.pb_login)
    ProgressBar pbLogin;


    @Inject
    public MusicAppService mNetEaseMusicService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        setUpMVP();
    }


    @Override
    public void setUpMVP() {
        loginPresenter = new LoginPresenterImpl(mNetEaseMusicService, this);
    }

    @OnClick(R.id.btn_login)
    public void loginCallback() {
        String phone = etLoginPhone.getText().toString();
        String password = etLoginPassword.getText().toString();
        loginPresenter.validateCredentials(new User(phone,password));
    }

    @Override
    public void showProgress() {
        pbLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLogin.setVisibility(View.GONE);
    }

    @Override
    public void usernameError(String errorMsg) {
        ToastUtils.showLong(errorMsg);
    }

    @Override
    public void passwordError(String errorMsg) {
        ToastUtils.showLong(errorMsg);
    }

    @Override
    public void loginSuccess(LoginResponse loginResponse) {
        pbLogin.setVisibility(View.GONE);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent.putExtra(ConstantUtils.LOGIN_RESPONSE_KEY, loginResponse));
        finish();
    }

    @Override
    public void hideKeyboard() {
        ActivityUtils.hideKeyboard(this);
    }
}
