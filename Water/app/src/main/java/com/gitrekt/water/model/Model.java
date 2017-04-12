package com.gitrekt.water.model;

import java.util.ArrayList;

/**
 * Created by zacharycheshire on 2/14/17.
 */

public class Model {
    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    private User currentUser;
<<<<<<< HEAD
    private String searchType;
    private ArrayList<User> userList = new ArrayList<User>();
    private ArrayList<UserReport> userReports = new ArrayList<UserReport>();
    private ArrayList<QualityReport> qualityReports = new ArrayList<QualityReport>();
    //Gets current user object
    public User getCurrentUser() {
        return this.currentUser;
    }
    //Sets the current user object
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
    //Adds a user to the user list
    public void addUser(User user) {
        this.userList.add(user);
    }
    //removes user from the user list
    public void removeUser(User user) {
        this.userList.remove(user);
    }
    //gets the list of users
=======
    private final ArrayList<User> userList = new ArrayList<>();
    private ArrayList<UserReport> userReports = new ArrayList<>();
    private final ArrayList<QualityReport> qualityReports = new ArrayList<>();

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

    /**
     * Adds a user to the user list
     * @param user
     */
    public void addUser(User user) {
        this.userList.add(user);
    }

    /**
     * removes user from a list
     * @param user
     */
    public void removeUser(User user) {
        this.userList.remove(user);
    }

    /**
     * Gets the user array
     * @return Array of Users
     */
>>>>>>> 2aa37df7640bed18e7c9d8a1d44653d4ce49edc5
    public ArrayList<User> getUserList() {
        return this.userList;
    }
    //defines type to search for water reports
    public void setSearchType (String search) {

<<<<<<< HEAD
        this.searchType = search;
    }
    public String getSearchType () {

        return this.searchType;
    }
    //adds a user report to the user report list
    public void addUserReport(UserReport ur) { this.userReports.add(ur); }
    //adds a quality report to the quality report list
    public void addQualityReport(QualityReport qr) { this.qualityReports.add(qr); }
    //Gets the userReport list
    public ArrayList<UserReport> getUserReports() {
        return this.userReports;
    }
    //Sets the User Report list
    public void setUserReports(ArrayList<UserReport> arr) {
        this.userReports = arr;
    }
    //Gets the Quality Reports
=======
    /**
     * Adds a user report to the user report list
     * @param ur
     */
    public void addUserReport(UserReport ur) {
        this.userReports.add(ur);
    }

    /**
     * Adds a quality report to the quality report list
     * @param qr
     */
    public void addQualityReport(QualityReport qr) {
        this.qualityReports.add(qr);
    }

    /**
     * Gets the user report array
     * @return Array of user reports
     */
    public ArrayList<UserReport> getUserReports() {
        return this.userReports;
    }

    /**
     * Sets the user report array to an existing array
     * @param arr
     */
    public void setUserReports(ArrayList<UserReport> arr) {
        this.userReports = arr;
    }

    /**
     * Gets the quality report array
     * @return Array of quality reports
     */
>>>>>>> 2aa37df7640bed18e7c9d8a1d44653d4ce49edc5
    public ArrayList<QualityReport> getQualityReports() {
        return this.qualityReports;
    }
}
