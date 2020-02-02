package com.example.part3_9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Practice1 extends AppCompatActivity implements View.OnClickListener{

    EditText nameEdit, phoneEdit, emailEdit;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice1);

        nameEdit = (EditText)findViewById(R.id.name_edit);
        phoneEdit = (EditText)findViewById(R.id.phone_edit);
        emailEdit = (EditText)findViewById(R.id.email_edit);
        addBtn = (Button)findViewById(R.id.add_btn);

        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = nameEdit.getText().toString();
        String phone = phoneEdit.getText().toString();
        String email = emailEdit.getText().toString();

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("insert into tb_contact (name, phone, email) values (?, ?, ?)",
                new String[]{name, phone, email});
        db.close();

        Intent intent = new Intent(this, PracticeResult1.class);
        startActivity(intent);
    }
}
