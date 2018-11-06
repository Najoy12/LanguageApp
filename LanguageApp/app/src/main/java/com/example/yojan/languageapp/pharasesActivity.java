package com.example.yojan.languageapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class pharasesActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_pharases);


        final ArrayList<word> phrasesList = new ArrayList<>();
        phrasesList.add(new word("Hello","Namaskar",R.raw.phrase_are_you_coming));
        phrasesList.add(new word("Good Morning","Subha Pravat",R.raw.family_grandmother));
        //phrasesList.add(new word("Good Afternoon","Subha Madhanna"));
        //phrasesList.add(new word("Good Night","Subha Rarti"));


        WordAdapter pharasesAdapter = new WordAdapter(this, phrasesList);
        ListView pharasesList = findViewById(R.id.phrasesList);
        pharasesList.setAdapter(pharasesAdapter);

        pharasesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                releaseMediaPlayer();
                word words = phrasesList.get(position);

                mediaPlayer = MediaPlayer.create(pharasesActivity.this,words.getMediaPlayerResoutceId());

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
