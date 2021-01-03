package com.example.musicapp.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicapp.R;
import com.example.musicapp.adapter.VideoPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VideoFragment extends Fragment {

    @BindView(R.id.tl_video)
    TabLayout tlVideo;

    @BindView(R.id.vp_video)
    ViewPager vpVideo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        ButterKnife.bind(this, view);
        init();
        return view;
    }

    public void init() {
        vpVideo.setAdapter(new VideoPagerAdapter(getFragmentManager()));
        tlVideo.setupWithViewPager(vpVideo);
//        vpVideo.setOffscreenPageLimit(5);
    }
}