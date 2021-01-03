package com.example.musicapp.presenter.impl;



import com.blankj.utilcode.util.LogUtils;
import com.example.musicapp.model.SearchModel;
import com.example.musicapp.model.impl.SearchModelImpl;
import com.example.musicapp.network.MusicAppService;
import com.example.musicapp.presenter.SearchPresenter;
import com.example.musicapp.response.search.SearchResponse;
import com.example.musicapp.response.search.SongsItem;
import com.example.musicapp.response.search.artist.ArtistResponse;
import com.example.musicapp.response.search.hot.HotResponse;
import com.example.musicapp.response.search.video.VideoResponse;
import com.example.musicapp.util.TagUtils;
import com.example.musicapp.view.SearchView;

import java.util.ArrayList;
import java.util.List;

public class SearchPresenterImpl implements SearchPresenter, SearchModel.Callback {
    private String TAG = TagUtils.getTag(this.getClass());
    private SearchModel mSearchModel;
    private MusicAppService mNetEaseMusicService;
    private SearchView mSearchView;

    public SearchPresenterImpl(SearchView searchView, MusicAppService netEaseMusicService) {
        this.mSearchView = searchView;
        this.mNetEaseMusicService = netEaseMusicService;
        this.mSearchModel = new SearchModelImpl(this.mNetEaseMusicService);

    }

    @Override
    public void searchHot() {
        mSearchView.showProgress();
        mSearchModel.searchHot(this);
    }

    @Override
    public void loadHistory() {
        mSearchModel.loadHistory(this);
    }

    @Override
    public void removeHistory() {
        mSearchView.showHistory(new ArrayList<>());
        mSearchModel.removeHistory();
    }

    @Override
    public void search(String keyword) {
        mSearchView.hideKeyboard();
        mSearchView.showProgress();
        mSearchModel.searchByKeywords(keyword, this);
    }

    @Override
    public void searchVideo(String keyword) {
        mSearchView.showProgress();
        mSearchModel.searchVideo(keyword, this);
    }

    @Override
    public void searchArtist(String keyword) {
        mSearchView.showProgress();
        mSearchModel.searchArtist(keyword, this);
    }

    @Override
    public void saveSong(SongsItem songsItem) {
        mSearchModel.saveSong(songsItem, this);
    }

    @Override
    public void loadMore(String keyword, int offset) {
        mSearchModel.loadMore(keyword, offset, this);
    }

    @Override
    public void onHotSuccess(HotResponse response) {
        mSearchView.hideProgress();
        mSearchView.showHots(response);
    }

    @Override
    public void onKeywordSuccess(SearchResponse searchResponse) {
        mSearchView.hideProgress();
        mSearchView.showSearchResult(searchResponse);
    }

    @Override
    public void onLoadMoreSuccess(SearchResponse searchResponse) {
//        showSearchResult
        mSearchView.showSearchResult(searchResponse);
    }

    @Override
    public void onVideoSuccess(VideoResponse videoResponse) {
        mSearchView.hideProgress();
        mSearchView.showVideos(videoResponse);
    }

    @Override
    public void onArtistSuccess(ArtistResponse artistResponse) {
        mSearchView.hideProgress();
        mSearchView.showArtist(artistResponse);
    }

    @Override
    public void onHistory(List<String> histories) {
        LogUtils.d(TAG, histories);
        mSearchView.showHistory(histories);
    }

    @Override
    public void onSongReady(String url) {
        mSearchView.updateMiniController();
        mSearchView.playSong(url);
    }

    @Override
    public void onHotFail() {
        //TODO
    }

}

