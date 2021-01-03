package com.example.musicapp.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.R;
import com.example.musicapp.response.search.ArtistsItem;
import com.example.musicapp.response.search.SongsItem;
import com.example.musicapp.util.TagUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;


import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SearchSingleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG = TagUtils.getTag(this.getClass());
    private List<SongsItem> songsItems = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_single_item, parent, false);

        ButterKnife.bind(this, view);
        return new SearchSingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SearchSingleViewHolder) {
            ((SearchSingleViewHolder) holder).bind(position);
        }
    }

    @Override
    public int getItemCount() {
        return songsItems.size();
    }

    public class SearchSingleViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_search_single_name)
        TextView tvSearchSingleName;

        @BindView(R.id.tv_search_single_artist_album)
        TextView tvSearchSingleArtistAlbum;

        public SearchSingleViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            tvSearchSingleName.setText(songsItems.get(position).getName());
            tvSearchSingleArtistAlbum.setText(getArtistAndAlbum(songsItems.get(position)));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(songsItems.get(position));
                }
            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getArtistAndAlbum(SongsItem songsItem) {
        StringJoiner joiner = new StringJoiner("/");
        for (ArtistsItem artist : songsItem.getArtists()) {
            joiner.add(artist.getName());
        }

        return joiner.toString() + " - " + songsItem.getAlbum().getName();
    }
}

