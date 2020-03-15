package com.example.part4_13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Currency;

public class Practice1 extends AppCompatActivity {

    boolean callPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice1);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            callPermission =true;
        }
        if(!callPermission){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 200);
        }

        ListView listView = findViewById(R.id.main_list);
        ArrayList<CallLogVO> datas = new ArrayList<>();

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select name, photo, date, phone from tb_calllog", null);
        while(cursor.moveToNext()){
            CallLogVO vo = new CallLogVO();
            vo.name = cursor.getString(0);
            vo.photo = cursor.getString(1);
            vo.date = cursor.getString(2);
            vo.phone = cursor.getString(3);
            datas.add(vo);
        }
        db.close();

        CallLogAdapter adapter = new CallLogAdapter(this, R.layout.list_item, datas);
        listView.setAdapter(adapter);
    }
}
