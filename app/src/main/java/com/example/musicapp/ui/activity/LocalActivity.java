package com.example.musicapp.ui.activity;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.musicapp.R;
import com.example.musicapp.adapter.LocalCategoryPagerAdapter;
import com.example.musicapp.model.entities.AlbumEvent;
import com.example.musicapp.model.entities.ArtistEvent;
import com.example.musicapp.model.entities.PlayActivityEvent;
import com.example.musicapp.model.entities.PlaySingleEvent;
import com.example.musicapp.model.entities.SongEvent;
import com.example.musicapp.presenter.LocalPresenter;
import com.example.musicapp.presenter.impl.LocalPresenterImpl;
import com.example.musicapp.util.ConstantUtils;
import com.example.musicapp.util.MusicUtils;
import com.example.musicapp.util.TagUtils;
import com.example.musicapp.view.LocalView;

public class LocalActivity extends AppCompatActivity implements LocalView {

    private String TAG = TagUtils.getTag(this.getClass());
    @BindView(R.id.tl_local_category)
    TabLayout tlLocalCategory;

    @BindView(R.id.vp_local_files)
    ViewPager vpLocalFiles;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.pb_local_loading)
    ProgressBar pbLocalLoading;

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

    public static LocalPresenter mLocalPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        ButterKnife.bind(this);
        initToolbar();
        vpLocalFiles.setOffscreenPageLimit(4);
        vpLocalFiles.setAdapter(new LocalCategoryPagerAdapter(getSupportFragmentManager(), this));
        findViewById(R.id.sliding_layout).setEnabled(false);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        tlLocalCategory.setupWithViewPager(vpLocalFiles);
        setUpMVP();

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateMiniController();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_local, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint(getResources().getString(R.string.searching_local_file));

        searchView.setOnCloseListener(null);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                LogUtils.d(TAG, newText);
                mLocalPresenter.query(newText);
                return false;
            }
        });

        return true;

    }

    @Override
    public void setUpMVP() {
        mLocalPresenter = new LocalPresenterImpl(this);
        mLocalPresenter.load();
    }

    @Override
    public void showProgress() {
        pbLocalLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLocalLoading.setVisibility(View.GONE);
    }

    @Override
    public void updateSongList() {
        EventBus.getDefault().post(new SongEvent());
    }

    @Override
    public void updateArtistList() {
        EventBus.getDefault().post(new ArtistEvent());
    }

    @Override
    public void updateAlbumList() {
        EventBus.getDefault().post(new AlbumEvent());
    }

    @Override
    public void navigatePlay() {
        startActivity(new Intent(this, PlayActivity.class));
    }

    @Override
    public void play(String songUrl) {
        Intent intent = new Intent(ConstantUtils.ACTION_PLAY);
        intent.putExtra(ConstantUtils.MUSIC_URI_KEY, songUrl);
        sendBroadcast(intent);
    }

    @Override
    public void updateMiniController() {
        Glide.with(this).load(MusicUtils.readAlbumCover()).into(ivControllerAlbumCover);
        tvControllerTitle.setText(MusicUtils.readSongName());
        tvControllerLyric.setText(MusicUtils.readArtistAndAlbum());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSingleEvent(PlaySingleEvent event) {
        mLocalPresenter.loadSong(event.getSingle());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPlayActivityEvent(PlayActivityEvent event) {
        navigatePlay();
    }
}
