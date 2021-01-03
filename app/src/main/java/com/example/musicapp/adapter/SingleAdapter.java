package com.example.musicapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.R;
import com.example.musicapp.presenter.LocalPresenter;
import com.example.musicapp.ui.activity.LocalActivity;
import com.example.musicapp.util.ConstantUtils;

import butterknife.ButterKnife;

public class SingleAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private LocalPresenter localPresenter = LocalActivity.mLocalPresenter;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType) {
            case ConstantUtils.SINGLE_TYPE_HEADER:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_item_header, viewGroup, false);
                ButterKnife.bind(this, view);
                return new LocalSongHeaderViewHolder(view);
            case ConstantUtils.SINGLE_TYPE_IS_PLAYING:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_item_playing, viewGroup, false);
                ButterKnife.bind(this, view);
                return new LocalSongPlayingViewHolder(localPresenter, view);
            case ConstantUtils.SINGLE_TYPE_IS_NORMAL:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_item, viewGroup, false);
                ButterKnife.bind(this, view);
                return new LocalSongViewHolder(localPresenter, view);
            default:
                return null; //this won't be executed
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        localPresenter.onBindSongRowViewAtPosition(position, holder);
    }

    @Override
    public int getItemViewType(int position) {
        return localPresenter.getItemViewTypeOfSingle(position);
    }

    @Override
    public int getItemCount() {
        return localPresenter.getSongItemCount();
    }
}
