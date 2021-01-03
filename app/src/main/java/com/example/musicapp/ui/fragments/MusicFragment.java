package com.example.musicapp.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.example.musicapp.R;
import com.example.musicapp.adapter.MusicMenuAdapter;
import com.example.musicapp.model.entities.MusicMenuEvent;
import com.example.musicapp.util.TagUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MusicFragment extends Fragment {

    private String TAG = TagUtils.getTag(this.getClass());
    @BindView(R.id.rv_music_menu)
    RecyclerView rvMusicMenu;
    private static MusicMenuAdapter adapter;




    public MusicFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_music, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        setUp();
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    private void setUp() {
        LogUtils.d(TAG, "init");
        rvMusicMenu.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMusicMenu.setAdapter(adapter = new MusicMenuAdapter());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMenuReadyEvent(MusicMenuEvent event) {
        LogUtils.d(TAG, event.getMusicMenus());
        adapter.setMusicMenus(event.getMusicMenus());
        adapter.notifyDataSetChanged();
    }
}