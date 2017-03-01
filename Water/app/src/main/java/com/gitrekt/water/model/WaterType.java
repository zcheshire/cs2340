package com.gitrekt.water.model;

/**
 * Created by zacharycheshire on 3/1/17.
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
