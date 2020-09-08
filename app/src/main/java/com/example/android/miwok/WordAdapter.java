package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

    int colorLayout;

    public WordAdapter(@NonNull Context context, @NonNull List<Word> objects,int colorLayout) {
        super(context, 0, objects);
        this.colorLayout = colorLayout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_word, parent, false);
        }
        int color = ContextCompat.getColor(getContext(),colorLayout);

        LinearLayout linearlayout = convertView.findViewById(R.id.linearLayoutId);
        linearlayout.setBackgroundColor(color);
        Word currentWord = getItem(position);

        TextView SourceLang = (TextView) convertView.findViewById(R.id.sLanguage);
        SourceLang.setText(currentWord.getSourceInput());

        TextView targetLang = (TextView) convertView.findViewById(R.id.tLanguage);
        targetLang.setText(currentWord.getTargeInput());

        ImageView iconView = (ImageView) convertView.findViewById(R.id.image);
        if(currentWord.HasImage())
        {
            iconView.setImageResource(currentWord.getImageInput());
            iconView.setVisibility(View.VISIBLE);
        }else
        {
            iconView.setVisibility(View.GONE);
        }



        return convertView;


    }
}
