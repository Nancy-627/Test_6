package com.example.musicapp.presenter.impl;

import com.blankj.utilcode.util.Utils;
import com.example.musicapp.R;
import com.example.musicapp.model.CommentModel;
import com.example.musicapp.model.impl.CommentModelImpl;
import com.example.musicapp.network.MusicAppService;
import com.example.musicapp.presenter.CommentPresenter;
import com.example.musicapp.response.comment.CommentResponse;
import com.example.musicapp.view.CommentView;

public class CommentPresenterImpl implements CommentPresenter, CommentModel.Callback {
    private CommentView mCommentView;
    private CommentModel mCommentModel;
    private MusicAppService mNetEaseMusicService;

    public CommentPresenterImpl(CommentView commentView) {
        this.mCommentView = commentView;
        this.mCommentModel = new CommentModelImpl(mNetEaseMusicService);
    }

    public CommentPresenterImpl(MusicAppService netEaseMusicService, CommentView commentView) {
        this.mNetEaseMusicService = netEaseMusicService;
        this.mCommentView = commentView;
        this.mCommentModel = new CommentModelImpl(mNetEaseMusicService);
    }

    @Override
    public void loadComment(int id) {
        mCommentModel.loadComment(id, this);
        mCommentView.showProgress();
    }

    @Override
    public void onLoaded(CommentResponse commentResponse) {
        String title = Utils.getApp().getResources().getString(R.string.commment) + "(" + commentResponse.getTotal() + ")";
        mCommentView.updateTitle(title);
        mCommentView.onLoaded(commentResponse);
        mCommentView.hideProgress();
    }

    @Override
    public void onFailed() {
        mCommentView.hideProgress();
    }
}
