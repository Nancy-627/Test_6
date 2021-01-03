package com.example.musicapp.model.entities;

import lombok.Data;
import com.example.musicapp.response.recommend.RecommendSongItem;

@Data
public class PlaylistEvent {
    RecommendSongItem recommendSongItem;

    public PlaylistEvent(RecommendSongItem recommendSongItem) {
        this.recommendSongItem = recommendSongItem;
    }
}
