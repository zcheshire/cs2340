package com.gitrekt.water.model;

/**
 * Created by John on 2/21/2017.
 * Types of different users on the system
 */

/**
 * Enum of user types
 */
public enum UserType {
    USER("User"),
    WORKER("Worker"),
    MANAGER("Manager"),
    ADMIN("Admin");

    private String name;

    UserType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
