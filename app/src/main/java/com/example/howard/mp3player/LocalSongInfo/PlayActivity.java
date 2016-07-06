package com.example.howard.mp3player.LocalSongInfo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.howard.mp3player.MyApplication;
import com.example.howard.mp3player.R;
import com.example.howard.mp3player.Service.IMusicPlayerService;
import com.example.howard.mp3player.Service.MusicPlayerService;

import java.io.IOException;

public class PlayActivity extends Activity implements View.OnClickListener{

    private final static int CHANGE_TIMESTART = 1;
    private MyHandler myHandler;
    //音乐文件的目录
//    private static final String PATH = Environment.getExternalStorageDirectory() + "/Music/";

    private Thread thread;

    private static final String TAG = "PlayActivity";
    private final static int PLAY_URL= 211;
    private String url;
    /*
    进度条
     */
    private SeekBar seekBar;
    /*
    音乐文件列表
     */
    private Button musicList;

    /*
    音乐控制按键
     */
    private ImageButton pre;
    private ImageButton play;
    private ImageButton next;
    private TextView timestart;
    private TextView timeend;
    //单首音乐的路径
//    private String musicPath="/storage/emulated/0/音乐/天空之城-纯音乐.mp3";

    private IMusicPlayerService mPlayerService;

    private MusicPlayerServiceConnection mConn;

    private boolean mBound =false;
    private MyApplication myApplication;
    private MusicPlayerService musicPlayerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.playview);
        myApplication= (MyApplication) getApplication();
        musicPlayerService=myApplication.musicPlayerService;

        //初始化
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        pre = (ImageButton) findViewById(R.id.pre);
        play = (ImageButton) findViewById(R.id.play);
        next = (ImageButton) findViewById(R.id.next);
        timestart= (TextView) findViewById(R.id.timestart);
        timeend= (TextView) findViewById(R.id.timeend);
        myHandler=new MyHandler();

        pre.setOnClickListener(this);
        play.setOnClickListener(this);
        next.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            //拖动进度条,改变播放进度
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (musicPlayerService!=null) {
//                mPlayerService.callChanageSeek(seekBar.getProgress());
                    musicPlayerService.chanageSeek(seekBar.getProgress());
                }
            }
        });
