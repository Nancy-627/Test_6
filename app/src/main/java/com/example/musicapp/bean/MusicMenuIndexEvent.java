package com.example.musicapp.bean;

import lombok.Data;

@Data
public class MusicMenuIndexEvent {
    private int index;

    public MusicMenuIndexEvent(int index) {
        this.index = index;
    }
}
