package com.gitrekt.water.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by zacharycheshire on 2/14/17.
 */

public class Model {
    //Constructor
    public Model() {
        this.database = new com.gitrekt.water.model.SQLiteDatabase();
    }

    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    private User currentUser;
    private String searchType;

    private Database database;
    /**
     * Returns current user
     * @return User user
     */
    public User getCurrentUser() {
        return this.currentUser;
    }

    /**
     * Sets current user
     * @param user
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    //defines type to search for water reports
    public void setSearchType (String search) {

        this.searchType = search;
    }
    public String getSearchType () {

        return this.searchType;
    }

    /**
     * Adds a user to the user list
     * @param user
     */
    public void addUserToDB(Context context, User user){
        database.setContext(context);
        try {
            database.insertUser(user);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    //adds a user report to the user report list
    //Gets the Quality Reports
    /**
     * Adds a user report to the user report list
     * @param ur
     */
    public void addUserReportToDB(Context context, UserReport ur){
        database.setContext(context);
        try {
            database.insertUR(ur, getCurrentUser());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Adds a quality report to the quality report list
     * @param qr
     */
    public void addQualityReportToDB(Context context, QualityReport qr){
        database.setContext(context);
        try {
            database.insertQR(qr, getCurrentUser());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Gets the user report array
     * @return Array of user reports
     */
    public ArrayList<UserReport> getUserReportsFromDB(Context context) {
        database.setContext(context);
        ArrayList<UserReport> URs = database.getURs();
        return URs;
    }

    /**
     * Sets the user report array to an existing array
     * @param arr
     */
    /*public void setUserReports(ArrayList<UserReport> arr) {
        this.userReports = arr;
    }*/

    /**
     * Gets the quality report array
     * @return Array of quality reports
     */
    public ArrayList<QualityReport> getQualityReportsFromDB(Context context) {
        database.setContext(context);
        ArrayList<QualityReport> QRs = database.getQRs();
        return QRs;
    }

    public ArrayList<User> getUsersFromDB(Context context) {
        database.setContext(context);
        ArrayList<User> result = database.getUsers();
        return result;
    }

    public void initializeMap(Context context, GoogleMap googleMap) {
        ArrayList<UserReport> URs = this.getUserReportsFromDB(context);

        //Iterate through UserReports and create points on the map
        for (UserReport _report: URs) {
            double longitude = Double.parseDouble(_report.getLongitude());
            double latitude = Double.parseDouble(_report.getLatitude());
            LatLng point = new LatLng(longitude, latitude);
            googleMap.addMarker(new MarkerOptions().position(point).title(_report.getWt() + _report.getWc()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(point));
        }
    }

    public void resetDatabase(Context context) {
        database.setContext(context);
        database.reset();
    }

    public boolean validateUser(ArrayList<User> users, User user) {
        for (User u : users) {
            if (user.validate(u)) { //Return true if there is a match
                return true;
            }
        }
        return false;
    }
}
