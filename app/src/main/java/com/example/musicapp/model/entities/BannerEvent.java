package com.example.musicapp.model.entities;

import com.example.musicapp.response.banner.BannersItem;

import java.util.List;

import lombok.Data;

@Data
public class BannerEvent {
    List<BannersItem> bannersItems;

    public BannerEvent(List<BannersItem> bannersItems) {
        this.bannersItems = bannersItems;
    }
}
