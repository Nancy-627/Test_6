package com.example.musicapp.model;

import android.os.Bundle;

import com.example.musicapp.model.entities.NavProfile;
import com.example.musicapp.response.detail.DetailResponse;

public interface MainModel {
    void update(Bundle bundle, MainCallback callback);

    interface MainCallback {


        void updateDetail(DetailResponse detailResponse);

        void showNetworkError(String errorMsg);

        void updateProfile(NavProfile navProfile);
    }
}
