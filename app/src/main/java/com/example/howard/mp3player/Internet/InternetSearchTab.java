package com.example.howard.mp3player.Internet;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.howard.mp3player.LocalSongInfo.SongInfoFolderMain;
import com.example.howard.mp3player.LocalSongInfo.SongInfoLocal;
import com.example.howard.mp3player.LocalSongInfo.SongInfoSingerMain;
import com.example.howard.mp3player.R;

public class InternetSearchTab extends TabActivity implements RadioGroup.OnCheckedChangeListener{

    public TabHost tabHost;
    public RadioGroup radioGroup;
    public RadioButton radioButtonbysong, radioButtonbysinger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internet_search_tab);
        setView();
        tabHost=getTabHost();
        tabHost.addTab(tabHost.newTabSpec("O").setIndicator("O")
                .setContent(new Intent(InternetSearchTab.this,SongSearchActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("I").setIndicator("I")
                .setContent(new Intent(InternetSearchTab.this,SingerSongSearchActivity.class)));
        tabHost.setCurrentTab(0);
        radioGroup.setOnCheckedChangeListener(this);
    }

    public void setView(){
        radioGroup= (RadioGroup) findViewById(R.id.search_radiogroup);
        radioButtonbysong= (RadioButton) findViewById(R.id.radioButtonbysong);
        radioButtonbysinger= (RadioButton) findViewById(R.id.radioButtonbySinger);
    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioButtonbysong:
                tabHost.setCurrentTabByTag("O");
                break;
            case R.id.radioButtonbySinger:
                tabHost.setCurrentTabByTag("I");
                break;

            default:
                break;
        }
    }
}
