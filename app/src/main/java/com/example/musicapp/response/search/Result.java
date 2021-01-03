package com.example.musicapp.response.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Result {
    @SerializedName("songs")
    private List<SongsItem> songs;

    @SerializedName("songCount")
    private int songCount;
}
