package com.example.musicapp.response.search;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data

public class SearchResponse {
    @SerializedName("result")
    private Result result;

    @SerializedName("code")
    private int code;
}
