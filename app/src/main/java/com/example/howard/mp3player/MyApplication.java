package com.example.howard.mp3player;

import android.app.Application;

import com.example.howard.mp3player.Internet.InternetSearchTab;
import com.example.howard.mp3player.Internet.SongRankingListActivity;
import com.example.howard.mp3player.Internet.SongRankingMainActivity;
import com.example.howard.mp3player.LocalSongInfo.PlayActivity;
import com.example.howard.mp3player.LocalSongInfo.TabSongActivity;
import com.example.howard.mp3player.Service.MusicPlayerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 微信公众号：(ID：study_tech)
 * Created by 杨守乐 on 2016/9/14.
 * Email：ysle0313@gmail.com
 * QQ:1733563441
 * 技术Q群：279126311
 */
public class MyApplication extends Application {
    public List<Map<String, Object>> mydatalist;
    public List<String> songidList =new ArrayList<>();
    public MusicPlayerService musicPlayerService=null;
    public MainActivity mainActivity;
    public TabSongActivity tabSongActivity;
    public SongRankingMainActivity songRankingMainActivity;
    public SongRankingListActivity songRankingListActivity;
    public InternetSearchTab internetSearchTab;
    public String activityname="mainactivity";
    public PlayActivity playActivity;
    public String songname="未播放";
    public String singername="未播放";





    public void setMydatalist (List<Map<String, Object>> list){
        mydatalist=list;
    }
    public void setMusicPlayerService (MusicPlayerService service){
        musicPlayerService=service;
    }
    public void setMainActivity(MainActivity activity){
        mainActivity=activity;
        activityname="mainactivity";
    }
    public void setTabSongActivity(TabSongActivity Activity){
        tabSongActivity=Activity;
        activityname="tabsongactivity";
    }
    public void setSongRankingMainActivity(SongRankingMainActivity activity){
        songRankingMainActivity=activity;
        activityname="songrankingmainactivity";
    }
    public void setSongRankingListActivity(SongRankingListActivity activity){
        songRankingListActivity=activity;
        activityname="songrankinglistactivity";
    }
    public void setInternetSearchTab(InternetSearchTab InterActivity){
        internetSearchTab=InterActivity;
        activityname="internetsearchtab";
    }
    public void setSongidList (List<String> list){
        songidList=list;
    }
    public void setPlayActivity(PlayActivity activity){
        playActivity=activity;
    }

    public void setsongname(String name){
        songname=name;
    }
    public void setsingername(String name){
        singername=name;
    }
    public void setmininame(){
        if (activityname.equals("mainactivity")){
            mainActivity.song_name.setText(songname);
            mainActivity.singer_name.setText(singername);
        }else if (activityname.equals("tabsongactivity")){
            tabSongActivity.song_name.setText(songname);
            tabSongActivity.singer_name.setText(singername);
        }else if (activityname.equals("songrankingmainactivity")){
            songRankingMainActivity.song_name.setText(songname);
            songRankingMainActivity.singer_name.setText(singername);
        }else if (activityname.equals("songrankinglistactivity")){
            songRankingListActivity.song_name.setText(songname);
            songRankingListActivity.singer_name.setText(singername);
        }else if (activityname.equals("internetsearchtab")){
           internetSearchTab.song_name.setText(songname);
            internetSearchTab.singer_name.setText(singername);
        }
    }
}


