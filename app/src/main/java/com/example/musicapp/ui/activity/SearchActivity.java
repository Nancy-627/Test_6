package com.example.musicapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;


import com.example.musicapp.ui.fragments.HotSearchFragment;
import com.example.musicapp.ui.fragments.SearchFragment;
import com.example.musicapp.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.example.musicapp.BaseApp;
import com.example.musicapp.R;
import com.example.musicapp.model.entities.HistoryEvent;
import com.example.musicapp.model.entities.LoadMoreEvent;
import com.example.musicapp.model.entities.RemoveHistoryEvent;
import com.example.musicapp.network.MusicAppService;
import com.example.musicapp.presenter.SearchPresenter;
import com.example.musicapp.presenter.impl.SearchPresenterImpl;
import com.example.musicapp.response.search.SearchResponse;
import com.example.musicapp.response.search.SongsItem;
import com.example.musicapp.response.search.artist.ArtistResponse;
import com.example.musicapp.response.search.hot.Hot;
import com.example.musicapp.response.search.hot.HotResponse;
import com.example.musicapp.response.search.video.VideoResponse;
import com.example.musicapp.util.ConstantUtils;
import com.example.musicapp.util.MusicUtils;
import com.example.musicapp.util.TagUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseApp implements  com.example.musicapp.view.SearchView{

    private String TAG = TagUtils.getTag(this.getClass());
    private SearchView searchView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.pb_searching)
    ProgressBar pbSearching;

    @BindView(R.id.iv_controller_album_cover)
    ImageView ivControllerAlbumCover;

    @BindView(R.id.tv_controller_title)
    TextView tvControllerTitle;

    @BindView(R.id.iv_controller_play_pause)
    ImageView ivControllerPlayPause;

    @BindView(R.id.iv_controller_playlist)
    ImageView ivControllerPlaylist;

    @BindView(R.id.tv_controller_lyric)
    TextView tvControllerLyric;


    @Inject
    MusicAppService mNetEaseMusicService;

    private SearchPresenter mSearchPresenter;

    private boolean isShowing = false;

    private List<String> searchHistory = new ArrayList<>();

    private int searchOffset = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        initToolbar();
        setUpMVP();
        findViewById(R.id.sliding_layout).setEnabled(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setIconified(false);
        searchView.clearFocus();

        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                LogUtils.d(TAG, query);
                showSearchResult();
                mSearchPresenter.search(query);
                mSearchPresenter.searchVideo(query);
                mSearchPresenter.searchArtist(query);

//                searchKeyword = query;
                MusicUtils.saveSearchKeyword(query);
                searchOffset = 1;
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                LogUtils.d(TAG, newText);
                return true;
            }
        });


        return true;
    }
    @Override
    public void showProgress() {
        pbSearching.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbSearching.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideKeyboard() {
        ActivityUtils.hideKeyboard(this);
    }

    @Override
    public void showHots(HotResponse hotResponse) {
        LogUtils.d(TAG, hotResponse);
        EventBus.getDefault().post(hotResponse);
    }

    @Override
    public void showHistory(List<String> searchHistory) {
        LogUtils.d(TAG, searchHistory);
        new Handler().postDelayed(() -> EventBus.getDefault().post(searchHistory), 500);
    }

    @Override
    public void showSearchResult(SearchResponse searchResponse) {
        LogUtils.d(TAG, searchResponse);
        EventBus.getDefault().post(searchResponse);
    }

    @Override
    public void showVideos(VideoResponse videoResponse) {
        LogUtils.d(TAG, videoResponse);
        EventBus.getDefault().post(videoResponse);
    }

    @Override
    public void showArtist(ArtistResponse artistResponse) {
        LogUtils.d(TAG, artistResponse);
        EventBus.getDefault().post(artistResponse);
    }

    @Override
    public void showSearchResult() {
        if (!isShowing) {

            getSupportFragmentManager().beginTransaction().replace(R.id.search_container, new SearchFragment()).commitAllowingStateLoss();
            isShowing = true;
        }
    }@Override
    public void playSong(String songUrl) {
        LogUtils.d(TAG, songUrl);
        Intent intent = new Intent(ConstantUtils.ACTION_PLAY);
        intent.putExtra(ConstantUtils.MUSIC_URI_KEY, songUrl);
        Utils.getApp().sendBroadcast(intent);
    }

    @Override
    public void updateMiniController() {
        Glide.with(this).load(MusicUtils.readAlbumCover()).into(ivControllerAlbumCover);
        tvControllerTitle.setText(MusicUtils.readSongName());
        tvControllerLyric.setText(MusicUtils.readArtistAndAlbum());
    }

    @Override
    public void setUpMVP() {
        getSupportFragmentManager().beginTransaction().replace(R.id.search_container, new HotSearchFragment()).commit();
        mSearchPresenter = new SearchPresenterImpl(this, mNetEaseMusicService);
        mSearchPresenter.searchHot();
        mSearchPresenter.loadHistory();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHotClickEvent(Hot hot) {
        LogUtils.d(TAG, hot);
        showSearchResult();
        mSearchPresenter.search(hot.getFirst());
        mSearchPresenter.searchVideo(hot.getFirst());
        mSearchPresenter.searchArtist(hot.getFirst());

        MusicUtils.saveSearchKeyword(hot.getFirst());
        searchOffset = 1;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHistoryEvent(HistoryEvent historyEvent) {
        LogUtils.d(TAG, historyEvent.getKeyword());
        showSearchResult();
        mSearchPresenter.search(historyEvent.getKeyword());

        MusicUtils.saveSearchKeyword(historyEvent.getKeyword());
        searchOffset = 1;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearchSingleClickEvent(SongsItem songsItem) {
        LogUtils.d(TAG, songsItem.getName());
        mSearchPresenter.saveSong(songsItem);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadMoreEvent(LoadMoreEvent loadMoreEvent) {
//        if (searchKeyword == null) {
//            searchKeyword = MusicUtils.readSearchKeyword();
//        }


        String searchKeyword = MusicUtils.readSearchKeyword();
        LogUtils.d(TAG, "searchKeyword:"  + searchKeyword + " searchOffset:" + searchOffset);
        mSearchPresenter.loadMore(searchKeyword, searchOffset);
        searchOffset++;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRemoveHistoryEvent(RemoveHistoryEvent event) {
        mSearchPresenter.removeHistory();
    }
}




