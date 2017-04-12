package com.gitrekt.water.model;

/**
 * Created by zacharycheshire on 3/27/17.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/*
Class outlines Database


 */

public final class UserReaderContract {

    private UserReaderContract() {};
    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {

        public static final String TABLE_NAME = "entry";



        // public static final int _ID = -1;

        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_ADMIN = "admintype";
        public static final String COLUMN_NAME_WT = "watertype";
        public static final String COLUMN_NAME_WC = "watercondition";
        public static final String COLUMN_NAME_LON = "longitude";
        public static final String COLUMN_NAME_VPPM = "virus";
        public static final String COLUMN_NAME_CPPM = "contaminant";
        public static final String COLUMN_NAME_LAT = "latitude";
        public static final String COLUMN_NAME_LOC = "location";

    }
    /*


     */
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY, " +
                    FeedEntry.COLUMN_NAME_USERNAME + " TEXT, " +
                    FeedEntry.COLUMN_NAME_PASSWORD + " TEXT, " +
                    FeedEntry.COLUMN_NAME_ADMIN + " TEXT, " +
                    FeedEntry.COLUMN_NAME_WT + " TEXT, " +
                    FeedEntry.COLUMN_NAME_WC + " TEXT, " +
                    FeedEntry.COLUMN_NAME_LON + " TEXT, " +
                    FeedEntry.COLUMN_NAME_VPPM + " TEXT, " +
                    FeedEntry.COLUMN_NAME_CPPM + " TEXT, " +
                    FeedEntry.COLUMN_NAME_LAT + " TEXT, " +
                    FeedEntry.COLUMN_NAME_LOC + " TEXT" + ")";


    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

}
