package com.example.yojan.languageapp;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.LinkAddress;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NumberActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){

                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
            else if (focusChange == audioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();

        }
    };


    private void releaseMediaPlayer(){
        if(mediaPlayer != null) {
            mediaPlayer.release();


            mediaPlayer = null;
            audioManager.abandonAudioFocus(afChangeListener);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        audioManager =(AudioManager) getSystemService(Context.AUDIO_SERVICE);



        final ArrayList<word> numbers = new ArrayList<>();
        numbers.add(new word("one","yek", R.drawable.number_one, R.raw.number_one));
        numbers.add(new word("two","dui",R.drawable.number_two, R.raw.number_two));
        numbers.add(new word("three","tin",R.drawable.number_three,R.raw.number_three));
        numbers.add(new word("four","char",R.drawable.number_four,R.raw.number_four));
        numbers.add(new word("five","pach",R.drawable.number_five,R.raw.number_five));
        numbers.add(new word ("six","chha",R.drawable.number_six,R.raw.number_six));
        numbers.add(new word("seven", "sat",R.drawable.number_seven,R.raw.number_seven));
        numbers.add(new word("eight","aath",R.drawable.number_eight,R.raw.number_eight));
        numbers.add(new word("nine","nau",R.drawable.number_nine, R.raw.number_nine));
        numbers.add(new word("ten","dash",R.drawable.number_ten,R.raw.number_ten));




        WordAdapter itemsAdapter = new WordAdapter(this, numbers);
        ListView listView  =  findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                word words = numbers.get(position);
                releaseMediaPlayer();
                int result = audioManager.requestAudioFocus(afChangeListener,AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                    mediaPlayer = MediaPlayer.create(NumberActivity.this,words.getMediaPlayerResoutceId());

                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });







    }
    @Override
    protected void onStop(){
        super.onStop();

        releaseMediaPlayer();

    }
    }



