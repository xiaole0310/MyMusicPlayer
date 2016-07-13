package com.example.howard.mp3player.InterAPItools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Howard on 2016/7/12.
 */
public class ImageUtils {
    public static final String CACHEDIR= Environment.getExternalStorageDirectory()+"/downloadFiles/images";

    public static boolean isMounted(){
        return Environment.MEDIA_MOUNTED.equals(
                Environment.getExternalStorageState());
    }

    public static void saveImg(String url) throws IOException {
        if(!isMounted())  return;

        File dir=new File(CACHEDIR);
        if(!dir.exists()) dir.mkdirs();
        File file=new File(dir,getName(url));
        if (!file.exists()){
            URL picurl=null;
            Bitmap bitmap=null;
            byte[] bytes;
            picurl=new URL(url);
            HttpURLConnection conn= (HttpURLConnection) picurl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is =conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            bytes= bitmap2Bytes(bitmap);
            FileOutputStream fos=new FileOutputStream(file);
            fos.write(bytes);
            fos.close();
        }
    }

    public static byte[] bitmap2Bytes (Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();

    }

    public static Bitmap getImg(String url){
        if(!isMounted())  return null;


        File imgFile=new File(CACHEDIR,getName(url));
        if(imgFile.exists()){
            return BitmapFactory.decodeFile(imgFile.getAbsolutePath());

        }

        return null;
    }

    public String getimgpath(String url){
        File imgFile=new File(CACHEDIR,getName(url));
        if(imgFile.exists()){
            return imgFile.getAbsolutePath();
        }
        return null;
    }
    public static String getName(String url){
        return url.substring(url.lastIndexOf("/")+1);
    }

    public static String getName(String url,int end){
        return url.substring(url.lastIndexOf("/")+1,end);
    }
}