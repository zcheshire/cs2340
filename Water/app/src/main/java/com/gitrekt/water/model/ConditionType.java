package com.gitrekt.water.model;

/**
 * Created by zacharycheshire on 3/1/17.
 */


/**
 * Enum of condition types
 */
public enum ConditionType {
    WASTE("Waste"),
    TREATABLECLEAR("Treatable-Clear"),
    TREATABLEMUDDY("Treatable-Muddy"),
    POTABLE("Potable");
    private String name;

    ConditionType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
