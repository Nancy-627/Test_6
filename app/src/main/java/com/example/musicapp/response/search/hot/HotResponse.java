package com.example.musicapp.response.search.hot;


import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class HotResponse {
    @SerializedName("result")
    private Result result;

    public int getCode() {
        return code;
    }

    @SerializedName("code")
    private int code;
}
