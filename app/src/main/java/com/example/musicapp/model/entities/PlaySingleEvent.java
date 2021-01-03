package com.example.musicapp.model.entities;


import lombok.Data;

@Data
public class PlaySingleEvent {
    Single single;

    public PlaySingleEvent(Single single) {
        this.single = single;
    }
}
