package com.example.musicapp.model.impl;

import com.example.musicapp.bean.MusicMenuIndexEvent;
import com.example.musicapp.model.MusicMenuModel;

public class MusicMenuModelImpl implements MusicMenuModel {

    @Override
    public void navigate(MusicMenuIndexEvent musicMenuIndexEvent, MusicMenuCallback callback) {
        switch (musicMenuIndexEvent.getIndex()) {
            case 0:
                callback.navigateToLocalView();
                break;
            case 1:
                callback.navigateToRecentView();
                break;
            case 2:
                callback.navigateToDownloadsView();
                break;
            case 3:
                callback.navigateToStationsView();
                break;
            case 4:
                callback.navigateToFavorites();
                break;
            case 5:
                //TODO
                break;
        }
    }
}
