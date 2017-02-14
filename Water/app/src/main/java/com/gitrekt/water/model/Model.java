package com.gitrekt.water.model;

import java.util.List;

/**
 * Created by zacharycheshire on 2/14/17.
 */

public class Model {
    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    private User currentUser;
    
    public User getCurrentUser() {

        return currentUser;

    }

    public void setCurrentUser(User user) {

        this.currentUser = user;

    }
}
