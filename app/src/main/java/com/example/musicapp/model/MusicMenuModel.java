package com.example.musicapp.model;

import com.example.musicapp.bean.MusicMenuIndexEvent;

public interface MusicMenuModel {
    void navigate(MusicMenuIndexEvent event, MusicMenuCallback callback);

    interface MusicMenuCallback{

        void navigateToLocalView();

        void navigateToRecentView();

        void navigateToDownloadsView();

        void navigateToStationsView();

        void navigateToFavorites();
    }
}
