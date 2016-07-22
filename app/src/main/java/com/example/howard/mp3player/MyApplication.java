package com.example.howard.mp3player;

import android.app.Application;

import com.example.howard.mp3player.Internet.InternetSearchTab;
import com.example.howard.mp3player.LocalSongInfo.TabSongActivity;
import com.example.howard.mp3player.Service.MusicPlayerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Howard on 2016/7/2.
 */
public class MyApplication extends Application {
    public List<Map<String, Object>> mydatalist;
    public MusicPlayerService musicPlayerService=null;
    public TabSongActivity tabSongActivity;
    public List<String> songidList =new ArrayList<>();
    public InternetSearchTab internetSearchTab;
//    private String url;




    public void setMydatalist (List<Map<String, Object>> list){
        mydatalist=list;
    }
    public void setMusicPlayerService (MusicPlayerService service){
        musicPlayerService=service;
    }
    public void setTabSongActivity(TabSongActivity Activity){
        tabSongActivity=Activity;
    }
    public void setInternetSearchTab(InternetSearchTab InterActivity){
        internetSearchTab=InterActivity;
    }
    public void setSongidList (List<String> list){
        songidList=list;
    }
//    public void preClick() throws IOException {
//
//        musicPlayerService.pesition=musicPlayerService.pesition-1;
//
//        if(musicPlayerService.pesition!=-1){
//            musicPlayerService.callMedia(musicPlayerService.pesition);
//            url=musicPlayerService.url;
//            musicPlayerService.play(url);
//        }else {
//            musicPlayerService.pesition=musicPlayerService.songbysearchlist.size()-1;
//            musicPlayerService.callMedia(musicPlayerService.pesition);
//            url=musicPlayerService.url;
//            musicPlayerService.play(url);
//        }
//    }
//    public void playClick(){
//        if(musicPlayerService!=null){
//            if(musicPlayerService.mediaPlayer.isPlaying()) {
//                musicPlayerService.pause();
////                play.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.mini_play));
//            }else {
//
//                musicPlayerService.restart();
////                play.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.mini_pause));
//            }
//        }
//    }
//
//    public void nextClick() throws IOException {
//        musicPlayerService.pesition=musicPlayerService.pesition+1;
//        if(musicPlayerService.pesition!=musicPlayerService.songbysearchlist.size()){
//            musicPlayerService.callMedia(musicPlayerService.pesition);
//            url=musicPlayerService.url;
//            musicPlayerService.play(url);
//        }else {
//            musicPlayerService.pesition=0;
//            musicPlayerService.callMedia(musicPlayerService.pesition);
//            url=musicPlayerService.url;
//            musicPlayerService.play(url);
//        }
//    }

}


