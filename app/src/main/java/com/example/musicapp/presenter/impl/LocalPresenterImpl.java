package com.example.musicapp.presenter.impl;

import android.util.Log;

import com.blankj.utilcode.util.Utils;

import com.example.musicapp.R;
import com.example.musicapp.adapter.BaseViewHolder;
import com.example.musicapp.adapter.LocalAlbumViewHolder;
import com.example.musicapp.adapter.LocalArtistViewHolder;
import com.example.musicapp.adapter.LocalSongHeaderViewHolder;
import com.example.musicapp.adapter.LocalSongPlayingViewHolder;
import com.example.musicapp.adapter.LocalSongViewHolder;
import com.example.musicapp.model.LocalModel;
import com.example.musicapp.model.entities.Album;
import com.example.musicapp.model.entities.Artist;
import com.example.musicapp.model.entities.Single;
import com.example.musicapp.model.impl.LocalModelImpl;
import com.example.musicapp.presenter.LocalPresenter;
import com.example.musicapp.util.ConstantUtils;
import com.example.musicapp.util.TagUtils;
import com.example.musicapp.view.LocalView;

import java.util.ArrayList;
import java.util.List;



public class LocalPresenterImpl implements LocalPresenter, LocalModel.Callback {
    private String TAG = TagUtils.getTag(this.getClass());
    private LocalView mLocalView;
    private LocalModel mLocalModel;

    private List<Single> singles = new ArrayList<>();
    private List<Artist> artists = new ArrayList<>();
    private List<Album> albums = new ArrayList<>();

    private int currentSongPosition;

    public LocalPresenterImpl(LocalView mLocalView) {
        this.mLocalView = mLocalView;
        mLocalModel = new LocalModelImpl();
    }

    @Override
    public void onLoadedSong(String songUrl) {
        mLocalView.play(songUrl);
        mLocalView.updateMiniController();

    }

    @Override
    public void onLoadedAllSingle(List<Single> singles) {
        this.singles = singles;
        mLocalView.hideProgress();
        mLocalView.updateSongList();

    }

    @Override
    public void onLoadedAllAlbum(List<Album> albums) {
        this.albums = albums;
        mLocalView.updateAlbumList();
    }

    @Override
    public void onLoadAllArtist(List<Artist> artists) {
        this.artists = artists;
        mLocalView.updateArtistList();
    }

    @Override
    public void load() {
        mLocalView.showProgress();
        mLocalModel.loadAllSingle(this);
        mLocalModel.loadAllArtist(this);
        mLocalModel.loadAllAlbum(this);
    }

    @Override
    public void query(String keyword) {
        mLocalModel.query(keyword, this);
    }

    @Override
    public void loadSong(Single single) {
        mLocalModel.loadSong(single, this);
    }

    @Override
    public int getSongItemCount() {
        return singles.size() + 1;
    }

    @Override
    public int getArtistItemCount() {
        return artists.size() + 1;
    }

    @Override
    public int getAlbumItemCount() {
        return albums.size() + 1;
    }

    @Override
    public void onBindSongRowViewAtPosition(int position, BaseViewHolder holder) {
        String trackCountTitle = "(" + singles.size()  + " " + Utils.getApp().getResources().getString(R.string.tracks) + ")";
        if (holder instanceof LocalSongHeaderViewHolder) {
            ((LocalSongHeaderViewHolder) holder).setTrackTitle(trackCountTitle);
        } else if (holder instanceof LocalSongViewHolder) {
            ((LocalSongViewHolder) holder).setSongTitle(singles.get(position - 1).getTitle());
            ((LocalSongViewHolder) holder).setSongArtist(singles.get(position - 1).getArtist());

        } else if (holder instanceof LocalSongPlayingViewHolder) {
            Log.d(TAG, "onBindSongRowViewAtPosition: " + "playing");
            ((LocalSongPlayingViewHolder) holder).setSongTitle(singles.get(position - 1).getTitle());
            ((LocalSongPlayingViewHolder) holder).setSongArtist(singles.get(position - 1).getArtist());
            ((LocalSongPlayingViewHolder)holder).setSongTitleColor(Utils.getApp().getResources().getColor(R.color.red));

        }

    }

    @Override
    public void onBindArtistRowViewAtPosition(int position, BaseViewHolder holder) {
        ((LocalArtistViewHolder) holder).setArtistName(artists.get(position).getName());
        String songCountTitle = artists.get(position).getSongCount() + "" + Utils.getApp().getResources().getString(R.string.songs);
        ((LocalArtistViewHolder) holder).setSongCountText(songCountTitle);
        ((LocalArtistViewHolder) holder).setProfile(artists.get(position).getProfile());

    }

    @Override
    public void onBindAlbumRowViewAtPosition(int position, BaseViewHolder holder) {
        ((LocalAlbumViewHolder) holder).setAlbumCover(albums.get(position).getAlbumCoverUri());
        ((LocalAlbumViewHolder) holder).setAlbumTitle(albums.get(position).getTitle());
        ((LocalAlbumViewHolder) holder).setAlbumSongCount(albums.get(position).getSongCount() + Utils.getApp().getResources().getString(R.string.songs));
        ((LocalAlbumViewHolder) holder).setArtistName(albums.get(position).getArtistName());
    }

    @Override
    public void onSongItemClickedAtPosition(int position) {
        if (position == currentSongPosition) {
            mLocalView.navigatePlay();
        } else {
            currentSongPosition = position;
            loadSong(singles.get(position - 1));
            mLocalView.updateSongList();
        }

    }

    @Override
    public void onArtistItemClickAtPosition(int position) {

    }

    @Override
    public int getCurrentSongPosition() {
        return currentSongPosition;
    }

    @Override
    public int getItemViewTypeOfSingle(int position) {
        if (position == 0) {
            return ConstantUtils.SINGLE_TYPE_HEADER;
        } else if (position == getCurrentSongPosition()) {
            return ConstantUtils.SINGLE_TYPE_IS_PLAYING;
        } else {
            return ConstantUtils.SINGLE_TYPE_IS_NORMAL;
        }
    }
}
