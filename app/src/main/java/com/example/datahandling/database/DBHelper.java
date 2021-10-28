package com.example.datahandling.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

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

    //insert data
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

    //read all data
    public List selectAll(){
        //object creation of the db in read mode
        SQLiteDatabase db = getReadableDatabase();

        String [] projection = {
                UsersMaster.Users._ID,
                UsersMaster.Users.COL_USERNAME,
                UsersMaster.Users.COL_PASSWORD,
        };

        String sort = UsersMaster.Users._ID + " DESC";

        Cursor cursor = db.query(
                UsersMaster.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sort
        );

        List user = new ArrayList <> ();

        while(cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COL_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COL_PASSWORD));

            user.add(username + " : " + password);
        }
        cursor.close();

        return user;

    }

    //delete data
    public void deleteInfo(String userName){
        SQLiteDatabase db = getReadableDatabase();
        String selection = UsersMaster.Users.COL_USERNAME + " LIKE ?";
        String [] selectionArgs = { userName };
        db.delete(UsersMaster.Users.TABLE_NAME,selection,selectionArgs);


    }

    //update data
    public void editUser(View view,String userName, String password){
        //object creation of db in read mode
        SQLiteDatabase db = getReadableDatabase();

        //create an object of content values
        ContentValues contentValues = new ContentValues();
        contentValues.put(UsersMaster.Users.COL_PASSWORD,password);

        //specify the rows to be deleted
        String selection = UsersMaster.Users.COL_USERNAME + " LIKE ?";
        String[] selectionArgs = {userName};

        int count = db.update(UsersMaster.Users.TABLE_NAME,contentValues,selection,selectionArgs);

        //implementing the snackbar
        Snackbar snackbar = Snackbar.make(view,count+" rows were affected", Snackbar.LENGTH_LONG);
        snackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
        snackbar.show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
