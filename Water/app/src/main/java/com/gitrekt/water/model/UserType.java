package com.gitrekt.water.model;

/**
 * Created by John on 2/21/2017.
 */

public enum UserType {
    USER("User"),
    WORKER("Worker"),
    MANAGER("Manager"),
    ADMIN("Admin")

    private String name;

    UserType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
