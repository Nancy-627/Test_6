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
import com.example.musicapp.adapter.DiscoveryPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DiscoveryFragment extends Fragment {

    @BindView(R.id.tl_discovery)
    TabLayout tbDiscovery;

    @BindView(R.id.vp_discovery)
    ViewPager vpDiscovery;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discovery, container, false);

        ButterKnife.bind(this, view);
        init();
        return view;
    }

    public void init() {
        vpDiscovery.setAdapter(new DiscoveryPagerAdapter(getFragmentManager()));
        tbDiscovery.setupWithViewPager(vpDiscovery);
    }


}