package com.example.musicapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.R;
import com.example.musicapp.response.playlist.TracksItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = true)

    public class PlaylistDetailAdapter extends RecyclerView.Adapter<PlaylistDetailAdapter.PlaylistDetailViewHolder> {
    private List<TracksItem> tracksItems = new ArrayList<>();
    @NonNull
    @Override
    public PlaylistDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_detail_item, parent, false);
        ButterKnife.bind(this, view);
        return new PlaylistDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistDetailViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return tracksItems.size();
    }

    public class PlaylistDetailViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_track_name)
        TextView tvTrackName;

        @BindView(R.id.tv_playlist_counter)
        TextView tvPlaylistCounter;

        @BindView(R.id.iv_track_quality)
        ImageView ivTrackQuality;

        @BindView(R.id.tv_artist_and_album)
        TextView tvArtistAndAlbum;

        @BindView(R.id.iv_playlist_action_pop_menu)
        ImageView ivPlaylistActionPopMenu;

        @BindView(R.id.iv_playlist_video_action)
        ImageView ivPlaylistVideoAction;


        public PlaylistDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            tvTrackName.setText(tracksItems.get(position).getName());
            tvPlaylistCounter.setText(position + 1 + "");
            tvArtistAndAlbum.setText(tracksItems.get(position).getAr().get(0).getName() + tracksItems.get(position).getAl().getName());
        }
    }
}
