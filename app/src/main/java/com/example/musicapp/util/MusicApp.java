package com.example.musicapp.util;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.blankj.utilcode.util.Utils;

import androidx.room.Room;
import com.example.musicapp.datebase.AppDatabase;
import com.example.musicapp.network.MusicAppService;
import com.example.musicapp.service.MusicServiceImpl;

public class MusicApp extends Application {
    private static final String DB_NAME = "NeEaseMusic.db";
    private static AppDatabase appDatabase;
    private static MusicAppService netEaseMusicService;

    public static void setNetEaseMusicService(MusicAppService netEaseMusicService) {
        MusicApp.netEaseMusicService = netEaseMusicService;
    }

    public static MusicAppService getNetEaseMusicService() {
        return netEaseMusicService;
    }

    public static AppDatabase getDBInstance() {
        return appDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, MusicServiceImpl.class));

        new Thread(()->{
            appDatabase = Room.databaseBuilder(Utils.getApp(), AppDatabase.class, DB_NAME).build();
        }).start();
    }
}
