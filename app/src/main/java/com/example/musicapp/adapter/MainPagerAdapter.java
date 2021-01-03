package com.example.musicapp.adapter;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.musicapp.ui.fragments.DiscoveryFragment;
import com.example.musicapp.ui.fragments.MusicFragment;
import com.example.musicapp.ui.fragments.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new MusicFragment());
        fragments.add(new DiscoveryFragment());
        fragments.add(new VideoFragment());

    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }
}
