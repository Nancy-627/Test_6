package com.example.musicapp.model;

import com.example.musicapp.response.search.SearchResponse;
import com.example.musicapp.response.search.SongsItem;
import com.example.musicapp.response.search.artist.ArtistResponse;
import com.example.musicapp.response.search.hot.HotResponse;
import com.example.musicapp.response.search.video.VideoResponse;

import java.util.List;

public interface SearchModel {
    void searchHot(Callback callback);

    void searchByKeywords(String keyword, Callback callback);

    void searchVideo(String keyword, Callback callback);

    void searchArtist(String keyword, Callback callback);

    void loadHistory(Callback callback);

    void saveHistory(String keyword);

    void removeHistory();

    void saveSong(SongsItem songsItem, Callback callback);

    void loadMore(String keyword, int offset, Callback callback);

    interface Callback{
        void onHotSuccess(HotResponse response);

        void onKeywordSuccess(SearchResponse searchResponse);

        void onLoadMoreSuccess(SearchResponse searchResponse);

        void onVideoSuccess(VideoResponse videoResponse);

        void onArtistSuccess(ArtistResponse artistResponse);

        void onHistory(List<String> histories);

        void onSongReady(String url);

        void onHotFail();
    }

}
