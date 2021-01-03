package com.example.musicapp.view;

import com.example.musicapp.bean.MusicMenu;
import com.example.musicapp.model.entities.NavProfile;
import com.example.musicapp.response.banner.BannersItem;
import com.example.musicapp.response.detail.DetailResponse;
import com.example.musicapp.response.recommend.RecommendSongItem;

import java.util.List;

public interface MainView extends BaseView {

    void updateNavProfile(NavProfile navProfile);

    void updateDetail(DetailResponse detailResponse);

    void updateMusicMenu(List<MusicMenu> musicMenus);

    void updateMiniCoverAndTitle();

    void setUpNavHeader();

    void navigateToLocalView();

    void navigateToRecentPlayView();

    void navigateToDownloadsView();

    void navigateToStationsView();

    void navigateToFavorites();

    void navigatePlaylist(RecommendSongItem recommendSongItem);

    void showNetworkError(String errorMsg);

    void showBanner(List<BannersItem> bannersItems);

    void showRecommendSongList(List<RecommendSongItem> recommendSongItems);
}
