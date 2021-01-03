package com.example.musicapp.response.search.song;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Al {
    @SerializedName("picUrl")
    private String picUrl;

    @SerializedName("name")
    private String name;

    @SerializedName("tns")
    private List<Object> tns;

    @SerializedName("pic_str")
    private String picStr;

    @SerializedName("id")
    private int id;

    @SerializedName("pic")
    private long pic;
}
