package com.example.howard.mp3player.LocalSongInfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.howard.mp3player.DataBase.GetMusicData;
import com.example.howard.mp3player.Service.MusicPlayerService;
import com.example.howard.mp3player.MyApplication;
import com.example.howard.mp3player.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SongInfoSingerMain extends Activity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private Button topbutton;
    public List<Map<String,Object>> datalist;
    private List<Map<String,Object>> singersonglist=new ArrayList<Map<String, Object>>();
    private List<String> singernameliststr=new ArrayList<String>();
    public AdapterView.OnItemClickListener listener;
    private  AdapterView.OnItemClickListener songlistener;
    private MyAdapterSingerName myAdapterSingerName;
    private MyAdapter myAdapter;
    public int mypesition = -1, play_state;
    private MyApplication myApplication;
    public MusicPlayerService musicPlayerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_info_singer_main);
        listView = (ListView) findViewById(R.id.singer_info);
        topbutton= (Button) findViewById(R.id.topbutton);
        topbutton.setVisibility(View.GONE);
        topbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topbutton.setVisibility(View.GONE);
                listView.setAdapter(myAdapterSingerName);
                listener=resetLickListener();
                listView.setOnItemClickListener(listener);
            }
        });
        myApplication= (MyApplication) getApplication();
        musicPlayerService=new MusicPlayerService();
        datalist= GetMusicData.GetMusicData(this);
        searchBySinger(datalist);
        myAdapterSingerName=new MyAdapterSingerName(this);
        listView.setAdapter(myAdapterSingerName);
        listener=resetLickListener();
        listView.setOnItemClickListener(listener);
    }
//查找歌手列表
    private void searchBySinger (List<Map<String,Object>> list) {
        Map<String,Object> map;
        Object object;
        String singername;
        int i;
        for (i=0;i<list.size();i++){
            map=list.get(i);
            object=map.get("SINGER");
            singername=object.toString();

            if (i==0){
                singernameliststr.add(singername);

            }else if (!singernameliststr.contains(singername)){
                singernameliststr.add(singername);
            }
        }

    }
//点击播放指定歌手的歌曲
    private AdapterView.OnItemClickListener singerSongListener(){
        AdapterView.OnItemClickListener songListclickListener = new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                musicPlayerService=myApplication.musicPlayerService;
                Intent intent = new Intent(SongInfoSingerMain.this,
                        MusicPlayerService.class);
                intent.putExtra("Activity","Singer");
                intent.putExtra("pesition", arg2);

                if (musicPlayerService==null){
                    intent.putExtra("what", "play");
                }else {
                    mypesition=musicPlayerService.pesition;
                    if (mypesition != arg2) {
                        intent.putExtra("what", "play");
                    } else if (mypesition == arg2 && musicPlayerService.mediaPlayer.isPlaying()) {
                        intent.putExtra("what", "pause");
                    } else if (mypesition == arg2 &&!musicPlayerService.mediaPlayer.isPlaying()) {
                        intent.putExtra("what", "restart");
                    }
                }

                myApplication.setMydatalist(singersonglist);
                startService(intent);

            }
        };
        return songListclickListener;
    }


//监听歌手列表点击
    private AdapterView.OnItemClickListener resetLickListener(){
        AdapterView.OnItemClickListener songListclickListener = new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                String singername=singernameliststr.get(arg2);
                showSingersong(singername);
                mypesition=-1;
//                Intent intent = new Intent(SongInfoSingerMain.this,
//                        SongInfoSinger.class);
//                intent.putExtra("SingerName",singername);
//                startService(intent);
            }
        };
        return songListclickListener;
    }
//查找指定歌手的歌曲
    private List<Map<String,Object>> songForSingerName(String singername){
        List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
        Map<String,Object> map;
        int i;
        for (i=0;i<datalist.size();i++) {
            map=datalist.get(i);

            if (map.get("SINGER").toString().equals(singername)){
                list.add(map);
            }
        }
        return list;
    }

    //跳转到指定歌手的歌曲列表
    private void showSingersong(String singername){
        singersonglist=songForSingerName(singername);
        topbutton.setVisibility(View.VISIBLE);
        myApplication.setMydatalist(singersonglist);
        myAdapter=new MyAdapter(this);
        listView.setAdapter(myAdapter);
        songlistener=singerSongListener();
        listView.setOnItemClickListener(songlistener);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

//用于歌手列表的显示
    private class MyAdapterSingerName extends BaseAdapter {
        LayoutInflater inflater;

        public MyAdapterSingerName(Context c) {
            this.inflater = LayoutInflater.from(c);
        }

        public int getCount() {
            return singernameliststr.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.song_info_singer_main_item, null);
                viewHolder = new ViewHolder();
                viewHolder.singer_name = (TextView) convertView
                        .findViewById(R.id.singer_info_name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.singer_name.setText(singernameliststr.get(position));
//            viewHolder.singer_name.setText("Howard");

            return convertView;
        }

        class ViewHolder {
            TextView singer_name;

        }

    }
//用于指定歌手歌曲的显示
    private class MyAdapter extends BaseAdapter {
        LayoutInflater inflater;

        public MyAdapter(Context c) {
            this.inflater = LayoutInflater.from(c);
        }

        public int getCount() {
            return singersonglist.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        public View getView(final int position, View convertView,
                            ViewGroup parent) {
                ViewHolder viewHolder;

                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.song_info_singer_item, null);
                    viewHolder = new ViewHolder();
                    viewHolder.song_name = (TextView) convertView
                            .findViewById(R.id.song_name);
                    viewHolder.siner_name = (TextView) convertView
                            .findViewById(R.id.singer_name);

                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                viewHolder.song_name.setText(singersonglist.get(position).get("TITLE")
                        .toString());
                viewHolder.siner_name.setText(singersonglist.get(position).get("SINGER")
                        .toString());

                return convertView;

        }
        class ViewHolder {
            TextView song_name;
            TextView siner_name;
        }

    }



}
