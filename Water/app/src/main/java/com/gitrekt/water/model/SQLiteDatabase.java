package com.gitrekt.water.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by John on 4/23/2017.
 */

public class SQLiteDatabase implements Database {
    private Context context;

    public void setContext(Context context){
        this.context = context;
    }

    public void reset() {
        UserReaderDbHelper mDbHelper = new UserReaderDbHelper(this.context);
        android.database.sqlite.SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL(UserReaderContract.SQL_DELETE_ENTRIES);
        db.execSQL(UserReaderContract.SQL_CREATE_ENTRIES);
    }

    public void insertUser(User user){

        UserReaderDbHelper mDbHelper = new UserReaderDbHelper(this.context);
        // Gets the data repository in write mode
        android.database.sqlite.SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME, user.getUserName());

        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_PASSWORD, user.getPassWord());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_WT, "");
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_WC, "");
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_VPPM, "-1");
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_ADMIN, user.getUserType().toString());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_LON, "-1");
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_LAT, "");
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_LOC, "");
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_DATE, "");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UserReaderContract.FeedEntry.TABLE_NAME, null, values);
        System.out.println(user);
    }

    public void insertQR(QualityReport qr, User currentUser){
        UserReaderDbHelper mDbHelper = new UserReaderDbHelper(this.context);
        android.database.sqlite.SQLiteDatabase db = mDbHelper.getWritableDatabase();

        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        String dateString = df.format(qr.getDate());

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME,currentUser.getUserName());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_PASSWORD, "");
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_VPPM, qr.getVirusPPM());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_CPPM, qr.getContaminantPPM());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_WC, qr.getCondition().toString());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_LON, qr.getLongitude());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_LAT, qr.getLatitude());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_LOC, qr.getLocation());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_DATE, dateString);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UserReaderContract.FeedEntry.TABLE_NAME, null, values);
    }

    public void insertUR(UserReport ur, User currentUser){
        UserReaderDbHelper mDbHelper = new UserReaderDbHelper(this.context);
        // Gets the data repository in write mode
        android.database.sqlite.SQLiteDatabase db = mDbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME, currentUser.getUserName());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_PASSWORD, "");
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_WT, ur.getWt());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_WC, ur.getCondition().toString());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_VPPM, "-1");
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_LON, ur.getLongitude());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_LAT, ur.getLatitude());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_LOC, ur.getLocation());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_DATE, "");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UserReaderContract.FeedEntry.TABLE_NAME, null, values);
        System.out.println(ur);
    }

    public ArrayList<User> getUsers(){
        UserReaderDbHelper mDbHelper = new UserReaderDbHelper(this.context);
        android.database.sqlite.SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                UserReaderContract.FeedEntry._ID,
                UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME,
                UserReaderContract.FeedEntry.COLUMN_NAME_PASSWORD,
                UserReaderContract.FeedEntry.COLUMN_NAME_ADMIN

        };

        // Filter results WHERE "username" is anything
        String selection = UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME + " = ?";

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME + " DESC";

        //Query the db using a cursor
        Cursor cursor = db.query(
                UserReaderContract.FeedEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,//"",//selection,                    // The columns for the WHERE clause
                null,//selectionArgs,                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        /* THE LIST OF USERS TO BE RETURNED */
        ArrayList<User> result = new ArrayList<>();

        //Grabs usernames from the db and add them to the itemIDS arrayList
        while (cursor.moveToNext()) {
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME));
            String itemPass = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_PASSWORD));
            String itemAdmin = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_ADMIN));
            User user = new User(itemId, itemPass, UserType.valueOf(itemAdmin));
            result.add(user);
        }
        cursor.close();
        return result;
    }

    public ArrayList<QualityReport> getQRs(){
        UserReaderDbHelper mDbHelper = new UserReaderDbHelper(this.context);
        android.database.sqlite.SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                UserReaderContract.FeedEntry._ID,
                UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME,
                //UserReaderContract.FeedEntry.COLUMN_NAME_PASSWORD,
                UserReaderContract.FeedEntry.COLUMN_NAME_VPPM,
                UserReaderContract.FeedEntry.COLUMN_NAME_CPPM,
                UserReaderContract.FeedEntry.COLUMN_NAME_WC,
                UserReaderContract.FeedEntry.COLUMN_NAME_LON,
                UserReaderContract.FeedEntry.COLUMN_NAME_LAT,
                UserReaderContract.FeedEntry.COLUMN_NAME_LOC,
                UserReaderContract.FeedEntry.COLUMN_NAME_DATE
        };

        // Filter results WHERE "username" is anything
        String selection = UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME + " = ?";

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME + " DESC";

        //Query the db using a cursor
        Cursor cursor = db.query(
                UserReaderContract.FeedEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,//"",//selection,                    // The columns for the WHERE clause
                null,//selectionArgs,                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        ArrayList<QualityReport> QRs = new ArrayList<>();
        //Grabs usernames from the db and add them to the itemIDS arrayList
        //model.setUserReports(null);
        while (cursor.moveToNext()) {
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME));
            String itemVP = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_VPPM));
            String itemCP = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_CPPM));
            String itemWC = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_WC));
            String itemLon = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_LON));
            String itemLat = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_LAT));
            String itemLoc = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_LOC));
            String itemDate = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_DATE));

            //Parse date string
            DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Date parsedDate = new Date();
            try {
                parsedDate = df.parse(itemDate);
            } catch (Exception e) {
                System.out.println("Error parsing date for QR");
            }

            //Since Users have been filtered, just filter out UserReports with VPPM
            if (!itemVP.equals("-1")) {
                QualityReport report = new QualityReport(itemId, OverallCondition.valueOf(itemWC), itemLoc,
                        itemLon, itemLat, parsedDate, itemVP, itemCP);
                QRs.add(report);
            }
        }
        cursor.close();
        return QRs;
    }

    public ArrayList<UserReport> getURs(){
        UserReaderDbHelper mDbHelper = new UserReaderDbHelper(this.context);

        /* Loops through the water reports and attaches them to maps */
        android.database.sqlite.SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                UserReaderContract.FeedEntry._ID,
                UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME,
                //UserReaderContract.FeedEntry.COLUMN_NAME_PASSWORD,
                UserReaderContract.FeedEntry.COLUMN_NAME_WT,
                UserReaderContract.FeedEntry.COLUMN_NAME_VPPM,
                UserReaderContract.FeedEntry.COLUMN_NAME_WC,
                UserReaderContract.FeedEntry.COLUMN_NAME_LON,
                UserReaderContract.FeedEntry.COLUMN_NAME_LAT,
                UserReaderContract.FeedEntry.COLUMN_NAME_LOC
        };

        // Filter results WHERE "username" is anything
        String selection = UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME + " = ?";

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME + " DESC";

        //Query the db using a cursor
        Cursor cursor = db.query(
                UserReaderContract.FeedEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,//"",//selection,                    // The columns for the WHERE clause
                null,//selectionArgs,                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        ArrayList<UserReport> URs = new ArrayList<>();

        //Grabs information from db for Water Report
        while (cursor.moveToNext()) {
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME));
            String itemWT = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_WT));
            String itemWC = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_WC));
            String itemLon = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_LON));
            String itemVP = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_VPPM));
            String itemLat = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_LAT));
            String itemLoc = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_LOC));
            //Conditions - Lat & Lon so we know entry is not a User
            //             VPPM = -1 so we know entry is not a QualityReport
            if (itemVP.equals("-1") && !itemLon.equals("-1") && !itemLat.equals("")
                    && !itemLat.equals("-1")) {
                UserReport report = new UserReport();//itemId, itemWT, itemWC, itemLoc, itemLon, itemLat);
                URs.add(report);
            }
        }
        cursor.close();
        return URs;
    }
}
