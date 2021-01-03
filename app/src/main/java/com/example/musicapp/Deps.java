package com.example.musicapp;


import com.example.musicapp.network.NetworkModule;
import com.example.musicapp.ui.activity.CommentActivity;
import com.example.musicapp.ui.activity.LoginActivity;
import com.example.musicapp.ui.activity.MainActivity;
import com.example.musicapp.ui.activity.PlayActivity;
import com.example.musicapp.ui.activity.SearchActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);

    void inject(CommentActivity commentActivity);

    void inject(SearchActivity searchActivity);

    void inject(PlayActivity playActivity);
}