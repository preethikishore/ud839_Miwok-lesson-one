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


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private ListView numberListView;
    final ArrayList<Word> numberList = new ArrayList<Word>();
    private  MediaPlayer mMediaPlayer;
    private AudioManager mAudioManger;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };


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
        setContentView(R.layout.activity_numbers);

        CreateUI();

    }

    void CreateUI()
    {
        mAudioManger = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        WordAdapter numberAdapter;
        numberListView = findViewById(R.id.numberList);
        numberList.add(new Word("One","one",R.drawable.number_one,R.raw.number_one));
        numberList.add(new Word("Two","one",R.drawable.number_two,R.raw.number_two));
        numberList.add(new Word("Three","one",R.drawable.number_three,R.raw.number_three));
        numberList.add(new Word("Four","one",R.drawable.number_four,R.raw.number_four));
        numberList.add(new Word("Five","one",R.drawable.number_five,R.raw.number_five));
        numberList.add(new Word("Six","one",R.drawable.number_six,R.raw.number_six));
        numberList.add(new Word("Seven","one",R.drawable.number_seven,R.raw.number_seven));
        numberAdapter = new WordAdapter(this,numberList,R.color.category_numbers);
        numberListView.setAdapter(numberAdapter);
        numberListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Word currentword = numberList.get(i);
                releaseMediaPlayer();
                int result = mAudioManger.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {

                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this,currentword.getaudioId() );
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(releasePlayer);
                }

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {

        if (mMediaPlayer != null) {

            mMediaPlayer.release();

            mMediaPlayer = null;
            mAudioManger.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
  }
}
