package com.example.musicapp.ui.fragments;

import androidx.fragment.app.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicapp.R;
import com.example.musicapp.adapter.MainPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment {

    @BindView(R.id.tl_main)
    TabLayout tlMain;

    @BindView(R.id.vp_main)
    ViewPager vpMain;

    public MainFragment() {
        // Required empty public constructor
    }

    int[] actionBarIcon = {
            R.drawable.action_music,
            R.drawable.ic_discovery,
            R.drawable.ic_video
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    public void init() {
        vpMain.setAdapter(new MainPagerAdapter(getFragmentManager()));
        tlMain.setupWithViewPager(vpMain);

        for (int i = 0; i < tlMain.getTabCount(); i++) {
            tlMain.getTabAt(i).setIcon(actionBarIcon[i]);
        }
    }

}
