package com.example.howard.mp3player.Internet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.howard.mp3player.Bean.SongBySearchBean;
import com.example.howard.mp3player.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Howard on 2016/7/19.
 */
public class SingerSongListViewAdapter extends BaseAdapter {
    LayoutInflater inflater;
    List<SongBySearchBean> searchSongList=new ArrayList<>();
    Callback callback;

    private Context context;
    public SingerSongListViewAdapter(Context c, List<SongBySearchBean> List, Callback call) {
        this.inflater = LayoutInflater.from(c);
        context = c;
        this.searchSongList=List;
        this.callback=call;
    }

    public int getCount() {
        return searchSongList.size();
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
            convertView = inflater.inflate(R.layout.singer_song_search_item, null);
            viewHolder = new ViewHolder();
            viewHolder.song_name = (TextView) convertView
                    .findViewById(R.id.singer_search_song_name);
            viewHolder.siner_name = (TextView) convertView
                    .findViewById(R.id.singer_search_singer_name);
            viewHolder.download= (ImageButton) convertView
                    .findViewById(R.id.singer_search_downloadbty);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.song_name.setText(searchSongList.get(position).getSongname());
        viewHolder.siner_name.setText(searchSongList.get(position).getSingername());

        viewHolder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.downloadsingersongcallback(searchSongList.get(position).getSongid());
            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView song_name;
        TextView siner_name;
        ImageButton download;

    }


    public interface Callback{
        public void downloadsingersongcallback (String songid);
    }
}
