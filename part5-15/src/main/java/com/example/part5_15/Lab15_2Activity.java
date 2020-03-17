package com.example.part5_15;

import android.content.res.Configuration;
import android.os.Build;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class Lab15_2Activity extends AppCompatActivity implements View.OnClickListener{
    Button keyboardBtn;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab15_2);
        keyboardBtn=findViewById(R.id.lab2_toggleBtn);
        editText=findViewById(R.id.lab2_edit);

        keyboardBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        InputMethodManager manager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        manager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showToast("onResume...");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            if(isInMultiWindowMode()){
                showToast("onResume...isInMultiWindowMode...yes...");
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        showToast("onPause....");
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        showToast("onMultiWindowModeChanged..." + isInMultiWindowMode);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            showToast("portrait...");
        }else{
            showToast("landscape...");
        }
    }
}

