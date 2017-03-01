package com.gitrekt.water.model;

/**
 * Created by zacharycheshire on 3/1/17.
 */

public class UserReport {
    User user;
    WaterType waterType;
    ConditionType conditionType;
    String location;
    int reportNumber;

    public UserReport (User user, WaterType waterType,ConditionType conditionType, String location) {

        this.user = user;
        this.waterType = waterType;
        this.conditionType = conditionType;
        this.location = location;


    }
}
