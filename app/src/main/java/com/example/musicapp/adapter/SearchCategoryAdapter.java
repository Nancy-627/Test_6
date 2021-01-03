package com.example.musicapp.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.blankj.utilcode.util.Utils;
import com.example.musicapp.R;
import com.example.musicapp.ui.fragments.ArtistSearchFragment;
import com.example.musicapp.ui.fragments.SingleSearchFragment;
import com.example.musicapp.ui.fragments.VideoSearchFragment;
import com.example.musicapp.util.TagUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchCategoryAdapter extends FragmentPagerAdapter {
    private String TAG = TagUtils.getTag(this.getClass());
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> categoryTitles = new ArrayList<>();

    public SearchCategoryAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(SingleSearchFragment.getInstance());
        categoryTitles.add(Utils.getApp().getResources().getString(R.string.single));

        fragments.add(VideoSearchFragment.getInstance());
        categoryTitles.add(Utils.getApp().getResources().getString(R.string.video));

        fragments.add(ArtistSearchFragment.getInstance());
        categoryTitles.add(Utils.getApp().getResources().getString(R.string.artist));

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categoryTitles.get(position);
    }
}