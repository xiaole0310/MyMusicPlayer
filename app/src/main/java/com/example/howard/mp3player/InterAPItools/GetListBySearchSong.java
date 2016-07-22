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

    private static ExecutorService service= Executors.newFixedThreadPool(5);
    private void search(String searchstr) throws UnsupportedEncodingException {
//        Log.e("str",searchstr);
        String path="http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&calback=&from=webapp_music&method=baidu.ting.search.catalogSug&query="+searchstr;
//        String path=new String(string.getBytes("UTF-8"),"iso-8859-1");
        NUtils.get(path , new NUtils.AbsCallback() {
            @Override
            public void response(String url, byte[] bytes) {
                super.response(url, bytes);
                String json = new String(bytes);
//                Log.e("json",json.toString());
                String Json=json.trim();
                SearchBean bean= fastJsonTools.createJsonBean(Json,SearchBean.class);
//                Log.e("bean",bean.toString());
                setSearchBean(bean);
            }
        });
    }

    private void setSearchBean (SearchBean bean){
        searchBean=new SearchBean();
        searchBean= bean;
//        Log.e("search",searchBean.toString());
        if (searchBean.getSong()!=null){
            getsong();
//            Log.e("searchbean", String.valueOf(searchBean.getSong().size()));
            callback.getList();
        }else{

            callback.listnull();
        }
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
}

