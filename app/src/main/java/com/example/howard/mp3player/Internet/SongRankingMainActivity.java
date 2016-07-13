package com.example.howard.mp3player.Internet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.howard.mp3player.Bean.RankingTitleBean;
import com.example.howard.mp3player.Bean.SongRankingBean;
import com.example.howard.mp3player.InterAPItools.ImageUtils;
import com.example.howard.mp3player.InterAPItools.Injection;
import com.example.howard.mp3player.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongRankingMainActivity extends Activity implements RecyclerViewAdapter.OnItemClickListener{
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RankingTitleBean rankingTitleBeen ;
    private AdapterView.OnItemClickListener listener;
    private List<RankingTitleBean> rankingtitleList=new ArrayList<>();



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_ranking);
        recyclerView = (RecyclerView) findViewById(R.id.rankingView);
        rankingTitleBeen=new RankingTitleBean();
//        listener=setListener();
//        net();
        setRecyclerViewAdapter();
        getRankingInfo();
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private void setRecyclerViewAdapter(){
        recyclerViewAdapter = new RecyclerViewAdapter(this, rankingtitleList, this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void getRankingInfo(){
        rankingTitleBeen = new RankingTitleBean();
        rankingTitleBeen.setPic(this.getResources().getDrawable(R.drawable.ranking1));
        rankingTitleBeen.setName("新歌榜");
        rankingtitleList.add(rankingTitleBeen);

        rankingTitleBeen = new RankingTitleBean();
        rankingTitleBeen.setPic(this.getResources().getDrawable(R.drawable.ranking2));
        rankingTitleBeen.setName("热歌榜");
        rankingtitleList.add(rankingTitleBeen);

        rankingTitleBeen = new RankingTitleBean();
        rankingTitleBeen.setPic(this.getResources().getDrawable(R.drawable.ranking21));
        rankingTitleBeen.setName("欧美金曲榜");
        rankingtitleList.add(rankingTitleBeen);

        rankingTitleBeen = new RankingTitleBean();
        rankingTitleBeen.setPic(this.getResources().getDrawable(R.drawable.ranking22));
        rankingTitleBeen.setName("经典老歌榜");
        rankingtitleList.add(rankingTitleBeen);

        rankingTitleBeen = new RankingTitleBean();
        rankingTitleBeen.setPic(this.getResources().getDrawable(R.drawable.ranking23));
        rankingTitleBeen.setName("情歌对唱榜");
        rankingtitleList.add(rankingTitleBeen);

        rankingTitleBeen = new RankingTitleBean();
        rankingTitleBeen.setPic(this.getResources().getDrawable(R.drawable.ranking25));
        rankingTitleBeen.setName("网络歌曲榜");
        rankingtitleList.add(rankingTitleBeen);
    }


    @Override
    public void OnItemClick(int pos) {
        if (pos==0){
            Intent intent=new Intent(SongRankingMainActivity.this,SongRankingListActivity.class);
            intent.putExtra("RankingType",1);
            startActivity(intent);
        }else if (pos==1){
            Intent intent=new Intent(SongRankingMainActivity.this,SongRankingListActivity.class);
            intent.putExtra("RankingType",2);
            startActivity(intent);
        }else if (pos==2){
            Intent intent=new Intent(SongRankingMainActivity.this,SongRankingListActivity.class);
            intent.putExtra("RankingType",21);
            startActivity(intent);
        }else if (pos==3){
            Intent intent=new Intent(SongRankingMainActivity.this,SongRankingListActivity.class);
            intent.putExtra("RankingType",22);
            startActivity(intent);
        }else if (pos==4){
            Intent intent=new Intent(SongRankingMainActivity.this,SongRankingListActivity.class);
            intent.putExtra("RankingType",23);
            startActivity(intent);
        }else if (pos==5){
            Intent intent=new Intent(SongRankingMainActivity.this,SongRankingListActivity.class);
            intent.putExtra("RankingType",25);
            startActivity(intent);
        }
    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认在列表中删除吗？");

        builder.setTitle("不会删除本地歌曲");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
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