package com.example.howard.mp3player.InterAPItools;

import android.util.Log;

import com.example.howard.mp3player.Bean.FastJsonTools;
import com.example.howard.mp3player.Bean.SearchBean;
import com.example.howard.mp3player.Bean.SingerSongBean;
import com.example.howard.mp3player.Bean.SongBySearchBean;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Howard on 2016/7/19.
 */
public class GetListBySingerSong {
    private String search;
    private Callback callback;
    private FastJsonTools fastJsonTools;
    private SearchBean searchBean;
    private String singerid;
    private SingerSongBean singerSongBean;
    public List<SongBySearchBean> singersonglist =new ArrayList<>();
    public SongBySearchBean songBySearchBean;


    public GetListBySingerSong(String searchstr, Callback call) {
        this.search = searchstr;
        this.callback = call;
    }

    public interface Callback {
        public void getsingersonglist();

        public void singernull();
    }

    public void setsingerdata() {
        fastJsonTools = new FastJsonTools();
        try {
            searchbysinger(search);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void searchbysinger(String str) throws UnsupportedEncodingException {
        String path = "http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&calback=&from=webapp_music&method=baidu.ting.search.catalogSug&query="+str ;
//        String path=new String(path1.getBytes("UTF-8"),"iso-8859-1");
        NUtils.get(path, new NUtils.AbsCallback() {
            @Override
            public void response(String url, byte[] bytes) {
                super.response(url, bytes);
                String json = new String(bytes);
                Log.e("json", json.toString());
                String Json = json.trim();
                SearchBean bean = fastJsonTools.createJsonBean(Json, SearchBean.class);
                Log.e("bean", bean.toString());
                getSingerid(bean);
            }
        });
    }

    private void getSingerid(SearchBean bean) {
        searchBean = new SearchBean();
        searchBean = bean;
//        Log.e("search",searchBean.toString());
        if (searchBean.getArtist() != null) {
            singerid = searchBean.getArtist().get(0).getArtistid();
            Log.e("singerid",singerid);
            getsongbysingerid(singerid);
        } else {
            callback.singernull();
        }
    }

    private void getsongbysingerid(String singeridstr) {
//        long id = Long.getLong(singeridstr);
        Call<SingerSongBean> call = Injection.provideSongAPI()
                .getSingerSong("json", "", "webapp_music", "baidu.ting.artist.getSongList", singeridstr, 20, 1, 2);
        call.enqueue(new retrofit2.Callback<SingerSongBean>() {
            @Override
            public void onResponse(Call<SingerSongBean> call, Response<SingerSongBean> response) {
                Log.e("singer", "true");
                SingerSongBean bean=response.body();
//                SingerSongBean singerSongBean=new SingerSongBean();
//                singerSongBean=bean;
                Log.e("singer", bean.toString());
                getsingersong(bean);
            }

            @Override
            public void onFailure(Call<SingerSongBean> call, Throwable t) {
                Log.e("singer", "true");
            }
        });

    }

    private void getsingersong (SingerSongBean singerSonglist){
        int j;
        SingerSongBean singerSongBean=new SingerSongBean();
        singerSongBean=singerSonglist;
        if (singerSongBean.getSonglist()!=null){
            for (j=0;j<singerSongBean.getSonglist().size();j++){
                songBySearchBean=new SongBySearchBean();
                songBySearchBean.setSongname(singerSongBean.getSonglist().get(j).getTitle());
                songBySearchBean.setSingername(singerSongBean.getSonglist().get(j).getAuthor());
                songBySearchBean.setSongid(singerSongBean.getSonglist().get(j).getSong_id());
                singersonglist.add(songBySearchBean);
            }
            callback.getsingersonglist();
        }else {
            callback.singernull();
        }

    }
}