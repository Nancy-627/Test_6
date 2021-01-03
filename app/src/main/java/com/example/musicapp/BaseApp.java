package com.example.musicapp;

import android.os.Bundle;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;


import com.example.musicapp.network.NetworkModule;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseApp extends AppCompatActivity {
    Deps deps;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();
    }

}