package com.gitrekt.water.controller;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;
import com.gitrekt.water.model.UserReaderContract;
import com.gitrekt.water.model.UserReaderDbHelper;
import com.gitrekt.water.model.UserReport;

import java.util.ArrayList;

public class WaterReportHistory extends AppCompatActivity {
    private EditText searchField;
    private TextView nameShow;
    private TextView locationShow;
    private TextView wtShow;
    private TextView wcShow;
    private Model model;
    private Button submitButton;
    UserReaderDbHelper mDbHelper = new UserReaderDbHelper(this);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_report_history);
        searchField = (EditText) findViewById(R.id.searchText);
        nameShow = (TextView) findViewById(R.id.nameShow);
        locationShow = (TextView) findViewById(R.id.locationShow);
        wtShow = (TextView) findViewById(R.id.wtShow);
        wcShow = (TextView) findViewById(R.id.wcShow);

        model = Model.getInstance();



    }
/*
Sumbits the managers request and finds the report in the database
@param view the current view
 */
    public void submit(View view) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                UserReaderContract.FeedEntry._ID,
                UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME,
                UserReaderContract.FeedEntry.COLUMN_NAME_PASSWORD,
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
        //Grabs information from db
        model.setUserReports(null);
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
            if (itemLoc.equals(searchField.getText().toString())) {

                nameShow.setText(itemId);
                locationShow.setText(itemLoc);
                wtShow.setText(itemWT);
                wcShow.setText(itemWC);



            }
        }
        cursor.close();
        model.setSearchType(searchField.getText().toString());
        for (UserReport rep: model.getUserReports()) {

            if (rep.getLocation().equals(searchField.getText().toString())) {

                nameShow.setText(rep.getUserr());
                locationShow.setText(rep.getLocation());
                wtShow.setText(rep.getWt());
                wcShow.setText(rep.getWc());



            }

        }
    }}
