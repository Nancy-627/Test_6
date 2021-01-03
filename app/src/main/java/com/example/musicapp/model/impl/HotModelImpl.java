package com.example.musicapp.model.impl;

import com.blankj.utilcode.util.ToastUtils;
import com.example.musicapp.model.HotModel;
import com.example.musicapp.network.MusicAppService;

import com.example.musicapp.response.recommend.RecommendSongListResponse;

import com.example.musicapp.response.banner.BannerResponse;
import com.example.musicapp.util.ConstantUtils;
import com.example.musicapp.util.MusicApp;

public class HotModelImpl implements HotModel {
    @Override
    public void getBanner(Callback callback) {
        MusicApp.getNetEaseMusicService().getBanner(new MusicAppService.Callback<BannerResponse>(){

            @Override
            public void onSuccess(BannerResponse data) {
                if (data.getCode() == ConstantUtils.SUCCESS) {
                    callback.onBanner(data.getBanners());
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void getRecommendSongList(Callback callback) {
        String cookie = "__remember_me=true; " +
                "MUSIC_U=8f68d095e67f61eb13cc459fb616d94b47e5068cd50411b88eda4b883181a673b770156b3ad1280a9e8ae5c70ca8806963531a931aa80ad0; " +
                "__csrf=47281a4e13088a05288b11ca161c982c";
        MusicApp.getNetEaseMusicService().getRecommendSongList(cookie, new MusicAppService.Callback<RecommendSongListResponse>(){

            @Override
            public void onSuccess(RecommendSongListResponse data) {
                if (data.getCode() == ConstantUtils.SUCCESS) {
                    callback.onRecommendSongList(data.getRecommend());
                }else {
                    ToastUtils.showShort("fail to resolve recommend");
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("error");
            }
        });
    }
}
