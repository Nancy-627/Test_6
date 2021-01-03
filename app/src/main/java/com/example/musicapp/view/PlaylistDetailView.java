package com.example.musicapp.view;


import com.example.musicapp.response.playlist.Playlist;

public interface PlaylistDetailView extends BaseView {
    void showPlaylist(Playlist playlist);

    void showProgress();

    void hideProgress();

    void showPlaylistCover(String coverImgUrl);

}
