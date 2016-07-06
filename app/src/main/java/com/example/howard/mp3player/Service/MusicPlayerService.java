package com.example.howard.mp3player.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.example.howard.mp3player.MyApplication;
import com.example.howard.mp3player.LocalSongInfo.SongInfoLocal;
import com.example.howard.mp3player.LocalSongInfo.TabSongActivity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Howard on 2016/3/31.
 */
public class MusicPlayerService extends Service {



    public List<Map<String, Object>> list;
    public int pesition, duration, current, progress = -1;
    private String musicPath;
    public String url;
    public MediaPlayer mediaPlayer;
    private MediaPlayer lastMediaPlayer=null;
    private MusicPlayerBinder musicPlayerBinder = new MusicPlayerBinder();
    private SongInfoLocal songInfoLocal;
    private MyApplication myApplication;
    private TabSongActivity tabSongActivity;
    private final static int CHANGE_TO_PLAY = 111;
    private final static int CHANG_TO_PAUSE=112;
    private final static int PLAY_URL= 211;
    private String what;
//    public MusicHandler musicHandler;


    @Override
    public void onCreate(){
        myApplication= (MyApplication) getApplication();
        myApplication.setMusicPlayerService(this);
        tabSongActivity=myApplication.tabSongActivity;
//        musicHandler=new MusicHandler();
    }

    public void setList (List<Map<String, Object>> datalist){
        list=datalist;

    }
    public List<Map<String, Object>> getList (){
        return list;
    }

    public void mediaPause(){
        pause();
    }
    public class MusicPlayerBinder extends Binder implements IMusicPlayerService {

        public void callplay(String path) {

                play(path);
        }
        public void callrestart(){
            restart();
        }


        @Override
        public void callStop() {
            stop();
        }

        @Override
        public boolean callIsPlaying() {
            return isPlaying();
        }

        @Override
        public int callGetgetDuration() {
            return getgetDuration();
        }

        @Override
        public int callGetgetCurrentDuration() {
            return getCurrentDuration();
        }

        @Override
        public boolean callMediaIsNull() {
            return mediaIsNull();
        }

        @Override
        public void callChanageSeek(int position) {
            chanageSeek(position);
        }

        @Override
        public void callPause() {
            pause();
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return musicPlayerBinder;
    }
    public String getUrl (){
        return url;
    }
    public void callMedia(int pesition){
        url = list.get(pesition).get("URL").toString();
    }
    public void onStart(Intent intent, int startId) {

        super.onStart(intent, startId);
        myApplication.setMusicPlayerService(this);
        String Activity=intent.getStringExtra("Activity");
        if(Activity.equals("Local")){
           list=myApplication.mydatalist;
            what = intent.getStringExtra("what");
            pesition = intent.getIntExtra("pesition",-1);
            progress = intent.getIntExtra("seekto", 0);
            callMedia(pesition);
            callTab();
        }else if (Activity.equals("Singer")){
            list=myApplication.mydatalist;
            what = intent.getStringExtra("what");
            pesition = intent.getIntExtra("pesition",-1);
            progress = intent.getIntExtra("seekto", 0);
            callMedia(pesition);
            callTab();
        }else if (Activity.equals("Folder")){
            list=myApplication.mydatalist;
            what = intent.getStringExtra("what");
            pesition = intent.getIntExtra("pesition",-1);
            progress = intent.getIntExtra("seekto", 0);
            callMedia(pesition);
            callTab();
        }else if (Activity.equals("pre")){
            list=myApplication.mydatalist;
            pesition=intent.getIntExtra("pesition",-1);
            if(pesition!=-1){
                callMedia(pesition);
                play(url);
            }else {
                pesition=list.size()-1;
                callMedia(pesition);
               play(url);
            }
        }else if (Activity.equals("next")){

            list=myApplication.mydatalist;
            pesition=intent.getIntExtra("pesition",-1);
            if(pesition!=list.size()){
                callMedia(pesition);
                play(url);
            }else {
                pesition=0;
                callMedia(pesition);
                play(url);
            }
        }



    }

    private void callTab (){


        lastMediaPlayer=new MediaPlayer();
        if (what.equals("play")) {

                play(url);
                Message message=new Message();
                message.what=CHANG_TO_PAUSE;
                tabSongActivity.myHandler.sendMessage(message);

        } else if (what.equals("pause")) {
            pause();
            Message message=new Message();
            message.what=CHANGE_TO_PLAY;
            tabSongActivity.myHandler.sendMessage(message);
        } else if (what.equals("restart")) {

            restart();
            Message message=new Message();
            message.what=CHANG_TO_PAUSE;
            tabSongActivity.myHandler.sendMessage(message);
        }
    }

    /**
     * 初始化
     * @param path
     */
//       private void init(String path) {
//
//        if (mediaPlayer == null) {
//            mediaPlayer = new MediaPlayer();
//            reset(path);
//
//        }else{
//            reset(path);
//        }
//    }

    /**
     * 资源重置
     * @param path
     */
//    private void reset(String path) {
//
//        try {
//            mediaPlayer.reset();
//            mediaPlayer.setDataSource(path);
//            mediaPlayer.prepare();
//            mediaPlayer.setLooping(true);
//            mediaPlayer.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    /**
     * 播放
     * @param path
     */
//    private void play(String path) {
//
//      if (mediaPlayer != null && mediaPlayer.isPlaying()) {
//            mediaPlayer.pause();
//        } else if (mediaPlayer != null&&!mediaPlayer.isPlaying()) {
//            mediaPlayer.start();
//        } else {
//            init(path);
//        }
//
////        init(path);
//
//    }

    /**
     * 是不是在播放
     * @return
     */
    private boolean isPlaying(){
        if(mediaPlayer!=null) {
            return mediaPlayer.isPlaying();
        }
        return false;
    }

    /**
     * 获取总的进度
     * @return
     */
    public int getgetDuration(){
        return  mediaPlayer.getDuration();
    }

    /**
     * 获取当前进度
     * @return
     */
    public int getCurrentDuration(){
        return mediaPlayer.getCurrentPosition();
    }

    /**
     * 暂停
     */
//    private void pause(){
//        if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
//            mediaPlayer.pause();
//        }else if(mediaPlayer!=null&&!mediaPlayer.isPlaying()){
//            mediaPlayer.start();
//        }
//    }

    /**
     * 停止
     */
    private void stop(){
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;

        }
    }

    /**
     * 判断当前是不是NULL
     * @return
     */
    private boolean mediaIsNull(){
        return  mediaPlayer==null;
    }

    public void chanageSeek(int position){
        mediaPlayer.seekTo(position);
        if (mediaPlayer.isPlaying()){
        mediaPlayer.start();
        }else mediaPlayer.pause();
    }

    public void play(String path) {

        if (mediaPlayer==null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.reset();

            try {
                mediaPlayer.setDataSource(path);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }else {
            mediaPlayer.reset();

            try {
                mediaPlayer.setDataSource(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer arg0) {
                    mediaPlayer.start();

                }
            });

//            try {
//                mediaPlayer.prepare();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

//            mediaPlayer.setLooping(true);
//            mediaPlayer.start();
        }
    }


    public void restart(){
        mediaPlayer.start();
    }
    public void pause() {
        mediaPlayer.pause();
    }

    public class MusicHandler extends Handler {
        @Override
        public void handleMessage(Message msg){
            switch (msg.what)
            {
                case PLAY_URL:
                    play(msg.obj.toString());
                    break;
            }
        }
    }
}
