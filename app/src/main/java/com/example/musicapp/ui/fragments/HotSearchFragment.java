package com.example.musicapp.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.example.musicapp.R;
import com.example.musicapp.adapter.HistoryAdapter;
import com.example.musicapp.adapter.HotSearchAdapter;
import com.example.musicapp.model.entities.RemoveHistoryEvent;
import com.example.musicapp.response.search.hot.HotResponse;
import com.example.musicapp.util.TagUtils;
import com.google.android.flexbox.FlexboxLayoutManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HotSearchFragment extends Fragment {


        private String TAG = TagUtils.getTag(this.getClass());
        @BindView(R.id.iv_remove_history)
        ImageView ivRemoveHistory;

        @BindView(R.id.rvHots)
        RecyclerView rvHots;

        @BindView(R.id.rvHistory)
        RecyclerView rvHistory;

        HotSearchAdapter mHotSearchAdapter;
        HistoryAdapter mHistoryAdapter;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_search_hot, container, false);
            ButterKnife.bind(this, view);

            setup();
            return view;
        }

        @Override
        public void onStart() {
            super.onStart();
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
        }

        private void setup() {
            rvHots.setAdapter(mHotSearchAdapter = new HotSearchAdapter());
            rvHots.setLayoutManager(new FlexboxLayoutManager(getContext()));

            rvHistory.setAdapter(mHistoryAdapter = new HistoryAdapter());
            rvHistory.setLayoutManager(new FlexboxLayoutManager(getContext()));

        }

        @Subscribe(threadMode = ThreadMode.MAIN)
        public void onHotsReady(HotResponse hotResponse) {
            LogUtils.d(TAG, hotResponse);
            mHotSearchAdapter.setHots(hotResponse.getResult().getHots());
            mHotSearchAdapter.notifyDataSetChanged();
        }

        @Subscribe(threadMode = ThreadMode.MAIN)
        public void onHistoryReady(List<String> histories) {
            LogUtils.d(TAG, histories);
            mHistoryAdapter.setHistories(histories);
            mHistoryAdapter.notifyDataSetChanged();
        }

        @OnClick({R.id.iv_remove_history})
        public void click(View view){
            switch (view.getId()) {
                case R.id.iv_remove_history:
                    EventBus.getDefault().post(new RemoveHistoryEvent());
                    break;
                default:
                    //todo
            }
        }
    }
