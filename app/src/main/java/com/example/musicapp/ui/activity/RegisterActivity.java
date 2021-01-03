package com.example.musicapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.musicapp.R;
import com.example.musicapp.view.RegisterView;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        //TODO
    }

    @Override
    public void register(String username, String password) {

    }

    @Override
    public void setUpMVP() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}