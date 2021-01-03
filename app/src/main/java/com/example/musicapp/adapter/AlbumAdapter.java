package com.example.musicapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.R;
import com.example.musicapp.presenter.LocalPresenter;
import com.example.musicapp.ui.activity.LocalActivity;

import butterknife.ButterKnife;

public class AlbumAdapter extends RecyclerView.Adapter<LocalAlbumViewHolder> {
    LocalPresenter presenter = LocalActivity.mLocalPresenter;

    @NonNull
    @Override
    public LocalAlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        ButterKnife.bind(this, view);
        return new LocalAlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalAlbumViewHolder holder, int position) {
        presenter.onBindAlbumRowViewAtPosition(position, holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getAlbumItemCount();
    }

}
