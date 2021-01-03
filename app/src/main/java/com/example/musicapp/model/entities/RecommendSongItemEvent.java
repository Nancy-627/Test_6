package com.example.musicapp.model.entities;

import com.example.musicapp.response.recommend.RecommendSongItem;

import java.util.List;

import lombok.Data;

@Data
public class RecommendSongItemEvent {
    List<RecommendSongItem> recommendSongItems;
}
