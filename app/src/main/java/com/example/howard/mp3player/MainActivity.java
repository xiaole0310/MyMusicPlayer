package com.example.howard.mp3player;

import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.howard.mp3player.Internet.InternetMain;
import com.example.howard.mp3player.Internet.SongRankingListActivity;
import com.example.howard.mp3player.Internet.SongRankingMainActivity;
import com.example.howard.mp3player.LocalSongInfo.LocalSong;
import com.example.howard.mp3player.LocalSongInfo.PlayActivity;
import com.example.howard.mp3player.Service.MusicPlayerService;

import java.io.IOException;
import java.util.List;

/**
 * 微信公众号：(ID：study_tech)
 * Created by 杨守乐 on 2016/9/14.
 * Email：ysle0313@gmail.com
 * QQ:1733563441
 * 技术Q群：279126311
 */

public class MainActivity extends TabActivity implements RadioGroup.OnCheckedChangeListener{
    public TabHost tabHost;
    public RadioGroup radioGroup;
    public RadioButton radioButtonMy, radioButtonInternet;
    public ImageButton play;
    public ImageButton pre;
    public ImageButton next;
    public ImageButton song;
    public TextView song_name;
    public TextView singer_name;
    private MyApplication myApplication;
    private MusicPlayerService musicPlayerService;
    private String url;
    public LinearLayout main_mini;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myApplication= (MyApplication) getApplication();
        musicPlayerService=myApplication.musicPlayerService;
        setView();
        song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,PlayActivity.class);
                startActivity(intent);
            }
        });
        main_mini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,PlayActivity.class);
                startActivity(intent);
            }
        });
        tabHost=getTabHost();
        tabHost.addTab(tabHost.newTabSpec("M").setIndicator("M")
                .setContent(new Intent(MainActivity.this, LocalSong.class)));
        tabHost.addTab(tabHost.newTabSpec("I").setIndicator("I")
                .setContent(new Intent(MainActivity.this, InternetMain.class)));
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
    }

    @Override
    protected void onResume(){
        super.onResume();
        myApplication.setMainActivity(this);
        song_name.setText(myApplication.songname);
        singer_name.setText(myApplication.singername);
        musicPlayerService=myApplication.musicPlayerService;
        if (musicPlayerService!=null) {
            if (musicPlayerService.mediaPlayer != null) {
                if (musicPlayerService.mediaPlayer.isPlaying()) {
                    changeToPause();
                } else {
                    changeToPlay();
                }
            }
        }
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
        radioGroup= (RadioGroup) findViewById(R.id.radiogroup);
        radioButtonMy= (RadioButton) findViewById(R.id.radioButtonMy);
        radioButtonInternet= (RadioButton) findViewById(R.id.radioButtonInter);
        pre= (ImageButton) findViewById(R.id.main_pre);
        play= (ImageButton) findViewById(R.id.main_play_or_pause);
        next= (ImageButton) findViewById(R.id.main_next);
        song= (ImageButton) findViewById(R.id.main_mini_image);
        song_name= (TextView) findViewById(R.id.main_song_name);
        singer_name= (TextView) findViewById(R.id.main_singer_name);
        main_mini= (LinearLayout) findViewById(R.id.main_song);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioButtonMy:
                tabHost.setCurrentTabByTag("M");
                break;
            case R.id.radioButtonInter:
                tabHost.setCurrentTabByTag("I");
                break;
            default:
                break;
        }
    }
    //变换List
    private void modifyList(List<String> result, List<String> branch) {
        result.clear();
        for (int i = 0; i < branch.size(); i++) {
            result.add(branch.get(i));
        }
    }
    //本activity不是普通activity，不能这样用
    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("确认退出吗？");

        builder.setTitle("提示");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                MainActivity.this.finish();
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
    protected static final int MENU_QUIT = Menu.FIRST;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_QUIT, 0, "退出");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_QUIT:
                if (musicPlayerService!=null){
                    if (musicPlayerService.mediaPlayer!=null) {
                        musicPlayerService.mediaPlayer.pause();
                    }
                }
//                dialog();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
