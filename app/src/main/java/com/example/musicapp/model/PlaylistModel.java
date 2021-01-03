package com.example.musicapp.model;

import com.example.musicapp.response.playlist.Playlist;

public interface PlaylistModel {
    void getPlaylistDetail(long id, Callback callback);

    interface Callback{
        void onPlaylistDetail(Playlist playlist);
    }
}