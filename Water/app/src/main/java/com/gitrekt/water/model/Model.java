package com.gitrekt.water.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zacharycheshire on 2/14/17.
 */

public class Model {
    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    private User currentUser;
    private ArrayList<User> userList = new ArrayList<User>();
    private ArrayList<UserReport> userReports = new ArrayList<UserReport>();
    private ArrayList<QualityReport> qualityReports = new ArrayList<QualityReport>();
    
    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void addUser(User user) {
        this.userList.add(user);
    }

    public void removeUser(User user) {
        this.userList.remove(user);
    }

    public ArrayList<User> getUserList() {
        return this.userList;
    }

    public void addUserReport(UserReport ur) { this.userReports.add(ur); }
    public void addQualityReport(QualityReport qr) { this.qualityReports.add(qr); }

    public ArrayList<UserReport> getUserReports() {
        return this.userReports;
    }
    public void setUserReports(ArrayList<UserReport> arr) {
        this.userReports = arr;
    }

    public ArrayList<QualityReport> getQualityReports() {
        return this.qualityReports;
    }
}
