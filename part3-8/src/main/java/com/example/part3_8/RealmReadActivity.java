package com.example.part3_8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import io.realm.Realm;

public class RealmReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_read);

        TextView titleView = (TextView)findViewById(R.id.realm_read_title);
        TextView contentView = (TextView)findViewById(R.id.realm_read_content);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        Realm mRealm = Realm.getDefaultInstance();
        MemoVo vo = mRealm.where(MemoVo.class).equalTo("title", title).findFirst();
        titleView.setText(vo.title);
        contentView.setText(vo.content);
    }
}
