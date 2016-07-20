package com.example.howard.mp3player.InterAPItools;


import android.util.Log;

import com.example.howard.mp3player.Bean.FastJsonTools;
import com.example.howard.mp3player.Bean.PlayBean;
import com.example.howard.mp3player.Bean.SearchBean;
import com.example.howard.mp3player.Bean.SingerSongBean;
import com.example.howard.mp3player.Bean.SongBySearchBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.PUT;


/**
 * Created by Howard on 2016/7/16.
 */
public class GetListBySearchSong {

    public List<SongBySearchBean> songbysearchlist =new ArrayList<>();
    private String search;
    public SearchBean searchBean;
    public SongBySearchBean songBySearchBean;
    private FastJsonTools fastJsonTools;
    public Callback callback;

    public GetListBySearchSong(String searchstr, Callback call){
        this.search=searchstr;
        this.callback=call;
    }

    public interface Callback {
        public void getList();
        public void listnull();
    }

    public void setDate (){
        fastJsonTools=new FastJsonTools();
        try {
            search(search);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

//    public List<SongBySearchBean> getSongbysearchlist(){
//        Log.e("songbysearchlist", String.valueOf(songbysearchlist.size()));
//        return songbysearchlist;
//    }
    private static ExecutorService service= Executors.newFixedThreadPool(5);
    private void search(String searchstr) throws UnsupportedEncodingException {

        Log.e("str",searchstr);



//        Call<SearchBean> call = Injection.provideSongAPI()
//                .getSearch("json","","webapp_music","baidu.ting.search.catalogSug","喜欢你");
//
//        call.enqueue(new retrofit2.Callback<SearchBean>() {
//            @Override
//            public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
//                Log.e("call","true");
//            }
//
//            @Override
//            public void onFailure(Call<SearchBean> call, Throwable t) {
//                Log.e("call","false");
//            }
//        });

        String path="http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&calback=&from=webapp_music&method=baidu.ting.search.catalogSug&query="+searchstr;
//        String path=new String(string.getBytes("UTF-8"),"iso-8859-1");
        NUtils.get(path , new NUtils.AbsCallback() {
            @Override
            public void response(String url, byte[] bytes) {
                super.response(url, bytes);
                String json = new String(bytes);
                Log.e("json",json.toString());
                String Json=json.trim();
                SearchBean bean= fastJsonTools.createJsonBean(Json,SearchBean.class);
                Log.e("bean",bean.toString());
                setSearchBean(bean);
            }
        });
    }

    private void setSearchBean (SearchBean bean){
        searchBean=new SearchBean();
        searchBean= bean;
        Log.e("search",searchBean.toString());
        if (searchBean.getSong()!=null){
            getsong();
            Log.e("searchbean", String.valueOf(searchBean.getSong().size()));
            callback.getList();
        }else{

            callback.listnull();
        }
//        getsongbyid();
    }

    public void getsong(){
        int i;
        for (i=0;i<searchBean.getSong().size();i++){
            songBySearchBean=new SongBySearchBean();
            songBySearchBean.setSongname(searchBean.getSong().get(i).getSongname());
            songBySearchBean.setSingername(searchBean.getSong().get(i).getArtistname());
            songBySearchBean.setSongid(searchBean.getSong().get(i).getSongid());
            songbysearchlist.add(songBySearchBean);
        }
    }
//    public String utftogbk(String str) throws UnsupportedEncodingException {
//        String utf8 = new String(str.getBytes( "UTF-8"));
//        System.out.println(utf8);
//        String unicode = new String(utf8.getBytes(),"UTF-8");
//        System.out.println(unicode);
//        String gbk = new String(unicode.getBytes("GBK"));
//        return gbk;
//    }
}



//    public void getsongbyid(){
//        String songid;
//        int i;
//
//        for (i=0;i<searchBean.getSong().size();i++){
//            songid=searchBean.getSong().get(i).getSongid();
//            playsong(songid);
//            setSongBySearchBeanBySongid();
//            songbysearchlist.add(songBySearchBean);
//        }
//    }

//    private void setSongBySearchBeanBySongid(){
//        songBySearchBean=new SongBySearchBean();
//        songBySearchBean.setPic(playBean.getSonginfo().getPic_small());
//        songBySearchBean.setSongname(playBean.getSonginfo().getTitle());
//        songBySearchBean.setSingername(playBean.getSonginfo().getAuthor());
//        songBySearchBean.setSongid(playBean.getSonginfo().getSong_id());
//    }
//    private void playsong(String songid){
//
//        final String path="http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&calback=&from=webapp_music&method=baidu.ting.song.playAAC&songid="+songid;
//        NUtils.get( path, new NUtils.AbsCallback() {
//            @Override
//            public void response(String url, byte[] bytes) {
//                super.response(url, bytes);
//                String json = new String(bytes);
//                Log.e("json",json);
//                PlayBean bean= fastJsonTools.createJsonBean(json,PlayBean.class);
//                Log.e("bean",bean.toString());
//                playBean=bean;
//            }
//        });
//        Call<PlayBean> call = Injection.provideSongAPI()
//                .getPlay("json", "", "webapp_music", "baidu.ting.song.playAAC", songid);
//
//        call.enqueue(new Callback<PlayBean>() {
//            @Override
//            public void onResponse(Call<PlayBean> call, Response<PlayBean> response) {
//                Log.e("Playbean","true");
//                PlayBean bean = response.body();
//                playBean=new PlayBean();
//                playBean=bean;
//            }
//
//            @Override
//            public void onFailure(Call<PlayBean> call, Throwable t) {
//                Log.e("Playbean","false");
//                t.printStackTrace();
//            }
//        });
//    }
//    public void getsinger(){
//        int j;
//        for (j=0;j<searchBean.getArtist().size();j++){
//            singeridlist.add(searchBean.getArtist().get(j).getArtistid());
//        }
//    }
//    public void searchsongbysingerid(){
//        int n;
//        for (n=0;n<singeridlist.size();n++){
//            getsongbysingerid(singeridlist.get(n));
//        }
//
//    }
//    private void getsongbysingerid(String singerid){
//        long id=Long.getLong(singerid);
//        Call<SingerSongBean> call = Injection.provideSongAPI()
//                .getSingerSong("json", "", "webapp_music", "baidu.ting.artist.getSongList",id,30,1,2);
//        call.enqueue(new Callback<SingerSongBean>() {
//            @Override
//            public void onResponse(Call<SingerSongBean> call, Response<SingerSongBean> response) {
//
//                SingerSongBean bean = response.body();
//                singerSongBean=new SingerSongBean();
//                singerSongBean=bean;
//                setSongBySearchBeanBySingerid();
//            }
//
//            @Override
//            public void onFailure(Call<SingerSongBean> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });

//    }
//    private void setSongBySearchBeanBySingerid(){
//        int k;
//        for (k=0;k<singerSongBean.getSonglist().size();k++){
//            songBySearchBean=new SongBySearchBean();
//            songBySearchBean.setPic(singerSongBean.getSonglist().get(k).getPic_small());
//            songBySearchBean.setSongname(singerSongBean.getSonglist().get(k).getTitle());
//            songBySearchBean.setSingername(singerSongBean.getSonglist().get(k).getAuthor());
//            songBySearchBean.setSongid(singerSongBean.getSonglist().get(k).getSong_id());
//            songbysearchlist.add(songBySearchBean);
//        }
//    }
//
//    private void net(){
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                .detectDiskReads()
//                .detectDiskWrites()
//                .detectNetwork()
//                .penaltyLog()
//                .build());
//        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                .detectLeakedSqlLiteObjects()
//                .detectLeakedClosableObjects()
//                .penaltyLog()
//                .penaltyDeath()
//                .build());
//    }
//
//    private static final int SET_SEARCHBEAN = 345;
//
//    public class GetHandler extends Handler {
//
//
//
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//
//                case SET_SEARCHBEAN:
//
//                    break;
//            }
//
//        }
//    }

