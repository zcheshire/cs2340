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
    private String searchType;
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
    public ArrayList<User> getUserList() {
        return this.userList;
    }
    //defines type to search for water reports
    public void setSearchType (String search) {

        this.searchType = search;
    }
    public String getSearchType () {

        return this.searchType;
    }
    //adds a user report to the user report list
    //Gets the Quality Reports
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
    public ArrayList<QualityReport> getQualityReports() {
        return this.qualityReports;
    }
}
