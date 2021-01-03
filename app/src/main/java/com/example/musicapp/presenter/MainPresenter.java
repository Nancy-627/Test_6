package com.example.musicapp.presenter;

import android.os.Bundle;

import com.example.musicapp.bean.MusicMenuIndexEvent;

public interface MainPresenter {

    void update(Bundle bundle);

    void musicMenuNavigate(MusicMenuIndexEvent musicMenuIndexEvent);

    void getBanner();

    void getRecommendSongList();
}
