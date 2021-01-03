package com.example.musicapp.model;

import com.example.musicapp.model.entities.Album;
import com.example.musicapp.model.entities.Artist;
import com.example.musicapp.model.entities.Single;

import java.util.List;

public interface LocalModel {
    void loadSong(Single single, Callback callback);

    void loadAllSingle(Callback callback);

    void loadAllAlbum(Callback callback);

    void loadAllArtist(Callback callback);

    void query(String keyword, Callback callback);


    interface Callback {
        void onLoadedSong(String songUrl);

        void onLoadedAllSingle(List<Single> singles);

        void onLoadedAllAlbum(List<Album> albums);

        void onLoadAllArtist(List<Artist> artists);
    }
}
