package com.example.howard.mp3player.Internet;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.howard.mp3player.Bean.SongRankingBean;
import com.example.howard.mp3player.Injection;
import com.example.howard.mp3player.R;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongRankingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_ranking);


    }

    @Override
    protected void onStart() {
        super.onStart();


        Call<SongRankingBean> call = Injection.provideSongRankingAPI().getSongRanking("json","","webapp_music","baidu.ting.billboard.billList",1,10,0);
        call.enqueue(new Callback<SongRankingBean>() {
            @Override
            public void onResponse(Call<SongRankingBean> call, Response<SongRankingBean> response) {

                SongRankingBean bean = response.body();
                Log.d("bean", bean.toString());
            }

            @Override
            public void onFailure(Call<SongRankingBean> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
