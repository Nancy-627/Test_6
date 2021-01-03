package com.example.musicapp.response.recommend;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class RecommendSongListResponse{

    @SerializedName("haveRcmdSongs")
    private boolean haveRcmdSongs;

    @SerializedName("code")
    private int code;

    @SerializedName("featureFirst")
    private boolean featureFirst;

    @SerializedName("recommend")
    private List<RecommendSongItem> recommend;
}