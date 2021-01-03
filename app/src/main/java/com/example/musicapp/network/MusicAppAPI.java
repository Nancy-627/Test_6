package com.example.musicapp.network;

import com.example.musicapp.recommend.RecommendSongListResponse;
import com.example.musicapp.response.banner.BannerResponse;
import com.example.musicapp.response.comment.CommentResponse;
import com.example.musicapp.response.detail.DetailResponse;
import com.example.musicapp.response.login.LoginResponse;
import com.example.musicapp.response.playlist.PlaylistDetailResponse;
import com.example.musicapp.response.search.SearchResponse;
import com.example.musicapp.response.search.SongResponse;
import com.example.musicapp.response.search.artist.ArtistResponse;
import com.example.musicapp.response.search.hot.HotResponse;
import com.example.musicapp.response.search.song.SongDetailResponse;
import com.example.musicapp.response.search.video.VideoResponse;
import com.example.musicapp.util.ConstantUtils;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface MusicAppAPI {
    @GET(ConstantUtils.CELLPHONE_API)
    Observable<LoginResponse> loginByPhone(@Query("phone") String phone, @Query("password") String password);

    @GET(ConstantUtils.USER_DETAIL_API)
    Observable<DetailResponse> detail(@Query("uid") String uid);

    @GET(ConstantUtils.SEARCH_API)
    Observable<SearchResponse> search(@Query("keywords") String keywords);

    @GET(ConstantUtils.SEARCH_API)
    Observable<SearchResponse> search(@Query("keywords") String keywords, @Query("offset") int offset); //page

    @GET(ConstantUtils.COMMENT_API)
    Observable<CommentResponse> comment(@Query("id") int id);

    @GET(ConstantUtils.COMMENT_API)
    Observable<CommentResponse> comment(@Query("id") int id, @Query(value = "limit") int limit);

    @GET(ConstantUtils.SEARCH_HOT_API)
    Observable<HotResponse> searchHot();

    @GET(ConstantUtils.SEARCH_API + "?type=1014")
    Observable<VideoResponse> searchVideo(@Query("keywords") String keywords);

    @GET(ConstantUtils.SEARCH_API + "?type=100")
    Observable<ArtistResponse> searchArtist(@Query("keywords") String keyword);

    @GET(ConstantUtils.SONG_URL_API)
    Observable<SongResponse> getSongUrl(@Query("id") int id);

    @GET(ConstantUtils.SONG_DETAIL_API)
    Observable<SongDetailResponse> getSongDetail(@Query("ids") int ids);

    @GET(ConstantUtils.BANNER_API)
    Observable<BannerResponse> getBanner();

    @GET(ConstantUtils.RECOMMEND_SONG_LIST_API)
    Observable<RecommendSongListResponse> getRecommendSongList(@Header("Cookie") String cookie);

    @GET(ConstantUtils.PLAYLIST_DETAIL_API)
    Observable<PlaylistDetailResponse> getPlaylistDetail(@Query("id") long id);
}
