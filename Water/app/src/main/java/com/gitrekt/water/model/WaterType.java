package com.gitrekt.water.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zacharycheshire on 3/1/17.
 */

/**
 * Enum of water types
 */
public enum WaterType {
    BOTTLED("Bottled"),
    WELL("Well"),
    STREAM("Stream"),
    LAKE("Lake"),
    SPRING("Spring"),
    OTHER("Other");

    private String name;

    WaterType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
