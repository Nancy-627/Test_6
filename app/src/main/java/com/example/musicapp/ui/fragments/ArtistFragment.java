package com.example.musicapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.R;
import com.example.musicapp.adapter.ArtistAdapter;
import com.example.musicapp.model.entities.ArtistEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistFragment extends Fragment {
    private static String TAG = SingleFragment.class.getSimpleName();

    @BindView(R.id.rv_artist)
    RecyclerView rvArtist;

    private ArtistAdapter adapter;

    public ArtistFragment() {
        // Required empty public constructor
    }

    private static class SingletonHelper {
        private static final ArtistFragment INSTANCE = new ArtistFragment();
    }

    public static ArtistFragment getInstance() {
        return ArtistFragment.SingletonHelper.INSTANCE;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artist, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        setUp();
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void setUp() {
        rvArtist.setLayoutManager(new LinearLayoutManager(getContext()));
        rvArtist.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        if ( adapter == null) {
            adapter = new ArtistAdapter();
        }
        rvArtist.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSingleEvent(ArtistEvent event) {
        adapter.notifyDataSetChanged();
    };

}