package com.example.musicapp.response.search.song;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class SongDetailResponse {
    @SerializedName("privileges")
    private List<PrivilegesItem> privileges;

    @SerializedName("code")
    private int code;

    @SerializedName("songs")
    private List<SongsItem> songs;
}
