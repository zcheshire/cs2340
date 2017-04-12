package com.gitrekt.water.controller;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;
import com.gitrekt.water.model.UserReaderContract;
import com.gitrekt.water.model.UserReaderDbHelper;
import com.gitrekt.water.model.UserReport;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity <T> extends AppCompatActivity {

    private Model model;

    private TextView loginMessage;
    private Button logoutButton;
    private Button history;

    private Button reportButton;
    private Button addQuality;
    private Button viewQualityReport;
    private ListView lv;
    private WaterReportAdapter adapter;
    UserReaderDbHelper mDbHelper = new UserReaderDbHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        model = Model.getInstance();

        loginMessage = (TextView) findViewById(R.id.loginMessage);
        logoutButton = (Button) findViewById(R.id.logoutButton);
        history = (Button) findViewById(R.id.history);
        reportButton = (Button) findViewById(R.id.reportButton);
        addQuality = (Button) findViewById(R.id.addQuality);
        viewQualityReport = (Button) findViewById(R.id.viewQualityReport);
        lv = (ListView) findViewById(R.id.reportListView);

        loginMessage.setText("Welcome !");
    }
/*

Code to run when the activity resumes
 */
    protected void onResume() {
        super.onRestart();
        ArrayList<UserReport> temp = new ArrayList<>();
       boolean num = getRep(temp);
        if (num) {
            adapter = new WaterReportAdapter(getApplicationContext(), temp);
            lv.setAdapter(adapter);
        }

    }
/*
Gets reports from the database
@param rep ArrayList of userReports
@return boolean wether the reports where added or not

 */
    public boolean getRep(ArrayList<UserReport> rep) {
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
            if (itemVP.equals("-1") && !itemLon.equals("-1")) {
                UserReport report = new UserReport(itemId, itemWT, itemWC, itemLoc, itemLon, itemLat);
                rep.add(report);
                //model.addUserReport(report);
            }
        }
        cursor.close();
        if (rep.size() > 0) {
            return true;
        } else {

            return false;
        }
    }
    protected void onPause() {
        super.onPause();

        loginMessage.setText("");
    }

    //Overriding back so user can't return to login screen without logging out
    @Override
    public void onBackPressed() {
    }

    public void logout(View view) {
        model.setCurrentUser(null);
        super.onBackPressed();
    }
/*

Checks if user is manager before sending them to history page
@param view the current view
 */
    public void history(View view) {

        if (model.getCurrentUser().getUserType().toString() == "Manager") {

            Intent intent = new Intent(this, WaterReportHistory.class);
            startActivity(intent);

        } else {

            loginMessage.setText("Only Managers can view history");
        }

    }


    public void settings(View view) {
        //Change back to SettingsActivity from MapsActivity
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    public void createReport(View view) {
        Intent intent = new Intent(this, ReportActivity.class);
        startActivity(intent);
    }

    public void viewMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void viewQualityReport(View view) {
        if (model.getCurrentUser().userTypee == "Worker" ||model.getCurrentUser().userTypee == "Manager") {

            Intent intent = new Intent(this, PurityActivity.class);
            startActivity(intent);

        } else {

            loginMessage.setText("Only Managers and Workers can create Purity Reports");
        }


    }
    public void viewQualityReports(View view) {
        if (model.getCurrentUser().userTypee == "Manager") {

            Intent intent = new Intent(this, ViewQualityReportActivity.class);
            startActivity(intent);

        } else {

            loginMessage.setText("Only Managers can view History Reports");
        }


    }
}
