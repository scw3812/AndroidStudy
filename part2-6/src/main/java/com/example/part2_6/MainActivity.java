package com.example.part2_6;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    TextView bellTextView;
    TextView labelTextVeiw;
    CheckBox repeatCheckView;
    CheckBox vibrateCheckView;
    Switch switchView;

    float initX;
    long initTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bellTextView = findViewById(R.id.bell_name);
        labelTextVeiw=findViewById(R.id.label);
        repeatCheckView=findViewById(R.id.repeatCheck);
        vibrateCheckView=findViewById(R.id.vibrate);
        switchView=findViewById(R.id.onOff);

        bellTextView.setOnClickListener(this);
        labelTextVeiw.setOnClickListener(this);
        repeatCheckView.setOnCheckedChangeListener(this);
        vibrateCheckView.setOnCheckedChangeListener(this);
        switchView.setOnCheckedChangeListener(this);
    }



    private void showToast(String message){
        Toast toast=Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }


    @Override
    public void onClick(View view) {
        if(view == bellTextView){
            showToast("bell text click event...");
        }else if(view == labelTextVeiw){
            showToast("label text click event");
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(compoundButton == repeatCheckView){
            showToast("repeat checkbox is " + b);
        }else if(compoundButton == vibrateCheckView){
            showToast("vibrate checkbox is " + b);
        }else if(compoundButton == switchView){
            showToast("switch is " + b);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            initX = motionEvent.getRawX();
        }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
            float diffX = initX - motionEvent.getRawX();
            if(diffX > 30){
                showToast("왼쪽으로 화면을 밀었습니다.");
            }else if(diffX < -30){
                showToast("오른쪽으로 화면을 밀었습니다.");
            }
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis() - initTime > 3000){
                showToast("종료하려면 한번 더 누르세요.");
                initTime = System.currentTimeMillis();
            }else{
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, keyEvent);
    }
}
