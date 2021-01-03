package com.example.musicapp.adapter;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.musicapp.R;
import com.example.musicapp.ui.fragments.AlbumFragment;
import com.example.musicapp.ui.fragments.ArtistFragment;
import com.example.musicapp.ui.fragments.FolderFragment;
import com.example.musicapp.ui.fragments.SingleFragment;
import com.example.musicapp.util.TagUtils;

import java.util.ArrayList;
import java.util.List;


public class LocalCategoryPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private String TAG = TagUtils.getTag(this.getClass());
    private List<Fragment> fragments = new ArrayList<>();

    public LocalCategoryPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        fragments.add(SingleFragment.getInstance());
        fragments.add(ArtistFragment.getInstance());
        fragments.add(AlbumFragment.getInstance());
        fragments.add(FolderFragment.getInstance());
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getResources().getString(R.string.single);
            case 1:
                return context.getResources().getString(R.string.artist);
            case 2:
                return context.getResources().getString(R.string.album);
            default:
                return context.getResources().getString(R.string.folder);
        }

    }
}
