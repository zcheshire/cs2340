package com.gitrekt.water.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by zacharycheshire on 3/12/17.
 */

public class QualityReport {
    private String username;
    private OverallCondition overallCondition;
    private final String location;
    public String overallConditionn;
    private final String longitude;
    private final String latitude;
    private Date date;
    //double time;
    //Constructor
    private final String virusPPM;
    private final String contaminantPPM;
    private static int reportNumber;

    public QualityReport() {
        this.username = "";
        this.overallCondition = null;
        this.location = "";
        this.longitude = "";
        this.latitude = "";
        this.date = null;
        this.virusPPM = "";
        this.contaminantPPM = "";
    }
    /**
     * Generates a quality report object
     * @param username
     * @param overallCondition
     * @param location
     * @param longitude
     * @param latitude
     * @param date
     * @param virusPPM
     * @param contaminantPPM
     */
    public QualityReport (String username, OverallCondition overallCondition, String location, String longitude, String latitude,
                       Date date, String virusPPM, String contaminantPPM) {
        this.username = username;
        this.overallCondition = overallCondition;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
        reportNumber = reportNumber + 1;
    }
    //Constructor for a username and not type

    /**
     * Gets user that made the report
     * @return User user
     */
    public String getUsername () {
        return this.username;
    }

    /**
     * Auto generates a report ID
     * @return String report number
     */
    public String getReportNumber () {
        return "" + reportNumber;

    }

    /**
     * Gets Condition of the water
     * @return Overall Condition condition
     */
    public OverallCondition getCondition () {

        return this.overallCondition;

    }

    /**
     * Gets location of the water that was reported
     * @return String location
     */
    public String getLocation () {

        return this.location;

    }
    //Gets longitude of the report

    /**
     * Gets longitude of the water that was reported
     * @return String longitude
     */
    public String getLongitude () {

        return this.longitude;
    }
    //Gets latitude of the report


    /**
     * Gets latitude of the water that was reported
     * @return String latitude
     */
    public String getLatitude () {

        return this.latitude;
    }
    //Gets the date for the report

    /**
     * Gets the date that the water was reported
     * @return Calender date
     */
    public Date getDate () {

        return this.date;

    }

    /**
     * Gets the time that the water was reported
     * @return double Time
     */
   /* public double getTime () {

        return this.time;

    }*/
    //Gets the virus PPM for the report

    /**
     * Gets the Virus PPM of the water reported
     * @return String virus ppm
     */
    public String getVirusPPM () {

        return this.virusPPM;

    }
    //Gets the contaminant PPM for the report

    /**
     * Gets the Containment PPM of the water reported
     * @return String containment ppm
     */
    public String getContaminantPPM () {

        return this.contaminantPPM;

    }

}
