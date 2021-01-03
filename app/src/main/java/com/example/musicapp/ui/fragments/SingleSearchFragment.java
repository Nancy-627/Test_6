package com.example.musicapp.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.LogUtils;
import com.example.musicapp.R;
import com.example.musicapp.adapter.EndlessRecyclerOnScrollListener;
import com.example.musicapp.adapter.SearchSingleAdapter;
import com.example.musicapp.model.entities.LoadMoreEvent;
import com.example.musicapp.response.search.SearchResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SingleSearchFragment extends Fragment {
    private static String TAG = SingleSearchFragment.class.getSimpleName();

    @BindView(R.id.rv_single)
    RecyclerView rvSingle;

    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    public static SearchSingleAdapter adapter;

    private boolean isLoading = false;

    private static class SingletonHelper {
        private static final SingleSearchFragment INSTANCE = new SingleSearchFragment();
    }

    public static SingleSearchFragment getInstance() {
        return SingleSearchFragment.SingletonHelper.INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local_single, container, false);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        ButterKnife.bind(this, view);
        setUp();
        return view;
    }

    private void setUp() {
        rvSingle.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSingle.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        if (adapter == null) {
            adapter = new SearchSingleAdapter();
        }
        rvSingle.setAdapter(adapter);

        rvSingle.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                LogUtils.d(TAG, "loading...");
                isLoading = true;
                pbLoading.setVisibility(View.VISIBLE);
                EventBus.getDefault().post(new LoadMoreEvent());
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearchSingleEvent(SearchResponse searchResponse) {
        if (!isLoading) {
            adapter.setSongsItems(searchResponse.getResult().getSongs());
        } else {
            adapter.getSongsItems().addAll(searchResponse.getResult().getSongs());
            pbLoading.setVisibility(View.GONE);
            isLoading = false;
        }
        adapter.notifyDataSetChanged();
    };

}