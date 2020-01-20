package com.example.part2_3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class Lab3_4Activity extends AppCompatActivity {

    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3_4_activity);

        TextView textView = (TextView)findViewById(R.id.fontView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "nanum_gothic_regular.ttf");
        textView.setTypeface(typeface);

        checkBox = (CheckBox)findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    checkBox.setText("is Checked");
                }else {
                    checkBox.setText("is unChecked");
                }
            }
        });
    }
}
