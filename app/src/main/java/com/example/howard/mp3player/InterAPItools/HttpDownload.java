package com.example.howard.mp3player.InterAPItools;

import android.os.Handler;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Howard on 2016/7/11.
 */
public class HttpDownload {

    public static final int TYPE_TXT=0;
    public static final int TYPE_IMG=1;

    public interface Callback{
        //是否取消下载
        public boolean isCancelled(String url);

        //下载完成之后，将数据回传
        public void response(String url, byte[] bytes);
    }

    public static class AbsCallback implements Callback{
        @Override
        public boolean isCancelled(String url) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public void response(String url, byte[] bytes) {
            // TODO Auto-generated method stub

        }
    }

    private static Handler mHandler =new Handler();
    private static ExecutorService service= Executors.newFixedThreadPool(5);

    public static void get(final int type,final String url,final Callback callback){
        service.execute(new Runnable() {

            @Override
            public void run() {
                try{
                    HttpURLConnection conn=(HttpURLConnection) new URL(url).openConnection();
                    InputStream is=conn.getInputStream();
                    byte[] buffer=new byte[10*1024];
                    int len=-1;

                    ByteArrayOutputStream baos=new ByteArrayOutputStream();

                    if(conn.getResponseCode()==200){
                        while((len=is.read(buffer))!=-1){
                            baos.write(buffer, 0, len);

                            //判断是否取消
                            if(callback.isCancelled(url)){

                                return;
                            }
                        }

                        final byte[] bytes=baos.toByteArray();

                        //判断当前请求类型是否为图片，若是则保存图片
//                        if(type==TYPE_IMG){
//                            ImageUtils.saveImg(url, bytes);
//                        }

                        mHandler.post(new Runnable(){
                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                callback.response(url, bytes);
                            }
                        });
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
