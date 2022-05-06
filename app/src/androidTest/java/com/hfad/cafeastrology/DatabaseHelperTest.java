package com.hfad.cafeastrology;

import static androidx.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.assertTrue;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class DatabaseHelperTest {

    private SQLiteDatabase db;
    private DatabaseHelper data;
    private ContentValues contentValues;

    @Before
    public void setUp() throws Exception {
        getTargetContext().deleteDatabase(DatabaseHelper.DBNAME);
        data = new DatabaseHelper(getTargetContext());
        db = data.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("fullname", "Sadjell");
        contentValues.put("username","sad");
        contentValues.put("password", "ser210");
        contentValues.put("birthdate", "01/06/1999");
        contentValues.put("Zodiac", "capricorn");
        db.insert("users", null, contentValues);

    }
    @Test
    public void checkUsernameTest(){
        Cursor cursor = db.rawQuery("Select fullname from users where fullname = ?", new String[]{"Sadjell"});
        Cursor cursor1 = db.rawQuery("Select username from users where username = ?", new String[]{"sad"});
        Cursor cursor2 = db.rawQuery("Select password from users where password = ?", new String[]{"ser210"});
        Cursor cursor3= db.rawQuery("Select birthdate from users where birthdate = ?", new String[]{"01/06/1999"});
        Cursor cursor4 = db.rawQuery("Select zodiac from users where username = ?", new String[]{"capricorn"});

        assertTrue(cursor.equals("Sadjell"));
        assertTrue(cursor1.equals("sad"));
        assertTrue(cursor2.equals("ser210"));
        assertTrue(cursor3.equals("01/06/1999"));
        assertTrue(cursor4.equals("capricorn"));

    }

    @After
    public void tearDown() throws Exception {
        data.close();
    }
}