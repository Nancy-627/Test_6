package com.example.musicapp.network;

import android.telecom.Call;

import com.example.musicapp.model.AccountModel;
import com.example.musicapp.recommend.RecommendSongListResponse;
import com.example.musicapp.response.banner.BannerResponse;
import com.example.musicapp.response.comment.CommentResponse;
import com.example.musicapp.response.detail.DetailResponse;
import com.example.musicapp.response.login.LoginResponse;
import com.blankj.utilcode.util.LogUtils;
import com.example.musicapp.response.playlist.PlaylistDetailResponse;
import com.example.musicapp.response.search.SearchResponse;
import com.example.musicapp.response.search.SongResponse;
import com.example.musicapp.response.search.artist.ArtistResponse;
import com.example.musicapp.response.search.hot.HotResponse;
import com.example.musicapp.response.search.song.SongDetailResponse;
import com.example.musicapp.response.search.video.VideoResponse;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.security.PublicKey;



import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.Data;

@Data
public class MusicAppService {
    private MusicAppAPI netEaseMusicAPI;

    public MusicAppService(MusicAppAPI netEaseMusicAPI) {
        this.netEaseMusicAPI = netEaseMusicAPI;
    }

    public void login(String phone, String password, final Callback callback) {
        netEaseMusicAPI.loginByPhone(phone, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        callback.onSuccess(loginResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void detail(String uid, final Callback callback) {
        netEaseMusicAPI.detail(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailResponse detailResponse) {
                        callback.onSuccess(detailResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void search(String keywords, final Callback callback) {
        netEaseMusicAPI.search(keywords).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchResponse searchResponse) {
                        callback.onSuccess(searchResponse);

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void search(String keywords, int offset, final Callback callback) {
        netEaseMusicAPI.search(keywords, offset).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SearchResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SearchResponse searchResponse) {
                callback.onSuccess(searchResponse);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void comment(int id, final Callback callback) {
        netEaseMusicAPI.comment(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommentResponse commentResponse) {
                        callback.onSuccess(commentResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void searchHot(final Callback callback) {
        netEaseMusicAPI.searchHot().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotResponse hotResponse) {
                        if (hotResponse.getCode() == 200) {
                            callback.onSuccess(hotResponse);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void searchVideo(String keywords, final Callback callback) {
        netEaseMusicAPI.searchVideo(keywords).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideoResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VideoResponse videoResponse) {
                        callback.onSuccess(videoResponse);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void searchArtist(String keyword, final Callback callback) {
        netEaseMusicAPI.searchArtist(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArtistResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArtistResponse artistResponse) {
                        callback.onSuccess(artistResponse);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getSongUrl(int id, final Callback callback) {
        netEaseMusicAPI.getSongUrl(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SongResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SongResponse songResponse) {
                        callback.onSuccess(songResponse);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getSongDetail(int ids, final Callback callback) {
        netEaseMusicAPI.getSongDetail(ids)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SongDetailResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SongDetailResponse songDetailResponse) {
                        callback.onSuccess(songDetailResponse);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getBanner(final Callback callback) {
        netEaseMusicAPI.getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BannerResponse bannerResponse) {
                        callback.onSuccess(bannerResponse);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getRecommendSongList(String cookie, final Callback callback) {
        netEaseMusicAPI.getRecommendSongList(cookie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecommendSongListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RecommendSongListResponse recommendSongListResponse) {
                        callback.onSuccess(recommendSongListResponse);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getPlaylistDetail(long id, final Callback callback) {
        netEaseMusicAPI.getPlaylistDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlaylistDetailResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(PlaylistDetailResponse playlistDetailResponse) {
                        callback.onSuccess(playlistDetailResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d("onError", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public interface Callback<T> {
        void onSuccess(T data);

        void onError(Throwable e);
    }
}
