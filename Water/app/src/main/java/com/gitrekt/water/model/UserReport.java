package com.gitrekt.water.model;

import java.net.Inet4Address;

/**
 * Created by zacharycheshire on 3/1/17.
 */

public class UserReport {
    User user;
    WaterType waterType;
    ConditionType conditionType;
    String location;
    String longitude;
    String latitude;
    static int reportNumber;
    public UserReport (User user, WaterType waterType,ConditionType conditionType, String location, String longitude, String latitude) {
        this.user = user;
        this.waterType = waterType;
        this.conditionType = conditionType;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
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
    //Gets type of water
    public WaterType getType () {

        return this.waterType;

    }
    //Gets Condition of the water
    public ConditionType getCondition () {

        return this.conditionType;

    }
    //Gets location of the water that was reported
    public String getLocation () {

        return this.location;

    }
    public String getLongitude () {

        return this.longitude;
    }
    public String getLatitude () {

        return this.latitude;
    }

}
