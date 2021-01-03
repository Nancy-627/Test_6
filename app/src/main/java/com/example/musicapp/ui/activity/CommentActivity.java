package com.example.musicapp.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.LogUtils;
import com.example.musicapp.BaseApp;
import com.example.musicapp.R;
import com.example.musicapp.adapter.CommentAdapter;
import com.example.musicapp.network.MusicAppService;
import com.example.musicapp.presenter.CommentPresenter;
import com.example.musicapp.presenter.impl.CommentPresenterImpl;
import com.example.musicapp.response.comment.CommentResponse;
import com.example.musicapp.util.ConstantUtils;
import com.example.musicapp.util.TagUtils;
import com.example.musicapp.view.CommentView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentActivity extends BaseApp implements CommentView {
    private String TAG = TagUtils.getTag(this.getClass());
    private CommentPresenter mCommentPresenter;

    @BindView(R.id.rv_comment)
    RecyclerView rvComment;

    @BindView(R.id.pb_comment)
    ProgressBar pbComment;

    @Inject
    MusicAppService mNetEaseMusicService;

    private CommentAdapter commentAdapter;

    private int songId;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);


        rvComment.setLayoutManager(new LinearLayoutManager(this));
        rvComment.setAdapter(commentAdapter= new CommentAdapter());

        songId = getIntent().getIntExtra(ConstantUtils.MUSIC_ID_KEY, 0);
        setUpMVP();
    }

    @Override
    public void setUpMVP() {
        LogUtils.d(TAG, mNetEaseMusicService == null);
        mCommentPresenter = new CommentPresenterImpl(mNetEaseMusicService, this);
        mCommentPresenter.loadComment(songId);
    }

    @Override
    public void onLoaded(CommentResponse commentResponse) {
        LogUtils.d(TAG, commentResponse);
        LogUtils.d(TAG, "hotComments is " + commentResponse.getHotComments().size() + "comments is " + commentResponse.getComments().size());
        commentAdapter.setHotComments(commentResponse.getHotComments());
        commentAdapter.setComments(commentResponse.getComments());
        commentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        pbComment.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbComment.setVisibility(View.GONE);
    }

    @Override
    public void updateTitle(String title) {
        setTitle(title);
    }
}