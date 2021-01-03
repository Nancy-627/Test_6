package com.example.musicapp.view;


import com.example.musicapp.response.login.LoginResponse;

public interface LoginVIew extends BaseView {

    void showProgress();

    void hideProgress();

    void usernameError(String errorMsg);

    void passwordError(String errorMsg);

    void loginSuccess(LoginResponse loginResponse);

    void hideKeyboard();
}