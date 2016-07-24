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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SongInfoFolderMain extends Activity implements AdapterView.OnItemClickListener{
    private MyApplication myApplication;
    private MusicPlayerService musicPlayerService;
    private Button topbutton;
    private ListView listView;
    private List<Map<String,Object>> datalist;
    private List<Map<String,Object>> folderPathAndName=new ArrayList<>();
    private List<String> folderPath=new ArrayList<>();
    private List<String> folderName=new ArrayList<>();
    private List<Map<String,Object>> foldersonglist=new ArrayList<Map<String, Object>>();
    private MyAdapter myAdapter;
    private MyFolderAdapter myFolderAdapter;
    private  AdapterView.OnItemClickListener songlistener;
    public int mypesition = -1, play_state;
    public AdapterView.OnItemClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_info_folder_main);
        listView= (ListView) findViewById(R.id.folder_info);
        topbutton= (Button) findViewById(R.id.topbutton);
        topbutton.setVisibility(View.GONE);
        topbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topbutton.setVisibility(View.GONE);
                listView.setAdapter(myFolderAdapter);
                listener=resetLickListener();
                listView.setOnItemClickListener(listener);
            }
        });
        myApplication= (MyApplication) getApplication();
        musicPlayerService=new MusicPlayerService();
        datalist= GetMusicData.GetMusicData(this);
        searchByPath(datalist);
        myFolderAdapter=new MyFolderAdapter(this);
        listView.setAdapter(myFolderAdapter);
        listener=resetLickListener();
        listView.setOnItemClickListener(listener);

    }
    //寻找文件夹列表
    private void searchByPath (List<Map<String,Object>> list){
        Map<String,Object> map;
        Map<String,Object> foldermap =new  HashMap<String, Object> ();
        Object objectURL,objectName;
        String path,name,allPath,foldername;
        int i;
        for (i=0;i<list.size();i++){
            map=list.get(i);
            objectURL=map.get("URL");
            objectName=map.get("NAME");
            name=objectName.toString();
            allPath=objectURL.toString();
            path=allPath.substring(19,allPath.length()-name.length()-1);
            foldername=path.substring(path.lastIndexOf('/')+1);
            if (i==0){
                foldermap.put("FOLDERNAME",foldername);
                foldermap.put("PATH",path);
                folderPathAndName.add(foldermap);
                folderPath.add(path);
                folderName.add(foldername);
            }else if (!folderPath.contains(path)){
                foldermap.put("FOLDERNAME",foldername);
                foldermap.put("PATH",path);
                folderPathAndName.add(foldermap);
                folderPath.add(path);
                folderName.add(foldername);
            }
        }
    }
    //查找指定文件夹内歌曲
    private List<Map<String,Object>> songForFolderPath(String path){
        List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
        Map<String,Object> map;
        String folderPath,songName,folderAllPath;
        Object objectURL,objectName;
        int i;
        for (i=0;i<datalist.size();i++) {
            map=datalist.get(i);
            objectURL=map.get("URL");
            objectName=map.get("NAME");
            songName=objectName.toString();
            folderAllPath=objectURL.toString();
            folderPath=folderAllPath.substring(19,folderAllPath.length()-songName.length()-1);

            if (folderPath.equals(path)){
                list.add(map);
            }
        }
        return list;
    }
    //跳转到指定文件夹歌曲列表
    private void showFolderSong(String folderpath){
        foldersonglist=songForFolderPath(folderpath);
        topbutton.setVisibility(View.VISIBLE);
        myApplication.setMydatalist(foldersonglist);
        myAdapter=new MyAdapter(this);
        listView.setAdapter(myAdapter);
        songlistener=folderSongListener();
        listView.setOnItemClickListener(songlistener);
    }
    //监听文件夹列表点击
    private AdapterView.OnItemClickListener resetLickListener(){
        AdapterView.OnItemClickListener songListclickListener = new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                String folderpath=folderPath.get(arg2);
                showFolderSong(folderpath);
                musicPlayerService=myApplication.musicPlayerService;
                if (musicPlayerService!=null){
                    musicPlayerService.pesition=-1;
                }
//                mypesition=-1;
            }
        };
        return songListclickListener;
    }
//点击播放指定文件夹歌曲
    private AdapterView.OnItemClickListener folderSongListener(){
        AdapterView.OnItemClickListener songListclickListener = new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                musicPlayerService=myApplication.musicPlayerService;
                Intent intent = new Intent(SongInfoFolderMain.this,
                        MusicPlayerService.class);
                intent.putExtra("Activity","Folder");
                intent.putExtra("pesition", arg2);
                if (musicPlayerService==null){
                    intent.putExtra("what", "play");
                }else {
                    mypesition=musicPlayerService.pesition;
                    if (mypesition != arg2) {
                        intent.putExtra("what", "play");
//                    play_state = 1;
//                    mypesition = arg2;
                    } else if (mypesition == arg2 && musicPlayerService.mediaPlayer.isPlaying()) {
                        intent.putExtra("what", "pause");
//                    play_state = 0;
                    } else if (mypesition == arg2 && !musicPlayerService.mediaPlayer.isPlaying()) {
                        intent.putExtra("what", "restart");
//                    play_state = 1;
                    }
                }
                myApplication.setMydatalist(foldersonglist);
                startService(intent);

            }
        };
        return songListclickListener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    //用于文件夹列表的显示
private class MyFolderAdapter extends BaseAdapter {
    LayoutInflater inflater;

    public MyFolderAdapter(Context c) {
        this.inflater = LayoutInflater.from(c);
    }

    public int getCount() {
        return folderPathAndName.size();
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
            convertView = inflater.inflate(R.layout.song_info_folder_main_item, null);
            viewHolder = new ViewHolder();
            viewHolder.folder_name = (TextView) convertView
                    .findViewById(R.id.folder_name);
            viewHolder.folder_path = (TextView) convertView
                    .findViewById(R.id.folder_path);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.folder_name.setText(folderName.get(position));
        viewHolder.folder_path.setText(folderPath.get(position));

        return convertView;

    }
    class ViewHolder {
        TextView folder_name;
        TextView folder_path;
    }

}

//用于指定文件夹歌曲的显示
    private class MyAdapter extends BaseAdapter {
        LayoutInflater inflater;

        public MyAdapter(Context c) {
            this.inflater = LayoutInflater.from(c);
        }

        public int getCount() {
            return foldersonglist.size();
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
//            if (position != 0) {
            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.song_info_folder_item, null);
                viewHolder = new ViewHolder();
                viewHolder.song_name = (TextView) convertView
                        .findViewById(R.id.song_name);
                viewHolder.siner_name = (TextView) convertView
                        .findViewById(R.id.singer_name);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.song_name.setText(foldersonglist.get(position).get("TITLE")
                    .toString());
            viewHolder.siner_name.setText(foldersonglist.get(position).get("SINGER")
                    .toString());

            return convertView;

        }
        class ViewHolder {
            TextView song_name;
            TextView siner_name;
        }

    }
}
