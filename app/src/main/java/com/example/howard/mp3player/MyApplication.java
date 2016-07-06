package com.example.howard.mp3player;

import android.app.Application;

import com.example.howard.mp3player.LocalSongInfo.TabSongActivity;
import com.example.howard.mp3player.Service.MusicPlayerService;

import java.util.List;
import java.util.Map;

/**
 * Created by Howard on 2016/7/2.
 */
public class MyApplication extends Application {
    public List<Map<String, Object>> mydatalist;
    public MusicPlayerService musicPlayerService=null;
    public TabSongActivity tabSongActivity;
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

//    public void preClick() throws IOException {
//
//        musicPlayerService.pesition=musicPlayerService.pesition-1;
//
//        if(musicPlayerService.pesition!=-1){
//            musicPlayerService.callMedia(musicPlayerService.pesition);
//            url=musicPlayerService.url;
//            musicPlayerService.play(url);
//        }else {
//            musicPlayerService.pesition=musicPlayerService.list.size()-1;
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
//        if(musicPlayerService.pesition!=musicPlayerService.list.size()){
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


