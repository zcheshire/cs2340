package com.gitrekt.water.model;

/**
 * Created by zacharycheshire on 3/1/17.
 */

public class UserReport {
    private User user;
    private WaterType waterType;
    private ConditionType conditionType;
    private final String location;
    private final String longitude;
    private final String latitude;
    private static int reportNumber;
    private String username;
    private String waterTypee;
    private String conditionTypee;

    public UserReport() {
        this.username = "";
        this.conditionType = null;
        this.waterType = null;
        this.location = "";
        this.longitude = "";
        this.latitude = "";
    }
    /**
     * Creates a user report object
     * @param username
     * @param waterType
     * @param conditionType
     * @param location
     * @param longitude
     * @param latitude
     */
    public UserReport (String username, WaterType waterType, ConditionType conditionType, String location, String longitude, String latitude) {
        this.username = username;
        this.waterType = waterType;
        this.conditionType = conditionType;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
        reportNumber = reportNumber + 1;


    }
    /**
     * Creates a user report object for database
     * @param user
     * @param waterType
     * @param conditionType
     * @param location
     * @param longitude
     * @param latitude
     */
    /*public UserReport (String user, String waterType, String conditionType, String location, String longitude, String latitude) {

        this.username = user;
        this.conditionTypee = conditionType;
        this.waterTypee = waterType;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;

    }*/

    /**
     * Gets user that made the report
     * @return User user
     */
    public User getUser () {

        return this.user;

    }
    //Gets the username in String


    /**
     * Gets user that made the report
     * @return String user
     */
    public String getUsername() {

        return this.username;

    }
    //Gets the water condition in String

    /**
     * Gets the condition type
     * @return String condition
     */
    public String getWc () {

        return this.conditionTypee;

    }
    //Gets the water type in String


    /**
     * Gets the water type
     * @return String water type
     */
    public String getWt () {

        return this.waterTypee;

    }

    /**
     * Auto generates a report ID
     * @return String report number
     */
    public String getReportNumber () {
        return "" + reportNumber;

    }

    /**
     * Gets type of water
     * @return WaterType type
     */
    public WaterType getType () {

        return this.waterType;

    }
    /**
     * Gets Condition of the water
     * @return ConditionType condition
     */
    public ConditionType getCondition () {

        return this.conditionType;

    }

    /**
     * Gets location of the water that was reported
     * @return String location
     */
    public String getLocation () {

        return this.location;

    }
    //Gets the Longitude

    /**
     * Gets longitude of the water that was reported
     * @return String longitude
     */
    public String getLongitude () {

        return this.longitude;
    }
    //Gets the latitude

    /**
     * Gets latitude of the water that was reported
     * @return String latitude
     */
    public String getLatitude () {

        return this.latitude;
    }

}
