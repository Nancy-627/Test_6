package com.example.musicapp.model.impl;

import com.blankj.utilcode.util.LogUtils;
import com.example.musicapp.model.CommentModel;
import com.example.musicapp.network.MusicAppService;
import com.example.musicapp.response.comment.CommentResponse;
import com.example.musicapp.util.TagUtils;

public class CommentModelImpl implements CommentModel {
    private String TAG = TagUtils.getTag(this.getClass());
    private MusicAppService mNetEaseMusicService;

    public CommentModelImpl(MusicAppService mNetEaseMusicService) {
        this.mNetEaseMusicService = mNetEaseMusicService;
    }

    @Override
    public void loadComment(int id, Callback callback) {
        LogUtils.d(TAG, id);
        mNetEaseMusicService.comment(id, new MusicAppService.Callback<CommentResponse>() {
            @Override
            public void onSuccess(CommentResponse data) {
                LogUtils.d(TAG, data);
                callback.onLoaded(data);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(TAG, e.fillInStackTrace());
                callback.onFailed();
            }
        });
    }

}