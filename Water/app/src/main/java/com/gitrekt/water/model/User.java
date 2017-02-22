package com.gitrekt.water.model;

/**
 * Created by zacharycheshire on 2/14/17.
 */

public class User {
    private String userName;
    private String passWord;
    private UserType userType;

    public User(String userName, String passWord, UserType userType) {
        this.userName = userName;
        this.passWord = passWord;
        this.userType = userType;
    }

    public User(String userName, String passWord) {
        this(userName, passWord, null);
    }

    public User() {
        this(null, null, null);
    }

    public String getUserName() {
        return userName;
    }

    public String setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean validate(User user) {
        if (this.getUserName().equals(user.getUserName())) {
            if (this.getPassWord().equals(user.getPassWord())) {
                return true;
            }
        }
        return false;
    }
}
