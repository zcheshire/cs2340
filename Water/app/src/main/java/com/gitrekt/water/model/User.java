package com.gitrekt.water.model;

/**
 * Created by zacharycheshire on 2/14/17.
 */

public class User {
    private String userName;
    private String passWord;

    public User() {

        userName = "";
        passWord = "";

    }

    public User(String userName, String passWord) {

        this.userName = userName;
        this.passWord = passWord;

    }

    public void setUserName(String username){

        this.userName = username;

    }

    public String getUserName() {

        return userName;

    }
    public void setPassWord(String passWord){

        this.passWord = passWord;

    }

    public String getPassWord() {

        return passWord;

    }
}
