package com.example.musicapp.view;

import com.example.musicapp.response.search.SearchResponse;
import com.example.musicapp.response.search.artist.ArtistResponse;
import com.example.musicapp.response.search.hot.HotResponse;
import com.example.musicapp.response.search.video.VideoResponse;

import java.util.List;

public interface SearchView extends BaseView {

    void hideKeyboard();

    void showHots(HotResponse hotResponse);

    void showHistory(List<String> searchHistory);

    void showSearchResult(SearchResponse searchResponse);

    void showVideos(VideoResponse videoResponse);

    void showArtist(ArtistResponse artistResponse);

    void showSearchResult();

    void playSong(String songUrl);

    void updateMiniController();

}
