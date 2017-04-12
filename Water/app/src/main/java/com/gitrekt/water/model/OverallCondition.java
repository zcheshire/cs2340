package com.gitrekt.water.model;

/**
 * Created by zacharycheshire on 3/12/17.
 */

/**
 * Enum of the overall condition of the water
 */
public enum OverallCondition {
    TREATABLE("Treatable"),
    SAFE("Safe"),
    UNSAFE("Unsafe");


    private final String name;

    OverallCondition(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
