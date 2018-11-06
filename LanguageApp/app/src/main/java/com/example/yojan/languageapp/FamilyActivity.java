package com.example.yojan.languageapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FamilyActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    private void releaseMediaPlayer(){
        if(mediaPlayer != null){
            mediaPlayer.release();

            mediaPlayer = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);


        final ArrayList<word> familyList = new ArrayList<>();
        familyList.add(new word("Mom","Mamu",R.drawable.family_mother,R.raw.family_mother));
        familyList.add(new word("Dad","Baba",R.drawable.family_father,R.raw.family_father));
        familyList.add(new word("Daughter","Chori",R.drawable.family_daughter,R.raw.family_daughter));
        familyList.add(new word("Son","Chora",R.drawable.family_son, R.raw.family_son));
        familyList.add(new word("Grand Father","Hajur Buwa",R.drawable.family_grandfather, R.raw.family_grandfather));
        familyList.add(new word("Grand Mother","Hajur Aama",R.drawable.family_grandmother, R.raw.family_grandmother));

        WordAdapter falimyAdapter = new WordAdapter(this, familyList);
        ListView family_listView =(ListView) findViewById(R.id.falilyList);
        family_listView.setAdapter(falimyAdapter);

        family_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                releaseMediaPlayer();
                word words = familyList.get(position);

                mediaPlayer = MediaPlayer.create(FamilyActivity.this,words.getMediaPlayerResoutceId());

                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(onCompletionListener);
            }
        });
    }
    @Override
    protected void onStop(){
        super.onStop();

        releaseMediaPlayer();

    }
}
