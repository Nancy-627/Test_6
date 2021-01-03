package com.example.musicapp.presenter;

import com.example.musicapp.response.recommend.RecommendSongItem;

public interface PlaylistPresenter {
    void getPlaylist(RecommendSongItem recommendSongItem);
}
