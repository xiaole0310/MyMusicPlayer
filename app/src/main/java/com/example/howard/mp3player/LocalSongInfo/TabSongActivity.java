package com.example.howard.mp3player.LocalSongInfo;


import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.howard.mp3player.MainActivity;
import com.example.howard.mp3player.MyApplication;
import com.example.howard.mp3player.R;
import com.example.howard.mp3player.Service.MusicPlayerService;

import java.io.IOException;

public class TabSongActivity extends TabActivity implements RadioGroup.OnCheckedChangeListener{

    public ImageView backview;
    public TabHost tabHost;
    public RadioGroup radioGroup;
    public RadioButton radioButtonLocal, radioButtonSinger, radioButtonFolder;
    public ImageButton play;
    public ImageButton pre;
    public ImageButton next;
    public ImageButton song;
    public TextView song_name;
    public TextView singer_name;
    public LinearLayout tab_mini;
    private MyApplication myApplication;
    private MusicPlayerService musicPlayerService;
    private String url;
    public MyHandler myHandler;
    private final static int CHANGE_TO_PLAY = 111;
    private final static int CHANG_TO_PAUSE=112;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_song);
        myApplication= (MyApplication) getApplication();
        myHandler=new MyHandler();
        myApplication.setTabSongActivity(this);
        setView();
//        backview= (ImageView) findViewById(R.id.back);
//        backview.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(TabSongActivity.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });
        song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TabSongActivity.this,PlayActivity.class);
                startActivity(intent);
            }
        });
        tab_mini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TabSongActivity.this,PlayActivity.class);
                startActivity(intent);
            }
        });
        tabHost=getTabHost();
        tabHost.addTab(tabHost.newTabSpec("L").setIndicator("L")
                .setContent(new Intent(TabSongActivity.this, SongInfoLocal.class)));
        tabHost.addTab(tabHost.newTabSpec("S").setIndicator("S")
                .setContent(new Intent(TabSongActivity.this, SongInfoSingerMain.class)));
        tabHost.addTab(tabHost.newTabSpec("F").setIndicator("F")
                .setContent(new Intent(TabSongActivity.this, SongInfoFolderMain.class)));
        tabHost.setCurrentTab(0);
        radioGroup.setOnCheckedChangeListener(this);
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        myApplication.setTabSongActivity(this);
        song_name.setText(myApplication.songname);
        singer_name.setText(myApplication.singername);
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
    public void setView(){
        radioGroup= (RadioGroup) findViewById(R.id.songinfo_radiogroup);
        radioButtonLocal= (RadioButton) findViewById(R.id.radioButtonLocal);
        radioButtonSinger= (RadioButton) findViewById(R.id.radioButtonSinger);
        radioButtonFolder= (RadioButton) findViewById(R.id.radioButtonFolder);
        pre= (ImageButton) findViewById(R.id.pre);
        play= (ImageButton) findViewById(R.id.play_or_pause);
        next= (ImageButton) findViewById(R.id.next);
        song= (ImageButton) findViewById(R.id.mini_image);
        song_name= (TextView) findViewById(R.id.song_name);
        singer_name= (TextView) findViewById(R.id.singer_name);
        tab_mini= (LinearLayout) findViewById(R.id.song);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioButtonLocal:
                tabHost.setCurrentTabByTag("L");
                break;
            case R.id.radioButtonSinger:
                tabHost.setCurrentTabByTag("S");
                break;
            case R.id.radioButtonFolder:
                tabHost.setCurrentTabByTag("F");
                break;

            default:
                break;
        }
    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认在列表中删除吗？");

        builder.setTitle("不会删除本地歌曲");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
    public  class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case CHANGE_TO_PLAY:
                    changeToPlay();
                    break;
                case CHANG_TO_PAUSE:
                    changeToPause();
                    break;
                    }

        }


    }
}
