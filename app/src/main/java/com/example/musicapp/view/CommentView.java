package com.example.musicapp.view;

import com.example.musicapp.response.comment.CommentResponse;

public interface CommentView extends BaseView{
    void onLoaded(CommentResponse commentResponse);

    void updateTitle(String title);
}
