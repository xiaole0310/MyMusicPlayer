package com.example.howard.mp3player.Service;

/**
 * Created by Howard on 2016/3/31.
 */
public interface IMusicPlayerService {
    public void callplay(String path);

    public  void callStop();

    public boolean callIsPlaying();

    public int callGetgetDuration();

    public int callGetgetCurrentDuration();

    public boolean callMediaIsNull();

    public void callChanageSeek(int position);

    public void callPause();
}
