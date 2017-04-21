package com.gitrekt.water.controller;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;
import com.gitrekt.water.model.UserReaderContract;
import com.gitrekt.water.model.UserReaderDbHelper;
import com.gitrekt.water.model.UserReport;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity <T> extends FragmentActivity implements OnMapReadyCallback {

    private Model model;
    private final UserReaderDbHelper mDbHelper = new UserReaderDbHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //Get a reference to the model singleton
        model = Model.getInstance();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        /*
        Loops through the water reports and attaches them to maps


         */
        //ArrayList<UserReport> list = model.getUserReports();
        //ArrayList<QualityReport> qpList = model.getQualityReports();
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
        ArrayList<UserReport> itemIds = new ArrayList<>();
        //Grabs information from db for Water Report
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
            if (itemVP.equals("-1") && !itemLon.equals("-1") && !itemLat.equals("")
                    && !itemLat.equals("-1")) {
                UserReport report = new UserReport(itemId, itemWT, itemWC, itemLoc, itemLon, itemLat);
                itemIds.add(report);
                for (UserReport _report: itemIds) {
                    double longitude = Double.parseDouble(report.getLongitude());
                    double latitude = Double.parseDouble(report.getLatitude());
                    LatLng point = new LatLng(longitude, latitude);
                    googleMap.addMarker(new MarkerOptions().position(point).title(report.getWt().toString() + report.getWc()));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(point));
                    googleMap.addMarker(new MarkerOptions().position(point).title(report.getWt() + report.getWc()));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(point));
                }
            }
        }
        cursor.close();
        //Loops through reports and adds them to the mapView
    }


}
