package com.hfad.cafeastrology;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.sql.Date;
import java.text.DateFormat;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("create Table users(fullname TEXT NOT NULL, username TEXT primary key, password TEXT NOT NULL,  birthdate TEXT NOT NULL, zodiac TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        onCreate(MyDB);
    }

    public Boolean insertData(String fullname, String username, String password, String birthdate, String zodiac){ //, String birthday){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname", fullname);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("birthdate", birthdate);
        contentValues.put("Zodiac", zodiac);

        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public static String getName() {
        return DBNAME;
    }
}