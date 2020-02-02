package com.example.part3_9;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class PracticeResult1 extends AppCompatActivity {

    TextView nameText, phoneText, emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_result1);

        nameText = (TextView)findViewById(R.id.name_text);
        phoneText = (TextView)findViewById(R.id.phone_text);
        emailText = (TextView)findViewById(R.id.email_text);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select name, phone, email from tb_contact " +
                "order by _id desc limit 1", null);

        while (cursor.moveToNext()){
            nameText.setText("Name : " + cursor.getString(0));
            phoneText.setText("Phone : " + cursor.getString(1));
            emailText.setText("Email : " + cursor.getString(2));
        }
    }
}
