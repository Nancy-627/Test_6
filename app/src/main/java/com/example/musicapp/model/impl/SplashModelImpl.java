package com.example.musicapp.model.impl;

import android.text.TextUtils;

import com.example.musicapp.model.SplashModel;
import com.example.musicapp.model.entities.User;
import com.example.musicapp.util.AccountUtils;

public class SplashModelImpl implements SplashModel {
    @Override
    public void checkLogin(SplashCallback callback) {
        User user = AccountUtils.restore();
        if (TextUtils.isEmpty(user.getUsername()) || TextUtils.isEmpty(user.getPassword())) {
            callback.navigateAccount();
        }else{
            callback.navigateMain();
        }
    }
}
