/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    private ListView colorListView;
    private ArrayList<Word> colorList = new ArrayList<Word>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        CreateUI();
    }
    void CreateUI()
    {
        WordAdapter numberAdapter;
        colorListView = findViewById(R.id.colorList);
        colorList.add(new Word("Black","one",R.drawable.color_black,R.raw.color_black));
        colorList.add(new Word("Brown","one",R.drawable.color_brown,R.raw.color_brown));
        colorList.add(new Word("Yellow","one",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        colorList.add(new Word("Grey","one",R.drawable.color_gray,R.raw.color_gray));
        colorList.add(new Word("Red","one",R.drawable.color_red,R.raw.color_red));
        colorList.add(new Word("White","one",R.drawable.color_white,R.raw.color_white));
        colorList.add(new Word("Musterd Yellow","one",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        numberAdapter = new WordAdapter(this,colorList,R.color.category_colors);
        colorListView.setAdapter(numberAdapter);
        colorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word currentPosition = colorList.get(position);
                MediaPlayer player = MediaPlayer.create(ColorsActivity.this,currentPosition.getaudioId());
                player.start();

            }
        });

    }
}
