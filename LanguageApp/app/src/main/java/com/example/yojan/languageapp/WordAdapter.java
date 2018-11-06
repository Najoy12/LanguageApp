package com.example.yojan.languageapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<word> {

    public WordAdapter(Activity context ,ArrayList<word> Word){
        super(context,0, Word);

    }
   // @androidx.annotation.NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
       View listViewItem = convertView;
       if (listViewItem == null){
           listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.viewlayout,parent,false);
       }

       word currentWord = getItem(position);
        TextView nepaliTextView = (TextView) listViewItem.findViewById(R.id.nepali);
        nepaliTextView.setText(currentWord.getnepali());

        TextView englishTextView = (TextView) listViewItem.findViewById(R.id.english);
        englishTextView.setText(currentWord.getdefult());

        ImageView imageView = (ImageView) listViewItem.findViewById(R.id.image);
        imageView.setImageResource(currentWord.getImageResourceId());
        return listViewItem;
    }
}
