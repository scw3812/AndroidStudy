package com.example.part3_9;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public DBHelper(Context context){
        super(context, "contactdb", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String contactSQL = "create table tb_contact "+
                "(_id integer primary key autoincrement," +
                "name not null," +
                "phone," +
                "email)";
        sqLiteDatabase.execSQL(contactSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i1 == DATABASE_VERSION){
            sqLiteDatabase.execSQL("drop table tb_contact");
            onCreate(sqLiteDatabase);
        }
    }
}
