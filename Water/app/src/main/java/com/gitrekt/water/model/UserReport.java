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
    static int reportNumber;
    public UserReport (User user, WaterType waterType,ConditionType conditionType, String location) {
        this.user = user;
        this.waterType = waterType;
        this.conditionType = conditionType;
        this.location = location;
        this.reportNumber = reportNumber + 1;


    }
    public User getUser () {

        return this.user;

    }
    public String getReportNumber () {
        return "" + reportNumber;

    }
    public WaterType getType () {

        return this.waterType;

    }
    public ConditionType getCondition () {

        return this.conditionType;

    }
    public String getLocation () {

        return this.location;

    }

}
