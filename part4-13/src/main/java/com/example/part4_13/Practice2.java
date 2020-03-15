package com.example.part4_13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;

public class Practice2 extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    SeekBar seekBar;
    DonutView donutView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice2);

        seekBar = findViewById(R.id.seekbar);
        donutView = findViewById(R.id.donut);

        seekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        donutView.setValue(i);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
