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
import com.example.musicapp.adapter.SearchArtistAdapter;
import com.example.musicapp.response.search.artist.ArtistResponse;
import com.example.musicapp.util.TagUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistSearchFragment extends Fragment {

    private String TAG = TagUtils.getTag(this.getClass());

    @BindView(R.id.rv_artist)
    RecyclerView rvArtist;

    private SearchArtistAdapter adapter;

    public ArtistSearchFragment() {
        // Required empty public constructor
    }

    private static class SingleHolder {
        private static ArtistSearchFragment INSTANCE = new ArtistSearchFragment();
    }

    public static ArtistSearchFragment getInstance() {
        return SingleHolder.INSTANCE;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artist_search, container, false);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        ButterKnife.bind(this, view);

        setUp();
        return view;
    }

    private void setUp() {
        rvArtist.setLayoutManager(new LinearLayoutManager(getContext()));
        if (adapter == null) {
            adapter = new SearchArtistAdapter();
        }
        rvArtist.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearchArtistEvent(ArtistResponse artistResponse) {
        LogUtils.d(TAG, artistResponse);
        adapter.setArtistsItems(artistResponse.getResult().getArtists());
        adapter.notifyDataSetChanged();
    };
}
