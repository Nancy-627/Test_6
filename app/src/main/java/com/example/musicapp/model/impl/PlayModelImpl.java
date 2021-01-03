package com.example.musicapp.model.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.example.musicapp.R;
import com.example.musicapp.model.PlayModel;
import com.example.musicapp.network.MusicAppService;
import com.example.musicapp.util.ConstantUtils;
import com.example.musicapp.util.MusicUtils;
import com.example.musicapp.util.TagUtils;

public class PlayModelImpl implements PlayModel {
    private String TAG = TagUtils.getTag(this.getClass());
    private MusicAppService mNetEaseMusicService;

    public PlayModelImpl(MusicAppService mNetEaseMusicService) {
        this.mNetEaseMusicService = mNetEaseMusicService;
    }

    private boolean mPlaying = SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).getBoolean(ConstantUtils.SP_CURRENT_IS_PLAYING_STATUS_KEY, false);
    @Override
    public void deal(View view, PlayCallback callback) {
        switch (view.getId()) {
            case R.id.iv_play_type:
                int resId = 0;
                SPUtils spUtils = SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_SETTING);
                switch (spUtils.getInt(ConstantUtils.SP_PLAY_TYPE_KEY, 0)) {
                    case ConstantUtils.PLAY_MODE_LOOP_ALL_CODE:
                        spUtils.put(ConstantUtils.SP_PLAY_TYPE_KEY, ConstantUtils.PLAY_MODE_LOOP_SINGLE_CODE);
                        resId = R.drawable.loop_single_black;
                        break;
                    case ConstantUtils.PLAY_MODE_LOOP_SINGLE_CODE:
                        spUtils.put(ConstantUtils.SP_PLAY_TYPE_KEY, ConstantUtils.PLAY_MODE_SHUFFLE_CODE);
                        resId = R.drawable.shuffle_black;
                        break;
                    case ConstantUtils.PLAY_MODE_SHUFFLE_CODE:
                        spUtils.put(ConstantUtils.SP_PLAY_TYPE_KEY, ConstantUtils.PLAY_MODE_LOOP_ALL_CODE);
                        resId = R.drawable.ic_loop_all_black;
                        break;
                }
                callback.onPlayType(resId);
                break;
            case R.id.iv_previous:
                callback.onPrevious();
                getPicUrl(callback);
                break;
            case R.id.iv_play_pause:
                //TODO
                if (!mPlaying) {
                    callback.onPlay();
                    LogUtils.d(TAG, "onPlay");
                    SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).put(ConstantUtils.SP_CURRENT_IS_PLAYING_STATUS_KEY, true);
                } else {
                    callback.onPause();
                    LogUtils.d(TAG, "onPause");
                    SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).put(ConstantUtils.SP_CURRENT_IS_PLAYING_STATUS_KEY, false);
                }
                mPlaying = !mPlaying;
                break;
            case R.id.iv_next:
                callback.onNext();
                getPicUrl(callback);
                break;
            case R.id.iv_playlist:
                callback.onPlaylist();
                break;
            case R.id.iv_favorite:
                callback.onFavorite();
                break;
            case R.id.iv_download:
                callback.onDownload();
                break;
            case R.id.iv_comment:
                int songId = SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).getInt(ConstantUtils.SP_CURRENT_SONG_ID_KEY, 0);
                callback.onComment(songId);
                break;
            case R.id.iv_menu:
                callback.onMenu();
                break;
        }
    }

    @Override
    public void getSongUrl(PlayCallback callback) {
        getPicUrl(callback);
    }
//
//    @Override
//    public void getSongUrl(int id, PlayCallback callback) {
//        if (id == 0) {
//            //invalid id, do nothing
//            return;
//        }
//        mNetEaseMusicService.getSongUrl(id, new NetEaseMusicService.Callback<SongResponse>(){
//
//            @Override
//            public void onSuccess(SongResponse data) {
//                callback.onSongUrl(data.getData().get(0).getUrl());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//        });
//    }

    @Override
    public void getPicUrl(PlayCallback callback) {
        new Thread(()->{
            Bitmap bitmap = MusicUtils.getAlbumCover();
            if (bitmap == null) {
                LogUtils.d(TAG, "no such album");
                return;
            }else {
                com.blankj.utilcode.util.ActivityUtils.getTopActivity().runOnUiThread(()->{
                    callback.onLoadedAlbum(bitmap);
                });
            }
        }).start();
//        int songId = SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).getInt(ConstantUtils.SP_CURRENT_SONG_ID_KEY, 0);
//        if (songId == 0) {
//            return;
//        }
//        mNetEaseMusicService.getSongDetail(songId, new NetEaseMusicService.Callback<SongDetailResponse>() {
//            @Override
//            public void onSuccess(SongDetailResponse data) {
//                callback.onAlbumUrl(data.getSongs().get(0).getAl().getPicUrl());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//        });
    }

    @Override
    public void saveSongId(int id) {
        LogUtils.d(TAG, id);
        SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).put(ConstantUtils.SP_CURRENT_SONG_ID_KEY, id);
    }

    @Override
    public void readSongId(PlayCallback callback) {
        int id = SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE)
                .getInt(ConstantUtils.SP_CURRENT_SONG_ID_KEY, 0);
    }

    @Override
    public void getPlayType(PlayCallback callback) {
        SPUtils spUtils = SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_SETTING);
        int resId = 0;
        switch (spUtils.getInt(ConstantUtils.SP_PLAY_TYPE_KEY, 0)) {
            case ConstantUtils.PLAY_MODE_LOOP_ALL_CODE:
                spUtils.put(ConstantUtils.SP_PLAY_TYPE_KEY, ConstantUtils.PLAY_MODE_LOOP_SINGLE_CODE);
                resId = R.drawable.loop_single_black;
                break;
            case ConstantUtils.PLAY_MODE_LOOP_SINGLE_CODE:
                spUtils.put(ConstantUtils.SP_PLAY_TYPE_KEY, ConstantUtils.PLAY_MODE_SHUFFLE_CODE);
                resId = R.drawable.shuffle_black;
                break;
            case ConstantUtils.PLAY_MODE_SHUFFLE_CODE:
                spUtils.put(ConstantUtils.SP_PLAY_TYPE_KEY, ConstantUtils.PLAY_MODE_LOOP_ALL_CODE);
                resId = R.drawable.ic_loop_all_black;
                break;
        }
        callback.onPlayType(resId);
    }

}
