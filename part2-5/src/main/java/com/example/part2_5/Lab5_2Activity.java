package com.example.part2_5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Lab5_2Activity extends AppCompatActivity implements View.OnClickListener{

    Button alertBtn, listBtn, progressBtn, dateBtn, timeBtn, customDialogBtn;
    AlertDialog customDialog, listDialog, alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab5_2);

        alertBtn = (Button)findViewById(R.id.btn_alert);
        listBtn = (Button)findViewById(R.id.btn_list);
        progressBtn = (Button)findViewById(R.id.btn_progress);
        dateBtn = (Button)findViewById(R.id.btn_date);
        timeBtn = (Button)findViewById(R.id.btn_time);
        customDialogBtn = (Button)findViewById(R.id.btn_custom);

        alertBtn.setOnClickListener(this);
        listBtn.setOnClickListener(this);
        progressBtn.setOnClickListener(this);
        dateBtn.setOnClickListener(this);
        timeBtn.setOnClickListener(this);
        customDialogBtn.setOnClickListener(this);
    }

    private void showToast(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if(dialogInterface == customDialog && i == DialogInterface.BUTTON_POSITIVE){
                showToast("custom dialog 확인 click...");
            }else if(dialogInterface == listDialog){
                String datas[] = getResources().getStringArray(R.array.dialog_array);
                showToast(datas[i] + " 선택 하셨습니다.");
            }else if(dialogInterface == alertDialog && i == DialogInterface.BUTTON_POSITIVE){
                showToast("alert dialog ok click...");
            }
        }
    };

    @Override
    public void onClick(View view) {
        if(view == alertBtn){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setTitle("알림");
            builder.setMessage("정말 종료 하시겠습니까?");
            builder.setPositiveButton("OK", dialogListener);
            builder.setNegativeButton("No", null);

            alertDialog = builder.create();
            alertDialog.show();
        }else if(view == listBtn){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("알람 벨소리");
            builder.setSingleChoiceItems(R.array.dialog_array, 0, dialogListener);

            builder.setPositiveButton("확인", null);
            builder.setNegativeButton("취소", null);

            listDialog = builder.create();
            listDialog.show();
        }else if(view == progressBtn){
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setIcon(android.R.drawable.ic_dialog_alert);
            progressDialog.setTitle("Wait...");
            progressDialog.setMessage("서버 연동 중입니다. 잠시만 기다리세요.");

            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setIndeterminate(true);

            progressDialog.show();
        }else if(view == dateBtn){
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    showToast(i + "/" + (i1 + 1) + "/" + i2);
                }
            }, year, month, day);
            dateDialog.show();
        }else if(view == timeBtn){
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    showToast(i + ":" + i1);
                }
            }, hour, minute, false);
            timePickerDialog.show();
        }else if(view == customDialogBtn){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            LayoutInflater layoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(R.layout.dialog_layout, null);
            builder.setView(v);
            builder.setPositiveButton("확인", dialogListener);
            builder.setNegativeButton("취소", null);

            customDialog = builder.create();
            customDialog.show();
        }
    }
}
