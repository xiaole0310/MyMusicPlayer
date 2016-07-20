package com.example.howard.mp3player.Internet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.howard.mp3player.LocalSongInfo.TabSongActivity;
import com.example.howard.mp3player.R;

public class InternetMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internet_main);
    }

    public void ClickToRanking(View v){
        Intent intent =new Intent(InternetMain.this,SongRankingMainActivity.class);
        startActivity(intent);
    }
    public void ClickToSearch (View v){
        Intent intent =new Intent(InternetMain.this,InternetSearchTab.class);
        startActivity(intent);
    }
}
