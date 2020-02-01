package com.example.part3_8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.BufferUnderflowException;

import io.realm.Realm;

public class Lab8_2 extends AppCompatActivity implements View.OnClickListener {

    EditText titleView, contentView;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab8_2);

        titleView = (EditText)findViewById(R.id.realm_add_title);
        contentView = (EditText)findViewById(R.id.realm_add_content);
        addBtn = (Button)findViewById(R.id.realm_add_btn);

        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String title = titleView.getText().toString();
        final String content = contentView.getText().toString();

        Realm.init(this);
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                MemoVo vo = realm.createObject(MemoVo.class);
                vo.title = title;
                vo.content = content;
            }
        });

        Intent intent = new Intent(this, RealmReadActivity.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }
}
