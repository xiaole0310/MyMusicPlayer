package com.example.howard.mp3player.Internet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.howard.mp3player.Bean.SongBySearchBean;
import com.example.howard.mp3player.InterAPItools.GetListBySearchSong;
import com.example.howard.mp3player.R;
import com.example.howard.mp3player.Service.NetSongDownloadServer;

import java.util.ArrayList;
import java.util.List;

public class SongSearchActivity extends Activity {
    private List<SongBySearchBean> searchSongList=new ArrayList<>();
    private EditText searchInfo;
    private ImageButton searchBtn;
    private ListView songBySearch;
    private String searchstr=null;
    private SongSearchListViewAdapter myAdapter;
    public AdapterView.OnItemClickListener listener;
    private GetListBySearchSong getListBySearchSong;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_search);
        setView();
        myAdapter = new SongSearchListViewAdapter(this, searchSongList, new SongSearchListViewAdapter.Callback() {
            @Override
            public void downloadsongcallback(String songid) {
                dialog(songid);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String string=searchInfo.getText().toString();
//                searchstr= URLDecoder.decode(string ,"UTF-8");
                searchstr=searchInfo.getText().toString();
                if (!(searchstr ==null)){
                    recieveList(searchstr);

                }
            }
        });

    }
    //设置view
    private void setView(){
        searchInfo= (EditText) findViewById(R.id.searchText);
        searchBtn= (ImageButton) findViewById(R.id.searchbtn);
        songBySearch= (ListView) findViewById(R.id.songbysearch);
    }

    //刷新ListView
    private void refreshListView() {
        songBySearch.setAdapter(myAdapter);
        listener= resetLickListener();
        songBySearch.setOnItemClickListener(listener);
        myAdapter.notifyDataSetChanged();
    }
    private AdapterView.OnItemClickListener resetLickListener(){
        AdapterView.OnItemClickListener songListclickListener = new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {


            }
        };
        return songListclickListener;
    }

    //获得歌曲列表
    private void recieveList(String searchstr){
//        final List<SongBySearchBean> list=new ArrayList<>();
        getListBySearchSong =new GetListBySearchSong(searchstr, new GetListBySearchSong.Callback() {
            @Override
            public void getList() {
                searchSongList.clear();
                searchSongList.addAll(getListBySearchSong.songbysearchlist);
                Log.e("listmain", String.valueOf(searchSongList.size()));
                refreshListView();
            }

            @Override
            public void listnull() {
                Toast toast = Toast.makeText(SongSearchActivity.this, "未搜索到歌曲",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        getListBySearchSong.setDate();
    }

    protected void dialog(final String songid) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认要下载歌曲到本地吗？");

        builder.setTitle("请求下载歌曲");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(SongSearchActivity.this, NetSongDownloadServer.class);
//                Log.e("Activity",rankingsongbeanlist.get(position).getSong_id());
                intent.putExtra("songID",songid);
                startService(intent);
                dialog.dismiss();
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
}
