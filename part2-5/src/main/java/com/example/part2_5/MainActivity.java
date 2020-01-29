package com.example.part2_5;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button vibrationBtn;
    Button systemBeepBtn;
    Button customBeepBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibrationBtn = (Button)findViewById(R.id.btn_vibrate);
        systemBeepBtn = (Button)findViewById(R.id.btn_system_beep);
        customBeepBtn = (Button)findViewById(R.id.btn_custom_sound);

        vibrationBtn.setOnClickListener(this);
        systemBeepBtn.setOnClickListener(this);
        customBeepBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == vibrationBtn){
            Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
//            vibrator.vibrate(1000);
            vibrator.vibrate(new long[]{1000, 500, 1000, 500}, -1);
        }else if(view == systemBeepBtn){
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
            ringtone.play();
        }else if(view == customBeepBtn){
            MediaPlayer player = MediaPlayer.create(this, R.raw.fallbackring);
            player.start();
        }
    }
}
