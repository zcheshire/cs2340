package com.gitrekt.water.model;

import java.util.Calendar;

/**
 * Created by zacharycheshire on 3/12/17.
 */

public class QualityReport {
    private User user;
   public String userr;
    private OverallCondition overallCondition;
    private final String location;
   public String overallConditionn;
    private final String longitude;
    private final String latitude;
    private Calendar date;
    //double time;
    private final String virusPPM;
    private final String contaminantPPM;
    private static int reportNumber;

    /**
     * Generates a quality report object
     * @param user
     * @param overallCondition
     * @param location
     * @param longitude
     * @param latitude
     * @param date
     * @param virusPPM
     * @param contaminantPPM
     */
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
        reportNumber = reportNumber + 1;
    }

    /**
     * Auto generates a report ID
     * @return String report number
     */
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
        reportNumber = reportNumber + 1;
    }

    /**
     * Gets user that made the report
     * @return User user
     */
    public User getUser () {

        return this.user;

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

    /**
     * Gets longitude of the water that was reported
     * @return String longitude
     */
    public String getLongitude () {

        return this.longitude;
    }

    /**
     * Gets latitude of the water that was reported
     * @return String latitude
     */
    public String getLatitude () {

        return this.latitude;
    }

    /**
     * Gets the date that the water was reported
     * @return Calender date
     */
    public Calendar getDate () {

        return this.date;

    }

    /**
     * Gets the time that the water was reported
     * @return double Time
     */
   /* public double getTime () {

        return this.time;

    }*/

    /**
     * Gets the Virus PPM of the water reported
     * @return String virus ppm
     */
    public String getVirusPPM () {

        return this.virusPPM;

    }

    /**
     * Gets the Containment PPM of the water reported
     * @return String containment ppm
     */
    public String getContaminantPPM () {

        return this.contaminantPPM;

    }

}
