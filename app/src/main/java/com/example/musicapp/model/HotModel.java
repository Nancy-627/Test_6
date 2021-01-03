package com.example.musicapp.model;

import java.util.List;

import com.example.musicapp.response.banner.BannerResponse;
import com.example.musicapp.response.banner.BannersItem;
import com.example.musicapp.response.recommend.RecommendSongItem;

public interface HotModel {

    void getBanner(Callback callback);

    void getRecommendSongList(Callback callback);

    interface Callback{
        void onBanner(List<BannersItem> bannersItems);

        void onRecommendSongList(List<RecommendSongItem> recommendSongItems);
    }
}
