package com.gitrekt.water.model;

import java.util.Calendar;

/**
 * Created by zacharycheshire on 3/12/17.
 */

public class QualityReport {
    User user;
   public String userr;
    OverallCondition overallCondition;
    String location;
   public String overallConditionn;
    String longitude;
    String latitude;
    Calendar date;
    //double time;
    String virusPPM;
    String contaminantPPM;
    static int reportNumber;
    //Constructor
    public QualityReport (User user, OverallCondition overallCondition, String location, String longitude, String latitude,
                       Calendar date, String virusPPM, String contaminantPPM) {
        this.user = user;
        this.overallCondition = overallCondition;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
        //this.time = time;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
        this.reportNumber = reportNumber + 1;


    }
    //Constructor for a username and not type
    public QualityReport (String user, String overallCondition, String location, String longitude, String latitude, String virusPPM, String contaminantPPM) {
        this.userr = user;
        this.overallConditionn = overallCondition;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
       // this.date = date;
        //this.time = time;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
        this.reportNumber = reportNumber + 1;


    }
    //Gets user that made the report
    public User getUser () {

        return this.user;

    }
    //Auto generates a report ID
    public String getReportNumber () {
        return "" + reportNumber;

    }
    //Gets Condition of the water
    public OverallCondition getCondition () {

        return this.overallCondition;

    }
    //Gets location of the water that was reported
    public String getLocation () {

        return this.location;

    }
    //Gets longitude of the report
    public String getLongitude () {

        return this.longitude;
    }
    //Gets latitude of the report

    public String getLatitude () {

        return this.latitude;
    }
    //Gets the date for the report
    public Calendar getDate () {

        return this.date;

    }
   /* public double getTime () {

        return this.time;

    }*/
    //Gets the virus PPM for the report
    public String getVirusPPM () {

        return this.virusPPM;

    }
    //Gets the contaminant PPM for the report
    public String getContaminantPPM () {

        return this.contaminantPPM;

    }

}
