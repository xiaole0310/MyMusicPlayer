package com.example.howard.mp3player.LocalSongInfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.howard.mp3player.R;

public class LocalSong extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local_song);
    }

    public void ClickToMyLocal(View v){
        Intent intent =new Intent(LocalSong.this,TabSongActivity.class);
        startActivity(intent);
    }
}
