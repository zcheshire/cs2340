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
    private String userr;
    private String waterTypee;
    private String conditionTypee;

    /**
     * Creates a user report object
     * @param user
     * @param waterType
     * @param conditionType
     * @param location
     * @param longitude
     * @param latitude
     */
    public UserReport (User user, WaterType waterType,ConditionType conditionType, String location, String longitude, String latitude) {
        this.user = user;
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
    public UserReport (String user, String waterType, String conditionType, String location, String longitude, String latitude) {

        this.userr = user;
        this.conditionTypee = conditionType;
        this.waterTypee = waterType;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;

    }
    /**
     * Gets user that made the report
     * @return User user
     */
    public User getUser () {

        return this.user;

    }
<<<<<<< HEAD
    //Gets the username in String

=======

    /**
     * Gets user that made the report
     * @return String user
     */
>>>>>>> 2aa37df7640bed18e7c9d8a1d44653d4ce49edc5
    public String getUserr () {

        return this.userr;

    }
<<<<<<< HEAD
    //Gets the water condition in String
=======

    /**
     * Gets the condition type
     * @return String condition
     */
>>>>>>> 2aa37df7640bed18e7c9d8a1d44653d4ce49edc5
    public String getWc () {

        return this.conditionTypee;

    }
<<<<<<< HEAD
    //Gets the water type in String

=======

    /**
     * Gets the water type
     * @return String water type
     */
>>>>>>> 2aa37df7640bed18e7c9d8a1d44653d4ce49edc5
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
<<<<<<< HEAD
    //Gets the Longitude
=======

    /**
     * Gets longitude of the water that was reported
     * @return String longitude
     */
>>>>>>> 2aa37df7640bed18e7c9d8a1d44653d4ce49edc5
    public String getLongitude () {

        return this.longitude;
    }
<<<<<<< HEAD
    //Gets the latitude
=======

    /**
     * Gets latitude of the water that was reported
     * @return String latitude
     */
>>>>>>> 2aa37df7640bed18e7c9d8a1d44653d4ce49edc5
    public String getLatitude () {

        return this.latitude;
    }

}
