package com.example.howard.mp3player.Internet;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.howard.mp3player.Bean.SongRankingBean;
import com.example.howard.mp3player.InterAPItools.ImageUtils;
import com.example.howard.mp3player.InterAPItools.Injection;
import com.example.howard.mp3player.R;
import com.example.howard.mp3player.Service.NetSongDownloadServer;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongRankingListActivity extends Activity {

    private int rankingnum;
    private SongRankingBean songRankingBean;
    private ImageView rankingimage;
    private TextView rankingname;
    private ListView rankingsonglist;
    private String rankingimageUrl;
    private String rankingnamestr;
    private List<SongRankingBean.SongListBean> rankingsongbeanlist=new ArrayList<>();
//    private ImageUtils imageUtils;
    public MyAdapter myAdapter;
    public AdapterView.OnItemClickListener listener;
    private MyHandler myHandler;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_ranking_list);
        Intent intent=getIntent();
        rankingnum=intent.getIntExtra("RankingType",1);
        rankingimage = (ImageView) findViewById(R.id.rankingimage);
        rankingname = (TextView) findViewById(R.id.rankingname);
        rankingsonglist = (ListView) findViewById(R.id.Ranking_song_list);
        myAdapter = new MyAdapter(this);
        rankingsonglist.setAdapter(myAdapter);

//        imageUtils = new ImageUtils();
        myHandler = new MyHandler(this);
        songRankingBean=new SongRankingBean();
        net();
        //加载网络信息
        getRankingInfo(rankingnum);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void getRankingInfo(int num){
        Call<SongRankingBean> call = Injection.provideSongAPI()
                .getSongRanking("json", "", "webapp_music", "baidu.ting.billboard.billList", num, 50, 0);
        call.enqueue(new Callback<SongRankingBean>() {
            @Override
            public void onResponse(Call<SongRankingBean> call, Response<SongRankingBean> response) {

                SongRankingBean bean = response.body();
//                Log.e("bean", bean.getBillboard().getPic_s210());
                setRankingsongbeanlist(bean);
                Message message =new Message();
                message.what=SET_RANKINGBEAN;
                myHandler.sendMessage(message);

            }

            @Override
            public void onFailure(Call<SongRankingBean> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setRankingsongbeanlist(SongRankingBean bean) {
        songRankingBean = bean;
//        Log.e("songrankingBean", songRankingBean.getBillboard().getPic_s210());
    }

    private void net(){
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }

    //得到网络list资源
    private void getRankingSongInfo() {
        Log.e("getRank", songRankingBean.getBillboard().getPic_s210());
        rankingimageUrl = songRankingBean.getBillboard().getPic_s210();
        rankingnamestr = songRankingBean.getBillboard().getName();
//        try {
//            imageUtils.saveImg(rankingimageUrl);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String path = imageUtils.getimgpath(rankingimageUrl);
//        Log.d("Path", path);
        int i;
        for (i = 0; i < songRankingBean.getSong_list().size() && i < 50; i++) {
            rankingsongbeanlist.add(songRankingBean.getSong_list().get(i));
//            try {
//                imageUtils.saveImg(songRankingBean.getSong_list().get(i).getPic_small());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    //刷新ListView
    private void refreshListView() {
        myAdapter.notifyDataSetChanged();
        listener = resetLickListener();
        rankingsonglist.setOnItemClickListener(listener);
    }

    //点击响应
    private AdapterView.OnItemClickListener resetLickListener() {
        AdapterView.OnItemClickListener songListclickListener = new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {


            }
        };
        return songListclickListener;
    }

    //LIST适配
    private class MyAdapter extends BaseAdapter {
        LayoutInflater inflater;

        private Context context;
        public MyAdapter(Context c) {
            this.inflater = LayoutInflater.from(c);
            context = c;
        }

        public int getCount() {
            return rankingsongbeanlist.size();
        }

        @Override
        public Object getItem(int position) {

            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.song_ranking_list_item, null);
                viewHolder = new ViewHolder();
                viewHolder.song_Image = (ImageView) convertView
                        .findViewById(R.id.song_image);
                viewHolder.song_name = (TextView) convertView
                        .findViewById(R.id.ranking_song_name);
                viewHolder.siner_name = (TextView) convertView
                        .findViewById(R.id.ranking_singer_name);
                viewHolder.download= (ImageButton) convertView
                        .findViewById(R.id.downloadbty);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
//            Log.e("urlpath",rankingsongbeanlist.get(position).getPic_small());
            Picasso.with(context).load(rankingsongbeanlist.get(position).getPic_small()).into(viewHolder.song_Image);
//            Log.e("urlpath",rankingsongbeanlist.get(position).getPic_small());
//            viewHolder.song_Image.setImageBitmap(imageUtils.getImg(rankingsongbeanlist.get(position).getPic_small()));
            viewHolder.song_name.setText(rankingsongbeanlist.get(position).getTitle());
            viewHolder.siner_name.setText(rankingsongbeanlist.get(position).getArtist_name());

            viewHolder.download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog(position);
                }
            });

            return convertView;
        }

        class ViewHolder {
            ImageView song_Image;
            TextView song_name;
            TextView siner_name;
            ImageButton download;

        }
    }


    protected void dialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认要下载歌曲到本地吗？");

        builder.setTitle("请求下载歌曲");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(SongRankingListActivity.this, NetSongDownloadServer.class);
//                Log.e("Activity",rankingsongbeanlist.get(position).getSong_id());
                intent.putExtra("songID",rankingsongbeanlist.get(position).getSong_id());
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

    private static final int SET_RANKINGBEAN = 123;

    public class MyHandler extends Handler {

        private Context context;

        public MyHandler(Context context) {
            this.context = context;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case SET_RANKINGBEAN:
                    getRankingSongInfo();
                    Picasso.with(context).load(songRankingBean.getBillboard().getPic_s210()).into(rankingimage);
//                    rankingimage.setImageBitmap(imageUtils.getImg(songRankingBean.getBillboard().getPic_s210()));
                    rankingname.setText(songRankingBean.getBillboard().getName()+" Top 50");
                    refreshListView();
                    break;
            }

        }
    }
}