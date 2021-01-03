package com.example.musicapp.model.impl;

import com.example.musicapp.model.PlaylistModel;
import com.example.musicapp.network.MusicAppService;
import com.example.musicapp.response.playlist.PlaylistDetailResponse;
import com.example.musicapp.util.MusicApp;
import com.example.musicapp.util.TagUtils;

public class PlaylistModelIMpl implements PlaylistModel {
    private String TAG = TagUtils.getTag(this.getClass());
    @Override
    public void getPlaylistDetail(long id, Callback callback) {
        MusicApp.getNetEaseMusicService().getPlaylistDetail(id, new MusicAppService.Callback<PlaylistDetailResponse>(){

            @Override
            public void onSuccess(PlaylistDetailResponse data) {
                callback.onPlaylistDetail(data.getPlaylist());
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }
}
