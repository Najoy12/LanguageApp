package com.example.yojan.languageapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class colorsActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_colors);


        final ArrayList<word> colors = new ArrayList<>();
        colors.add(new word("Red","Rato",R.drawable.color_red,R.raw.color_red));
        colors.add(new word("Green","Hareio",R.drawable.color_green,R.raw.color_green));
        colors.add(new word ("White","Seto", R.drawable.color_white, R.raw.color_white));
        colors.add(new word("Yellow","Pahelio", R.drawable.color_mustard_yellow, R.raw.color_dusty_yellow));
        colors.add(new word("Pink","gulabi",R.raw.color_red));


        WordAdapter colorsListAdapter = new WordAdapter(this, colors);
        ListView colorsList = findViewById(R.id.colorsList);
        colorsList.setAdapter(colorsListAdapter);


        colorsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                word words = colors.get(position);
                mediaPlayer = MediaPlayer.create(colorsActivity.this,words.getMediaPlayerResoutceId());

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