//        Intent intent = new Intent(PlayActivity.this,
//                MusicPlayerService.class);
//        intent.putExtra("Activity","null");
//        startService(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        if(mConn==null){
//            mConn= new MusicPlayerServiceConnection();
//        }
//        Intent intent = new Intent(this,MusicPlayerService.class);
//        mBound = bindService(intent, (MusicPlayerServiceConnection) mConn, BIND_AUTO_CREATE);

    }
    @Override
    protected  void onResume(){
        super.onResume();
        //获得服务实例
        musicPlayerService=myApplication.musicPlayerService;

        if (musicPlayerService==null){
            play.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.play));
        }else if (musicPlayerService.mediaPlayer.isPlaying()){
            changSeekBar();
            play.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pause));
        }else {
            changSeekBar();
            play.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.play));
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
//            case R.id.musicList:
//                showList();
//                break;
//            case R.id.stop:
//                stop();
//                break;
            case R.id.pre:
                try {
                    pre();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.play:
                play();
                break;
            case R.id.next:
                try {
                    next();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    /**
     * 上一首
     */
    private void pre() throws IOException {
        musicPlayerService = myApplication.musicPlayerService;
        if (musicPlayerService!=null) {
//            musicPlayerService.mediaPlayer.pause();
//            changSeekBar(false);
//            musicPlayerService.mediaPlayer.release();
//            SystemClock.sleep(500);
            int arg2=musicPlayerService.pesition-1;
            Intent intent = new Intent(PlayActivity.this,
                    MusicPlayerService.class);
            intent.putExtra("Activity","pre");
            intent.putExtra("pesition", arg2);
//            musicPlayerService.pesition = musicPlayerService.pesition - 1;
            startService(intent);
            changeToPause();
//            thread.run();
//            changSeekBar(true);
        }
//        myApplication.musicPlayerService.mediaPlayer.pause();
//        SystemClock.sleep(1000);
//        myApplication.preClick();
//        changeToPause();
    }


    /*
    播放
     */
    private void play() {
        musicPlayerService = myApplication.musicPlayerService;
        if (musicPlayerService != null){
            if (musicPlayerService.mediaPlayer.isPlaying()) {
            musicPlayerService.pause();
            changeToPlay();
        } else {
            musicPlayerService.restart();
                changeToPause();
        }
        }
//        myApplication.playClick();
//        if (myApplication.musicPlayerService.mediaPlayer.isPlaying()){
//            changeToPause();
//        }else {
//            changeToPlay();
//        }

    }

    /*
       下一首
        */
    private void next() throws IOException {
        musicPlayerService=myApplication.musicPlayerService;
        if (musicPlayerService!=null) {
//            musicPlayerService.mediaPlayer.pause();
//            SystemClock.sleep(300);
//            thread.interrupt();
//            changSeekBar(false);
//            musicPlayerService.mediaPlayer.release();
            int arg2=musicPlayerService.pesition+1;
            Intent intent = new Intent(PlayActivity.this,
                    MusicPlayerService.class);
            intent.putExtra("Activity","next");
            intent.putExtra("pesition", arg2);
//            musicPlayerService.pesition = musicPlayerService.pesition - 1;
            startService(intent);
            changeToPause();


//            thread.run();
//            changSeekBar(true);

        }
//        myApplication.musicPlayerService.mediaPlayer.pause();
//        SystemClock.sleep(1000);
//        myApplication.nextClick();
//        changeToPause();

    }


    private void changSeekBar(){
        int inttimeend = musicPlayerService.getgetDuration();
        String timeendstr = timeToString(inttimeend);
        timeend.setText(timeendstr);
        thread=new Thread() {

            boolean isFinished = musicPlayerService.mediaPlayer.isPlaying();
            //                    boolean isFinished=ifisFinished;
            @Override
            public void run() {
//                boolean isFinished = musicPlayerService.mediaPlayer.isPlaying();
                while (true){
                    while (isFinished) {
                        SystemClock.sleep(200);
                        int currentDuration = musicPlayerService.getCurrentDuration();
                        int duration = musicPlayerService.getgetDuration();
                        seekBar.setMax(duration);
                        seekBar.setProgress(currentDuration);
                        if (currentDuration >= duration) {
                            isFinished = false;
                        }
                        Message message = new Message();
                        message.what = CHANGE_TIMESTART;
                        myHandler.sendMessage(message);
                    }
                    isFinished=musicPlayerService.mediaPlayer.isPlaying();
                }

            }
        };
        thread.start();
//        new Thread() {
//
//            boolean isFinished = musicPlayerService.mediaPlayer.isPlaying();
////            boolean isFinished=ifisFinished;
//            @Override
//            public void run() {
//                if (isFinished) {
//                    while (isFinished) {
////                        SystemClock.sleep(200);
//                        int currentDuration = musicPlayerService.getCurrentDuration();
//                        int duration = musicPlayerService.getgetDuration();
//                        seekBar.setMax(duration);
//                        seekBar.setProgress(currentDuration);
//                        if (currentDuration >= duration) {
//                            isFinished = false;
//                        }
//                        Message message = new Message();
//                        message.what = CHANGE_TIMESTART;
//                        myHandler.sendMessage(message);
//                    }
//                }
//
//            }
//        }.start();
    }

    private void changeToPlay(){
        play.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.play));
    }
    private void changeToPause(){
        play.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pause));
    }
    /**
     * 停止播放
     */
    private void stop() {
        mPlayerService.callStop();
    }

    /**
     * 显示音乐列表
     */
//    private void showList() {
//
//        Intent intent = new Intent(this,MusicListActivity.class);
//        //intent.putStringArrayListExtra("filelist",fileList);
//        intent.putStringArrayListExtra("filenamelist",fileNameList);
//
//        startActivityForResult(intent,100);
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data==null){
            Toast.makeText(PlayActivity.this, "没有结果", Toast.LENGTH_SHORT).show();
            return;
        }
        //获取下标
        int position = data.getIntExtra("position", 0);
        //设置音乐路径

//        musicPath = fileList.get(position);
        // play();
//        Log.d(TAG,musicPath);
    }

    private class MusicPlayerServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mPlayerService = (IMusicPlayerService) service;


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            if(mConn!=null){
                mConn =null;
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mConn!=null){
            unbindService(mConn);
            mConn=null;
            mPlayerService=null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mConn!=null){
            unbindService(mConn);
            mConn=null;
            mPlayerService=null;
        }
    }
    private String timeToString (int time){
        String timestring;
        int minutes=(time/1000)/60;
        int seconds=(time/1000)%60;
        String min=String.valueOf(minutes);
        String sec;
        if (seconds<10){
            sec="0"+String.valueOf(seconds);
        }else {
            sec = String.valueOf(seconds);
        }
        timestring=min+":"+sec;
        return timestring;
    }
    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg){
        switch (msg.what)
        {
            case CHANGE_TIMESTART:
                int inttimestart=musicPlayerService.getCurrentDuration();
                String timestartstr=timeToString(inttimestart);
                timestart.setText(timestartstr);
                break;


        }
        }
    }
}
