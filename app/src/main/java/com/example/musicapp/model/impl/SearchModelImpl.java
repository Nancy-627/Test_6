package com.example.musicapp.model.impl;

import android.content.Context;
import android.content.Intent;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.example.musicapp.NetworkMusic;
import com.example.musicapp.model.SearchModel;
import com.example.musicapp.model.entities.SearchHistory;
import com.example.musicapp.network.MusicAppService;
import com.example.musicapp.response.search.SearchResponse;
import com.example.musicapp.response.search.SongResponse;
import com.example.musicapp.response.search.SongsItem;
import com.example.musicapp.response.search.artist.ArtistResponse;
import com.example.musicapp.response.search.hot.HotResponse;
import com.example.musicapp.response.search.song.SongDetailResponse;
import com.example.musicapp.response.search.video.VideoResponse;
import com.example.musicapp.util.ConstantUtils;
import com.example.musicapp.util.MusicApp;
import com.example.musicapp.util.MusicUtils;
import com.example.musicapp.util.TagUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchModelImpl implements SearchModel {
    private String TAG = TagUtils.getTag(this.getClass());
    private MusicAppService mNetEaseMusicService;

    public SearchModelImpl(MusicAppService netEaseMusicService) {
        this.mNetEaseMusicService = netEaseMusicService;
    }

    @Override
    public void searchHot(Callback callback) {
        mNetEaseMusicService.searchHot(new MusicAppService.Callback<HotResponse>() {
            @Override
            public void onSuccess(HotResponse data) {
                callback.onHotSuccess(data);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void searchByKeywords(String keyword, Callback callback) {
        saveHistory(keyword);
        mNetEaseMusicService.search(keyword, new MusicAppService.Callback<SearchResponse>() {

            @Override
            public void onSuccess(SearchResponse searchResponse) {
                callback.onKeywordSuccess(searchResponse);

                //store search result
                for (SongsItem songsItem : searchResponse.getResult().getSongs()) {
                    mNetEaseMusicService.getSongUrl(songsItem.getId(), new MusicAppService.Callback<SongResponse>() {

                        @Override
                        public void onSuccess(SongResponse data) {
                            data.getData().get(0).getUrl();
                            NetworkMusic networkMusic = new NetworkMusic();
                            networkMusic.setId(songsItem.getId());
                            networkMusic.setUrl(data.getData().get(0).getUrl());
                            networkMusic.setName(songsItem.getName());
                            networkMusic.setArtistAndAlbum(MusicUtils.getArtistAndAlbum(songsItem));
                            EventBus.getDefault().post(networkMusic);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void loadMore(String keyword, int offset, Callback callback) {
        mNetEaseMusicService.search(keyword, offset, new MusicAppService.Callback<SearchResponse>() {
            @Override
            public void onSuccess(SearchResponse data) {
                callback.onLoadMoreSuccess(data);

                //store search re
                for (SongsItem songsItem : data.getResult().getSongs()) {
                    mNetEaseMusicService.getSongUrl(songsItem.getId(), new MusicAppService.Callback<SongResponse>() {

                        @Override
                        public void onSuccess(SongResponse data) {
                            data.getData().get(0).getUrl();
                            NetworkMusic networkMusic = new NetworkMusic();
                            networkMusic.setId(songsItem.getId());
                            networkMusic.setUrl(data.getData().get(0).getUrl());
                            networkMusic.setName(songsItem.getName());
                            networkMusic.setArtistAndAlbum(MusicUtils.getArtistAndAlbum(songsItem));
                            EventBus.getDefault().post(networkMusic);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void searchVideo(String keyword, Callback callback) {
        mNetEaseMusicService.searchVideo(keyword, new MusicAppService.Callback<VideoResponse>() {
            @Override
            public void onSuccess(VideoResponse data) {
                callback.onVideoSuccess(data);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void searchArtist(String keyword, Callback callback) {
        mNetEaseMusicService.searchArtist(keyword, new MusicAppService.Callback<ArtistResponse>() {
            @Override
            public void onSuccess(ArtistResponse data) {
                callback.onArtistSuccess(data);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void loadHistory(Callback callback) {
        MusicApp.getDBInstance().searchHistoryDao().getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SearchHistory>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<SearchHistory> searchHistories) {
                        List<String> histories = new ArrayList<>();
                        for (int i = 0, size = searchHistories.size(); i < size && histories.size() <= 5; i++) {
                            if (!histories.contains(searchHistories.get(i).getKeyword())) {
                                histories.add(searchHistories.get(i).getKeyword());
                            }
                        }
                        callback.onHistory(histories);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void saveHistory(String keyword) {

        new Thread(() -> {
            List<String> keywords =  MusicApp.getDBInstance().searchHistoryDao().getAllKeywords();

            MusicApp.getDBInstance().searchHistoryDao().deleteAll();
//            make keyword to top
            keywords.add(0, keyword);
            keywords = removeRepeat(keywords);
            LogUtils.d(TAG, keywords);
            //save top 5 history
            for (int i = 0, size = keywords.size(); i < size && i < 5; i++) {
                MusicApp.getDBInstance().searchHistoryDao().insertAll(new SearchHistory(i, keywords.get(i)));
            }
        }).start();
    }

    private List<String> removeRepeat(List<String> list) {
        List<String> result = new ArrayList<>();
        for (String item : list) {
            if (!result.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public void removeHistory() {
        new Thread(() -> {
            MusicApp.getDBInstance().searchHistoryDao().deleteAll();
        }).start();
    }

    @Override
    public void saveSong(SongsItem songsItem, Callback callback) {
        MusicUtils.saveSongId(songsItem.getId());
        SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).put(ConstantUtils.SP_CURRENT_SONG_NAME_KEY, songsItem.getName());
        SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).put(ConstantUtils.SP_CURRENT_SONG_ARTIST_AND_ALBUM_KEY, MusicUtils.getArtistAndAlbum(songsItem));

        mNetEaseMusicService.getSongUrl(songsItem.getId(), new MusicAppService.Callback<SongResponse>() {

            @Override
            public void onSuccess(SongResponse data) {
                LogUtils.d(TAG, data.getData().get(0).getUrl());
                callback.onSongReady(data.getData().get(0).getUrl());
            }

            @Override
            public void onError(Throwable e) {

            }
        });
        mNetEaseMusicService.getSongDetail(songsItem.getId(), new MusicAppService.Callback<SongDetailResponse>() {
            @Override
            public void onSuccess(SongDetailResponse data) {
                //store pic
                LogUtils.d(TAG, data.getSongs().get(0).getAl().getPicUrl());
                SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE)
                        .put(ConstantUtils.SP_CURRENT_SONG_ALBUM_URL_KEY, data.getSongs().get(0).getAl().getPicUrl());
                Utils.getApp().sendBroadcast(new Intent(ConstantUtils.ACTION_UPDATE_NOTIFICATION));
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

}

