package com.example.howard.mp3player.LocalSongInfo;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.howard.mp3player.DataBase.GetMusicData;
import com.example.howard.mp3player.Service.MusicPlayerService;
import com.example.howard.mp3player.MyApplication;
import com.example.howard.mp3player.R;

import java.util.List;
import java.util.Map;

public  class  SongInfoLocal extends Activity  {

    public ListView listView;
    public  List<Map<String, Object>> localdatalist;
    public int mypesition = -1, play_state;
    public View view;
    public ImageView imageView;
    public MyAdapter myAdapter;
    public AdapterView.OnItemClickListener listener;
    public MusicPlayerService musicPlayerService;
    private MyApplication myApplication;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_info_local);
        listView = (ListView) findViewById(R.id.local_song_name_list);
        myApplication= (MyApplication) getApplication();
        musicPlayerService=new MusicPlayerService();
        //获取本页面的ListView
        localdatalist = GetMusicData.GetMusicData(this);
        myApplication.setMydatalist(localdatalist);
        refreshListView();

        IntentFilter filter = new IntentFilter();
        filter.addAction("isplay");
        filter.addAction("info");
        registerReceiver(receiver, filter);

    }

    //刷新ListView
    private void refreshListView() {

        myAdapter = new MyAdapter(this);
        listView.setAdapter(myAdapter);
        listener= resetLickListener();
        listView.setOnItemClickListener(listener);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("isplay")) {
                play_state = intent.getIntExtra("state", 0);
            }
            if (intent.getAction().equals("info")) {
                mypesition = intent.getIntExtra("pesition", -1);
            }
        }
    };


    private AdapterView.OnItemClickListener resetLickListener(){
        AdapterView.OnItemClickListener songListclickListener = new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                musicPlayerService=myApplication.musicPlayerService;
                Intent intent = new Intent(SongInfoLocal.this,
                        MusicPlayerService.class);
                intent.putExtra("Activity","Local");
                intent.putExtra("pesition", arg2);
                if (musicPlayerService==null){
                    intent.putExtra("what", "play");
                }else {
                    mypesition=musicPlayerService.pesition;
                    if (mypesition != arg2) {
                        intent.putExtra("what", "play");
                    } else if (mypesition == arg2 &&musicPlayerService.mediaPlayer.isPlaying()) {
                        intent.putExtra("what", "pause");
                    } else if (mypesition == arg2 &&!musicPlayerService.mediaPlayer.isPlaying()) {
                        intent.putExtra("what", "restart");
                    }
                }

                myApplication.setMydatalist(localdatalist);
                startService(intent);
            }
        };
        return songListclickListener;
    }





    private class MyAdapter extends BaseAdapter {
        LayoutInflater inflater;

        public MyAdapter(Context c) {
            this.inflater = LayoutInflater.from(c);
        }

        public int getCount() {
            return localdatalist.size();
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
                convertView = inflater.inflate(R.layout.song_info_local_item, null);
                viewHolder = new ViewHolder();
//                viewHolder.isplay = (ImageView) convertView
//                        .findViewById(R.id.isplay);
                viewHolder.song_name = (TextView) convertView
                        .findViewById(R.id.song_name);
                viewHolder.siner_name = (TextView) convertView
                        .findViewById(R.id.singer_name);
                viewHolder.listview_click = (TextView) convertView
                        .findViewById(R.id.songlist_click);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.song_name.setText(localdatalist.get(position).get("TITLE")
                    .toString());
            viewHolder.siner_name.setText(localdatalist.get(position).get("SINGER")
                    .toString());

//            viewHolder.isplay
//                    .setBackgroundResource(R.drawable.play_list_played);

            viewHolder.listview_click.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    dialog(position);


                }
            });
            return convertView;
        }

        class ViewHolder {
            ImageView isplay;
            TextView song_name, listview_click;
            TextView siner_name;

        }
    }



    protected void dialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认在列表中删除吗？");

        builder.setTitle("不会删除本地歌曲");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                localdatalist.remove(position);
                if(mypesition==position){
                    musicPlayerService=myApplication.musicPlayerService;
                    musicPlayerService.mediaPause();
                    mypesition=-1;
                }else if(mypesition>position){
                    mypesition=mypesition-1;
                }

                refreshListView();
                myApplication.setMydatalist(localdatalist);
//                musicPlayerService.setList(localdatalist);
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
