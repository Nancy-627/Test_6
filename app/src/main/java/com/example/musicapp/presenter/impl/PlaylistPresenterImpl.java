package com.example.musicapp.presenter.impl;

import com.example.musicapp.model.PlaylistModel;
import com.example.musicapp.model.impl.PlaylistModelIMpl;
import com.example.musicapp.presenter.PlaylistPresenter;
import com.example.musicapp.response.playlist.Playlist;
import com.example.musicapp.response.recommend.RecommendSongItem;
import com.example.musicapp.view.PlaylistDetailView;

public class PlaylistPresenterImpl implements PlaylistPresenter, PlaylistModel.Callback {
    PlaylistDetailView mPlaylistDetailView;
    PlaylistModel mPlaylistModel;
    public PlaylistPresenterImpl(PlaylistDetailView playlistDetailView) {
        this.mPlaylistDetailView = playlistDetailView;
        this.mPlaylistModel = new PlaylistModelIMpl();
    }

    @Override
    public void getPlaylist(RecommendSongItem recommendSongItem) {
        //todo
        mPlaylistModel.getPlaylistDetail(/*recommendSongItem.getId()*/recommendSongItem.getId(), this);
    }

    @Override
    public void onPlaylistDetail(Playlist playlist) {
        mPlaylistDetailView.showPlaylist(playlist);
    }
}
