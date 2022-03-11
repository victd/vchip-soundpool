package com.example.vicsoundpool;

import androidx.appcompat.app.AppCompatActivity;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SoundPool soundPool;
    int game_over,
        level_complete,
        player_died;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
            soundPool = new SoundPool.Builder().setMaxStreams(3).setAudioAttributes(audioAttributes).build();
        }
        else {
            soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        }

        // This load function takes
        // three parameter context,
        // file_name and priority.  raw file image types are 
        game_over = soundPool.load(this, R.raw.game_over, 1);

        level_complete = soundPool.load(this, R.raw.level_complete,1);

        player_died = soundPool.load(this, R.raw.player_died, 1);

    }

    public void playSound(View v) {
        switch (v.getId()) {
        case R.id.button_sound1:

             // This play function
             // takes five parameters
             // leftVolume, rightVolume,
             // priority, loop and rate.
             soundPool.play(game_over, 1, 1, 0, 0, 1);
             soundPool.autoPause();
             break;

        case R.id.button_sound2:
             soundPool.play(player_died, 1,1, 0, 0, 1);
             break;

        case R.id.button_sound3:
             soundPool.play(level_complete, 1, 1, 0, 0, 1);
             break;

        }
    }
}
