package com.gitrekt.water.model;

/**
 * Created by zacharycheshire on 2/14/17.
 */

public class User {
    private String userName;
    private String passWord;
    private UserType userType;
    private String userTypee;

    public User(String userName, String passWord, UserType userType) {
        this.userName = userName;
        this.passWord = passWord;
        this.userType = userType;
    }

    public User(String userName, String passWord, String userType) {
        this.userName = userName;
        this.passWord = passWord;
        this.userTypee = userType;
    }
    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        this.userType = null;
    }

    public User() {
        this.userName = null;
        this.passWord = null;
        this.userType = null;
    }

    public String getUserName() {
        return this.userName;
    }


    public UserType getUserType() {

        return this.userType;

    }

    public void setUserType(UserType userType) {

        this.userType = userType;

    }



    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
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
