package com.example.part2_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Lab3_3Activity extends AppCompatActivity implements View.OnClickListener{

    Button trueBtn, falseBtn;
    TextView targetTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3_3);

        trueBtn = (Button)findViewById(R.id.btn_visible_true);
        falseBtn = (Button)findViewById(R.id.btn_visible_false);
        targetTextView = (TextView)findViewById(R.id.text_visible_target);

        trueBtn.setOnClickListener(this);
        falseBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == trueBtn){
            targetTextView.setVisibility(View.VISIBLE);
        }else if(view == falseBtn){
            targetTextView.setVisibility(View.INVISIBLE);
        }
    }
}
