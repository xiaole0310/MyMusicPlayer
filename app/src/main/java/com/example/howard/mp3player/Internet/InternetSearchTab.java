package com.example.howard.mp3player.Internet;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.howard.mp3player.LocalSongInfo.PlayActivity;
import com.example.howard.mp3player.LocalSongInfo.SongInfoFolderMain;
import com.example.howard.mp3player.LocalSongInfo.SongInfoLocal;
import com.example.howard.mp3player.LocalSongInfo.SongInfoSingerMain;
import com.example.howard.mp3player.MyApplication;
import com.example.howard.mp3player.R;
import com.example.howard.mp3player.Service.MusicPlayerService;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class InternetSearchTab extends TabActivity implements RadioGroup.OnCheckedChangeListener{

    public TabHost tabHost;
    public RadioGroup radioGroup;
    public RadioButton radioButtonbysong, radioButtonbysinger;
    public ImageButton play;
    public ImageButton pre;
    public ImageButton next;
    public ImageButton song;
    public TextView song_name;
    public TextView singer_name;
    private MyApplication myApplication;
    private MusicPlayerService musicPlayerService;
    private String url;
    public InterHandler interHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internet_search_tab);
        setView();
        myApplication= (MyApplication) getApplication();
        musicPlayerService=new MusicPlayerService();
        interHandler=new InterHandler();

        tabHost=getTabHost();
        tabHost.addTab(tabHost.newTabSpec("O").setIndicator("O")
                .setContent(new Intent(InternetSearchTab.this,SongSearchActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("I").setIndicator("I")
                .setContent(new Intent(InternetSearchTab.this,SingerSongSearchActivity.class)));
        tabHost.setCurrentTab(0);
        radioGroup.setOnCheckedChangeListener(this);
        song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InternetSearchTab.this,PlayActivity.class);
                startActivity(intent);
            }
        });
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    preClick();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playClick();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    nextClick();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        musicPlayerService = myApplication.musicPlayerService;
        if (musicPlayerService!=null&&musicPlayerService.mediaPlayer!=null){
            if (musicPlayerService.mediaPlayer.isPlaying()){
                changeToPause();
            }
        }
        myApplication.setInternetSearchTab(this);
    }

    public void setView(){
        radioGroup= (RadioGroup) findViewById(R.id.search_radiogroup);
        radioButtonbysong= (RadioButton) findViewById(R.id.radioButtonbysong);
        radioButtonbysinger= (RadioButton) findViewById(R.id.radioButtonbySinger);
        pre= (ImageButton) findViewById(R.id.inter_search_pre);
        play= (ImageButton) findViewById(R.id.inter_search_play_or_pause);
        next= (ImageButton) findViewById(R.id.inter_search_next);
        song= (ImageButton) findViewById(R.id.inter_search_mini_image);
        song_name= (TextView) findViewById(R.id.inter_search_song_name);
        singer_name= (TextView) findViewById(R.id.inter_search_singer_name);
    }

    public void preClick() throws IOException {
        musicPlayerService = myApplication.musicPlayerService;

        if (musicPlayerService != null) {
            if (!musicPlayerService.ifintersong) {
                musicPlayerService.pesition = musicPlayerService.pesition - 1;
                if (musicPlayerService.pesition != -1) {
                    musicPlayerService.callMedia(musicPlayerService.pesition);
                    url = musicPlayerService.url;
                    musicPlayerService.play(url);
                    changeToPause();
                } else {
                    musicPlayerService.pesition = musicPlayerService.list.size() - 1;
                    musicPlayerService.callMedia(musicPlayerService.pesition);
                    url = musicPlayerService.url;
                    musicPlayerService.play(url);
                    changeToPause();
                }
            } else {
                musicPlayerService.pesition = musicPlayerService.pesition - 1;
                if (musicPlayerService.pesition != -1) {
                    musicPlayerService.callInterMedia(musicPlayerService.pesition);
                    changeToPause();
                } else {
                    musicPlayerService.pesition =myApplication.songidList.size()-1;
                    musicPlayerService.callInterMedia(musicPlayerService.pesition);
                    changeToPause();
                }

            }
        }
    }
    public void playClick(){
        musicPlayerService=myApplication.musicPlayerService;
        if(musicPlayerService!=null){
            if(musicPlayerService.mediaPlayer.isPlaying()) {
                musicPlayerService.pause();
                play.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.mini_play));
                changeToPlay();
            }else {

                musicPlayerService.restart();
                play.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.mini_pause));
                changeToPause();
            }
        }
    }

    public void nextClick() throws IOException {
        musicPlayerService = myApplication.musicPlayerService;
        if (musicPlayerService != null) {
            if (!musicPlayerService.ifintersong) {
                musicPlayerService.pesition = musicPlayerService.pesition + 1;
                if (musicPlayerService.pesition != musicPlayerService.list.size()) {
                    musicPlayerService.callMedia(musicPlayerService.pesition);
                    url = musicPlayerService.url;
                    musicPlayerService.play(url);
                    changeToPause();
                } else {
                    musicPlayerService.pesition = 0;
                    musicPlayerService.callMedia(musicPlayerService.pesition);
                    url = musicPlayerService.url;
                    musicPlayerService.play(url);
                    changeToPause();
                }
            } else {
                musicPlayerService.pesition = musicPlayerService.pesition + 1;
                if (musicPlayerService.pesition != myApplication.songidList.size()) {
                    musicPlayerService.callInterMedia(musicPlayerService.pesition);
                    changeToPause();
                } else {
                    musicPlayerService.pesition = 0;
                    musicPlayerService.callInterMedia(musicPlayerService.pesition);
                    changeToPause();
                }
            }
        }
    }

    public void changeToPlay(){
        play.setBackground(this.getResources().getDrawable(R.drawable.mini_play));
    }
    public void changeToPause(){
        play.setBackground(this.getResources().getDrawable(R.drawable.mini_pause));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioButtonbysong:
                tabHost.setCurrentTabByTag("O");
                break;
            case R.id.radioButtonbySinger:
                tabHost.setCurrentTabByTag("I");
                break;

            default:
                break;
        }
    }

    public static final int CHANG_TO_PLAY=321;
    public static final int CHANG_TO_PAUSE=322;
    public class InterHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CHANG_TO_PAUSE:
                    changeToPause();
                    break;
                case CHANG_TO_PLAY:
                    changeToPlay();
                    break;

            }

        }
    }
}
