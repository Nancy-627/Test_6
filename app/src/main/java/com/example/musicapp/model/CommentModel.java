package com.example.musicapp.model;

import com.example.musicapp.response.comment.CommentResponse;

public interface CommentModel {
    void loadComment(int id, Callback callback);

    interface Callback{
        void onLoaded(CommentResponse commentResponse);

        void onFailed();
    }
}
