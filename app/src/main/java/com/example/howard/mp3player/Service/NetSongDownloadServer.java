package com.example.howard.mp3player.Service;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.howard.mp3player.Bean.SongDownloadBean;
import com.example.howard.mp3player.Bean.SongRankingBean;
import com.example.howard.mp3player.InterAPItools.Injection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Howard on 2016/7/14.
 */
public class NetSongDownloadServer extends Service{
    private String songID;
    private String path=null;
    private DownloadManager downloadManager;
    public static final String DOWNLOADPATH=Environment.getExternalStorageDirectory()+"/吧主音乐盒下载";
    private String songname;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e("onStart","true");
        songID=intent.getStringExtra("songID");
        long songid= Long.parseLong(songID);
        getJSON(songid);
    }

    private void getJSON(final long songid){
        Call<SongDownloadBean> call = Injection.provideSongAPI()
                .getdownloadsong("json", "","webapp_music" ,"baidu.ting.song.downWeb",songid,64,1393123213);
        call.enqueue(new Callback<SongDownloadBean>() {

            @Override
            public void onResponse(Call<SongDownloadBean> call, Response<SongDownloadBean> response) {
                SongDownloadBean bean = response.body();
                Log.e("file",bean.getBitrate().get(0).getShow_link());
                path=bean.getBitrate().get(0).getShow_link();
                if (path.equals("")||path==null){
                    path=bean.getBitrate().get(0).getFile_link();
                }
                songname=bean.getSonginfo().getTitle()+"."+bean.getBitrate().get(0).getFile_extension();
                Log.e("CALL",songID);
                Log.e("CALL",path);
                if (path!=null){
                    downloadsong();
                }
            }

            @Override
            public void onFailure(Call<SongDownloadBean> call, Throwable t) {

            }
        });
    }

    private void downloadsong(){
        Log.e("downstart",path);
        downloadManager= (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request downloadsong=new  DownloadManager.Request(Uri.parse(path));
        //移动和无线wifi都可以下载
//        downloadsong.setAllowedNetworkTypes((DownloadManager.Request.NETWORK_MOBILE|DownloadManager.Request.NETWORK_WIFI));
        //只有WIFI可以下载
        downloadsong.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        //发出通知，既后台下载
        downloadsong.setDescription("吧主音乐盒歌曲下载");
        downloadsong.setTitle("吧主音乐盒歌曲");
        //设置下载后文件存放的位置
        downloadsong.setDestinationInExternalPublicDir(DOWNLOADPATH,songname);
        //将下载请求放入队列
        downloadManager.enqueue(downloadsong);
        Log.e("downend","true");


    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
