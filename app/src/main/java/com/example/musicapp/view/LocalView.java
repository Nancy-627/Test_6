package com.example.musicapp.view;

public interface LocalView extends BaseView {
    void updateSongList();

    void updateArtistList();

    void updateAlbumList();

    void navigatePlay();

    void play(String songUrl);

    void updateMiniController();
}
