package com.example.part4_13;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, "calldb", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tableSql = "create table tb_calllog (_id integer primary key autoincrement, " +
                "name not null, photo, date, phone)";

        sqLiteDatabase.execSQL(tableSql);

        sqLiteDatabase.execSQL("insert into tb_calllog (name, photo, date, phone) " +
                "values ('홍길동', 'yes', '휴대전화, 1일전', '010010')");
        sqLiteDatabase.execSQL("insert into tb_calllog (name, photo, date, phone) " +
                "values ('류현진', 'yes', '휴대전화, 1일전', '000010')");
        sqLiteDatabase.execSQL("insert into tb_calllog (name, photo, date, phone) " +
                "values ('강정호', 'no', '휴대전화, 2일전', '014010')");
        sqLiteDatabase.execSQL("insert into tb_calllog (name, photo, date, phone) " +
                "values ('김현수', 'yes', '휴대전화, 3일전', '010020')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i1 == DATABASE_VERSION){
            sqLiteDatabase.execSQL("drop table tb_calllog");
            onCreate(sqLiteDatabase);
        }
    }
}
