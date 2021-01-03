package com.example.musicapp.presenter.impl;

import android.os.Bundle;

import com.example.musicapp.bean.MusicMenuIndexEvent;
import com.example.musicapp.model.HotModel;
import com.example.musicapp.model.MainModel;
import com.example.musicapp.model.MusicMenuModel;
import com.example.musicapp.model.entities.NavProfile;
import com.example.musicapp.model.impl.HotModelImpl;
import com.example.musicapp.model.impl.MainModelImpl;
import com.example.musicapp.model.impl.MusicMenuModelImpl;
import com.example.musicapp.network.MusicAppService;
import com.example.musicapp.presenter.MainPresenter;
import com.example.musicapp.response.banner.BannersItem;
import com.example.musicapp.response.detail.DetailResponse;
import com.example.musicapp.response.recommend.RecommendSongItem;
import com.example.musicapp.util.TagUtils;
import com.example.musicapp.view.MainView;

import java.util.List;

public class MainPresenterImpl implements MainPresenter, MainModel.MainCallback, MusicMenuModel.MusicMenuCallback, HotModel.Callback {
    private String TAG = TagUtils.getTag(this.getClass());
    private MainView mainView;
    private MainModel mainModel;
    private MusicMenuModel musicMenuModel;
    private HotModel mHotModel;


    public MainPresenterImpl(MusicAppService netEaseMusicService, MainView mainView) {
        this.mainView = mainView;
        this.mainModel = new MainModelImpl(netEaseMusicService);
        musicMenuModel = new MusicMenuModelImpl();
        mHotModel = new HotModelImpl();
    }

    @Override
    public void update(Bundle bundle) {
        mainModel.update(bundle, this);
    }

    @Override
    public void musicMenuNavigate(MusicMenuIndexEvent musicMenuIndexEvent) {
        musicMenuModel.navigate(musicMenuIndexEvent,this);
    }

    @Override
    public void getBanner() {
        mHotModel.getBanner(this);
    }

    @Override
    public void getRecommendSongList() {
        mHotModel.getRecommendSongList(this);
    }

    @Override
    public void updateProfile(NavProfile navProfile) {
        mainView.updateNavProfile(navProfile);
    }

    @Override
    public void updateDetail(DetailResponse detailResponse) {
        mainView.updateDetail(detailResponse);
    }

    @Override
    public void showNetworkError(String errorMsg) {
        mainView.showNetworkError(errorMsg);
    }


    @Override
    public void navigateToLocalView() {
        mainView.navigateToLocalView();
    }

    @Override
    public void navigateToRecentView() {
        mainView.navigateToRecentPlayView();
    }

    @Override
    public void navigateToDownloadsView() {
        mainView.navigateToDownloadsView();
    }

    @Override
    public void navigateToStationsView() {
        mainView.navigateToStationsView();
    }

    @Override
    public void navigateToFavorites() {
        mainView.navigateToFavorites();
    }

    @Override
    public void onBanner(List<BannersItem> bannersItems) {
        mainView.showBanner(bannersItems);
    }

    @Override
    public void onRecommendSongList(List<RecommendSongItem> recommendSongItems) {
        mainView.showRecommendSongList(recommendSongItems);
    }
}
