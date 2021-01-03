package com.example.musicapp.model;

public interface SplashModel {
    void checkLogin(SplashCallback callback);

    interface SplashCallback{
        void navigateMain();

        void navigateLogin();

        void navigateAccount();
    }
}
