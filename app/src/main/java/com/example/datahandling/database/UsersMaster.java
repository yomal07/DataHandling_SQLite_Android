package com.example.datahandling.database;

import android.provider.BaseColumns;

public class UsersMaster {
    //constructor
    private UsersMaster(){}

    //inner class which defines the table contents
    public static class Users implements BaseColumns{
        public static final String TABLE_NAME = "users";
        public static final String COL_USERNAME = "Username";
        public static final String COL_PASSWORD = "Password";
    }
}
