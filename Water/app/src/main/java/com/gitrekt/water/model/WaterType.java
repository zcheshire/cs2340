package com.gitrekt.water.model;

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

    private final String name;

    WaterType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
