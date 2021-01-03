package com.example.musicapp.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicapp.R;
import com.example.musicapp.adapter.HotAdapter;
import com.example.musicapp.model.entities.BannerEvent;
import com.example.musicapp.util.TagUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HotFragment extends Fragment {
    private String TAG  = TagUtils.getTag(this.getClass());
    @BindView(R.id.rv_fragment_hot)
    RecyclerView rvHot;

    private HotAdapter hotAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        setup();
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    private void setup() {
        rvHot.setLayoutManager(new LinearLayoutManager(getContext()));
        rvHot.setAdapter(hotAdapter = new HotAdapter());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBannerEvent(BannerEvent bannerEvent) {
        hotAdapter.setBannersItems(bannerEvent.getBannersItems());
        hotAdapter.notifyDataSetChanged();
    }
}