package com.example.howard.mp3player.InterAPItools;

import com.example.howard.mp3player.Bean.SingerSongBean;
import com.example.howard.mp3player.Bean.SongRankingBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Howard on 2016/7/11.
 */
public interface SongAPI {
    String BaseURL = "http://tingapi.ting.baidu.com/";

    @GET("v1/restserver/ting")
    Call<SongRankingBean> getSongRanking (@Query("format") String format, @Query("callback")String callback, @Query("from") String from,
                                          @Query("method") String method , @Query("type") int type, @Query("size") int size, @Query("offset") int offset);



}
