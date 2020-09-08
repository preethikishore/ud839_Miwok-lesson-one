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

public class FamilyActivity extends AppCompatActivity {

    private ListView familyListView;
    final ArrayList<Word> familyList = new ArrayList<Word>();
    private  MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener releasePlayer = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
        CreateUI();
    }
    void CreateUI()
    {
        WordAdapter numberAdapter;
        familyListView = findViewById(R.id.familyList);
        familyList.add(new Word("One","one",R.drawable.family_daughter,R.raw.family_daughter));
        familyList.add(new Word("Two","one",R.drawable.family_father,R.raw.family_father));
        familyList.add(new Word("Three","one",R.drawable.family_grandfather,R.raw.family_grandfather));
        familyList.add(new Word("Four","one",R.drawable.family_mother,R.raw.family_grandmother));
        familyList.add(new Word("Five","one",R.drawable.family_older_brother,R.raw.family_mother));
        familyList.add(new Word("Six","one",R.drawable.family_older_sister,R.raw.family_older_sister));
        familyList.add(new Word("Seven","one",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        numberAdapter = new WordAdapter(this,familyList,R.color.category_family);
        familyListView.setAdapter(numberAdapter);
        familyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                Word currentword = familyList.get(i);
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this,currentword.getaudioId() );
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(releasePlayer);
            }
        });
    }


    private void releaseMediaPlayer() {

        if (mMediaPlayer != null) {

            mMediaPlayer.release();

            mMediaPlayer = null;
        }
    }
}
