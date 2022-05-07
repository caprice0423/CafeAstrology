package com.hfad.cafeastrology;

import static androidx.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class DatabaseHelperTest {

    private SQLiteDatabase db;
    private DatabaseHelper data;
    private ContentValues contentValues;
    private String fullName, userName, passWord, birthDate, zodiac;

    @Before
    public void setUp() throws Exception {
        //get context
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //delete existing database
        context.deleteDatabase(DatabaseHelper.DBNAME);
        //create a new database
        data = new DatabaseHelper(context);
        //get the database
        db = data.getWritableDatabase();
        //put values into the columns
        contentValues = new ContentValues();
        contentValues.put("fullname", "Sadjell");
        contentValues.put("username","sadje");
        contentValues.put("password", "ser210");
        contentValues.put("birthdate", "01/06/1999");
        contentValues.put("Zodiac", "capricorn");
        //insert into the databasr
        db.insert("users", null, contentValues);
    }
    @Test
    public void checkUsernameTest(){
        //get information from the user previously inserted
        Cursor cursor = db.rawQuery("Select *  from users where fullname = ?", new String[]{"Sadjell"});
        //put information into variables
        if(cursor.moveToFirst()){
            fullName = cursor.getString(0);
            userName = cursor.getString(1);
            passWord = cursor.getString(2);
            birthDate = cursor.getString(3);
            zodiac = cursor.getString(4);
        }
        //checks if they are the same
        assertEquals(cursor.getCount(),1);
        assertTrue(fullName.equals("Sadjell"));
        assertTrue(userName.equals("sadje"));
        assertTrue(passWord.equals("ser210"));
        assertTrue(birthDate.equals("01/06/1999"));
        assertTrue(zodiac.equals("capricorn"));
        //delete information for the user inserted
        Cursor cursor1 = db.rawQuery("Delete from users where fullname = ?", new String[]{"Sadjell"});
        //check if they disappeared
        assertEquals(cursor1.getCount(),0);
    }

    @After
    public void tearDown() throws Exception {
        data.close();
    }
}