package com.example.musicapp.service;


public interface MusicService {
    int getCurrentPosition();

    int getDuration();

    void seekTo(int position);

    String next();

    String previous();

    int getBufferPercent();

}
