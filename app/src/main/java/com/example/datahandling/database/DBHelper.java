package com.example.datahandling.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    //database name
    public static final String DATABASE_NAME="Userinfo.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + UsersMaster.Users.TABLE_NAME + " (" +
                        UsersMaster.Users._ID + " INTEGER PRIMARY KEY," +
                        UsersMaster.Users.COL_USERNAME + " TEXT," +
                        UsersMaster.Users.COL_PASSWORD + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);

    }

    public void addUser(String username, String password){
        //object creation of the db in write mode
        SQLiteDatabase db = getWritableDatabase();

        //create an object of contentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(UsersMaster.Users.COL_USERNAME,username);
        contentValues.put(UsersMaster.Users.COL_PASSWORD,password);

        //using the insert method in the SQLiteDatabase
        long result = db.insert(UsersMaster.Users.TABLE_NAME,null,contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
