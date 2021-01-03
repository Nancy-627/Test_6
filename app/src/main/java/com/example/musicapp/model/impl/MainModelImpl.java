package com.example.musicapp.model.impl;

import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.Utils;

import com.example.musicapp.R;
import com.example.musicapp.model.MainModel;
import com.example.musicapp.model.entities.NavProfile;
import com.example.musicapp.model.entities.User;
import com.example.musicapp.network.MusicAppService;
import com.example.musicapp.response.detail.DetailResponse;
import com.example.musicapp.response.login.LoginResponse;
import com.example.musicapp.response.login.Profile;
import com.example.musicapp.util.AccountUtils;
import com.example.musicapp.util.ConstantUtils;
import com.example.musicapp.util.TagUtils;

public class MainModelImpl implements MainModel {
    private MusicAppService mNetEaseMusicService;
    private String TAG = TagUtils.getTag(this.getClass());
    public MainModelImpl(MusicAppService mNetEaseMusicService) {
        this.mNetEaseMusicService = mNetEaseMusicService;
    }

    @Override
    public void update(Bundle bundle, final MainCallback callback) {

        //检查网络
        if (!NetworkUtils.isConnected()) {
            LogUtils.d(TAG, "isConnected()");
            callback.showNetworkError(Utils.getApp().getResources().getString(R.string.network_error));
            return;
        }
        String uid;
        //登录信息
        if (bundle != null && bundle.getSerializable(ConstantUtils.LOGIN_RESPONSE_KEY) != null) {
            LoginResponse loginResponse = (LoginResponse) bundle.getSerializable(ConstantUtils.LOGIN_RESPONSE_KEY);
            Profile profile = loginResponse.getProfile();
            uid = profile.getUserId() + "";
            callback.updateProfile(new NavProfile(profile.getBackgroundUrl(), profile.getAvatarUrl(), profile.getNickname()));
        } else {
            // no data provide, need call network
            User user = AccountUtils.restore();
            uid = user.getUid();
            mNetEaseMusicService.login(user.getUsername(), user.getPassword(), new MusicAppService.Callback<LoginResponse>() {
                @Override
                public void onSuccess(LoginResponse data) {
                    Profile profile = data.getProfile();
                    callback.updateProfile(new NavProfile(profile.getBackgroundUrl(), profile.getAvatarUrl(), profile.getNickname()));
                }

                @Override
                public void onError(Throwable e) {
                    callback.showNetworkError(e.getMessage());
                    LogUtils.e(TAG, e.fillInStackTrace());
                }
            });
        }

        //updateMusicMenu detail
        mNetEaseMusicService.detail(uid, new MusicAppService.Callback<DetailResponse>() {
            @Override
            public void onSuccess(DetailResponse detailResponse) {
                callback.updateDetail(detailResponse);
            }

            @Override
            public void onError(Throwable e) {
                callback.showNetworkError(e.getMessage());
                LogUtils.e(TAG, e.fillInStackTrace());
            }
        });
    }
}
