package com.example.musicapp.response.search;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class SongResponse implements Serializable {
    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private List<DataItem> data;
}
