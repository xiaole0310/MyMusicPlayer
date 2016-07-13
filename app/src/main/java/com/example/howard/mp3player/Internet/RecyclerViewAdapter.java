package com.example.howard.mp3player.Internet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.howard.mp3player.Bean.RankingTitleBean;
import com.example.howard.mp3player.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Howard on 2016/7/13.
 */


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    @NonNull
    private List<RankingTitleBean> datas;

//    @NonNull
//    private final onClickListener onClickListener;

    @NonNull
    private final Context context;


    public RecyclerViewAdapter(@NonNull Context context,@NonNull List<RankingTitleBean> ListBean,
                               @NonNull OnItemClickListener onClickListener) {

        this.datas = ListBean;
        this.context = context;
        this.listener = onClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_ranking_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        RankingTitleBean rankingTitleBean = datas.get(position);


        if(rankingTitleBean==null) {

            Log.e("RecylerViewAdapter","rankingTitleBean is null");
        } else {
            holder.RankingImage.setBackground(rankingTitleBean.pic);
            holder.RankingName.setText(rankingTitleBean.name);
        }

        final int pos = holder.getAdapterPosition() ;

        holder.RankingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.OnItemClick(pos);
            }
        });


        setViewsSizeAndPos(holder);
    }


    @Override
    public int getItemCount() {

        return datas.size();
    }



    /** Item size 0-width 1-height*/
    private int[] itemSize;


    public int[] getItemSize() {

        return itemSize;
    }

    public void setItemSize(int[] itemSize) {

        this.itemSize = itemSize;
    }



    private void setViewsSizeAndPos(ViewHolder holder) {

    }





    public static class ViewHolder extends RecyclerView.ViewHolder {



        public ImageView RankingImage;

        public TextView RankingName;

        public ViewHolder(View view){

            super(view);

            RankingImage= (ImageView) view.findViewById(R.id.rankingChanelImage);
            RankingName= (TextView) view.findViewById(R.id.rankingChanelName);

        }
    }

    private OnItemClickListener listener;


    public interface OnItemClickListener {

        void OnItemClick(int pos);
    }
}
