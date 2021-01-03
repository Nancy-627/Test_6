package com.example.musicapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicapp.R;
import com.example.musicapp.response.search.video.VideosItem;
import com.example.musicapp.util.MusicUtils;
import com.example.musicapp.util.TagUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SearchVideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String TAG = TagUtils.getTag(this.getClass());
    private List<VideosItem> videosItems = new ArrayList<>();
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_video_item, parent, false);
        ButterKnife.bind(this, view);
        return new SearchVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SearchVideoViewHolder) {
            ((SearchVideoViewHolder) holder).bind(position);
        }
    }

    @Override
    public int getItemCount() {
        return videosItems.size();
    }

    public class SearchVideoViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_video_cover)
        ImageView ivVideoCover;

        @BindView(R.id.tv_duration_creator)
        TextView tvDurationCreator;

        public SearchVideoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            Glide.with(itemView).load(videosItems.get(position).getCoverUrl()).into(ivVideoCover);

            String durationCreator = MusicUtils.formatDuration(videosItems.get(position).getDurationms()) + " by " + videosItems.get(position).getCreator().get(0).getUserName();
            tvDurationCreator.setText(durationCreator);
        }
    }
}
