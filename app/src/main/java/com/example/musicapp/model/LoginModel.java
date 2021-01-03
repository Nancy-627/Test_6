package com.example.musicapp.model;

import com.example.musicapp.model.entities.User;
import com.example.musicapp.response.login.LoginResponse;

public interface LoginModel {
    void login(User user, LoginCallback callback);

    interface LoginCallback {
        void onUsernameError(String errorMsg);

        void onPasswordError(String errorMsg);

        void onSuccess(LoginResponse response);

    }
}
