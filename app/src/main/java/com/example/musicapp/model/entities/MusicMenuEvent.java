package com.example.musicapp.model.entities;

import com.example.musicapp.bean.MusicMenu;

import java.util.List;

import lombok.Data;

@Data
public class MusicMenuEvent {

    private List<MusicMenu> musicMenus;

    public MusicMenuEvent(List<MusicMenu> musicMenus) {
        this.musicMenus = musicMenus;
    }
}
