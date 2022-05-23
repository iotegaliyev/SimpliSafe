package com.example.simplisafe;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Users.db";

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users" +
                "(username VARCHAR primary key, " +
                "fullName VARCHAR, " +
                "age INTEGER, " +
                "address VARCHAR, " +
                "cardNumber VARCHAR, " +
                "password VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        onCreate(MyDB);
    }

    public Boolean insertData(String username, String fullName, int age, String address, String cardNumber, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("fullName", fullName);
        contentValues.put("age", age);
        contentValues.put("address", address);
        contentValues.put("cardNumber", cardNumber);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        return result != -1;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});
        return cursor.getCount() > 0;
    }

    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});
        return cursor.getCount() > 0;
    }

    public Cursor getData(String userName) {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users where username = '" + userName + "'", null);
        return cursor;
    }
}