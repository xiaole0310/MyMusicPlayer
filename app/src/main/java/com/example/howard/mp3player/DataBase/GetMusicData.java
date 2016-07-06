package com.example.howard.mp3player.DataBase;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Howard on 2016/6/30.
 */
public class GetMusicData {
    public static List<Map<String, Object>> GetMusicData(Context c) {
        List<Map<String, Object>> musicdata = new ArrayList<Map<String, Object>>();
        ContentResolver cr = c.getContentResolver();
        Cursor cursor = cr.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        if (cursor != null && cursor.getCount() > 0) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                    .moveToNext()) {
                long id=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                String title = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));

                String singer = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));

                int time = cursor
                        .getInt(cursor
                                .getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));

                String name = cursor
                        .getString(cursor
                                .getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));

                String suffix = name
                        .substring(name.length() - 4, name.length());

                String url = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                String album = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                long albumid = cursor
                        .getLong(cursor
                                .getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));

                if (url.endsWith(".mp3") || url.endsWith(".MP3")||url.endsWith(".wma")||url.endsWith(".m4a")){
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("ID", id);
                    map.put("TITLE", title);
                    map.put("SINGER", singer);
                    map.put("TIME", time);
                    map.put("NAME", name);
                    map.put("SUFFIX", suffix);
                    map.put("URL", url);
                    map.put("ALBUM", album);
                    map.put("ALBUMID", albumid);
                    musicdata.add(map);
                }
            }
        }
        return musicdata;

    }

    public static List<Map<String, Object>> GetMusicDataByPath(Context c,String path) {
        List<Map<String, Object>> musicdata = new ArrayList<Map<String, Object>>();
        ContentResolver cr = c.getContentResolver();
        Cursor cursor = cr.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        if (cursor != null && cursor.getCount() > 0) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                    .moveToNext()) {
                long id=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                String title = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));

                String singer = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));

                int time = cursor
                        .getInt(cursor
                                .getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));

                String name = cursor
                        .getString(cursor
                                .getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));

                String suffix = name
                        .substring(name.length() - 4, name.length());

                String url = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                String album = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                long albumid = cursor
                        .getLong(cursor
                                .getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));


                if (  url.equals(path.contains(name)) &&(url.endsWith(".mp3") || url.endsWith(".MP3")||url.endsWith(".wma")||url.endsWith(".m4a"))){
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("ID", id);
                    map.put("TITLE", title);
                    map.put("SINGER", singer);
                    map.put("TIME", time);
                    map.put("NAME", name);
                    map.put("SUFFIX", suffix);
                    map.put("URL", url);
                    map.put("ALBUM", album);
                    map.put("ALBUMID", albumid);
                    musicdata.add(map);
                }
            }
        }
        return musicdata;

    }
}
