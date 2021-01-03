package com.example.musicapp.adapter;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.blankj.utilcode.util.Utils;
import com.example.musicapp.R;
import com.example.musicapp.ui.fragments.HotFragment;

import java.util.ArrayList;
import java.util.List;

public class DiscoveryPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();


    public DiscoveryPagerAdapter(FragmentManager fm) {
        super(fm);
        titles.add(Utils.getApp().getResources().getString(R.string.hot));
        titles.add(Utils.getApp().getResources().getString(R.string.contact));
        titles.add(Utils.getApp().getResources().getString(R.string.radio));
        fragments.add(new HotFragment());
        fragments.add(new HotFragment());
        fragments.add(new HotFragment());
        //TODO
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
        return titles.get(position);
    }
}
