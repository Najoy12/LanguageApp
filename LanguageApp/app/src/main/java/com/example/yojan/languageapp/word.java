package com.example.yojan.languageapp;

import android.media.MediaPlayer;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class word {

    private String mdefult_translation;
    private String mnepali_translation;
    private int nNepali_resourceId;
    private int nNepali_mediaPlayer;

    public word(String defult_translation,String nepali_translation,int nepali_audio){
        mdefult_translation = defult_translation;
        mnepali_translation = nepali_translation;
        nNepali_mediaPlayer = nepali_audio;
    }

    public word (String defult_translation, String nepali_translation, int Resource_Id, int  nepali_audio){
        mdefult_translation = defult_translation;
        mnepali_translation = nepali_translation;
        nNepali_resourceId = Resource_Id;
        nNepali_mediaPlayer = nepali_audio;

    }
    public String getdefult(){return mdefult_translation;}
    public String getnepali (){return mnepali_translation;}
    public int getImageResourceId(){return nNepali_resourceId;}

    public int  getMediaPlayerResoutceId() {
        return nNepali_mediaPlayer;
    }
}
