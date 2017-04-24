package com.gitrekt.water.model;

/**
 * Created by zacharycheshire on 2/14/17.
 */

public class User {
    private String userName;
    private String passWord;
    private UserType userType;


    /**
     * Creates a user object
     * delete once database in place
     * @param userName
     * @param passWord
     * @param userType
     */public User(String userName, String passWord, UserType userType) {
        this.userName = userName;
        this.passWord = passWord;
        this.userType = userType;
    }

    /**
     * Creates a user object without a type
     * @param userName
     * @param passWord
     */
    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        this.userType = null;
    }

    /**
     * Creates a new empty user object
     */
    public User() {
        this.userName = null;
        this.passWord = null;
        this.userType = null;
    }
    //Gets the username of te user

    /**
     * Gets the user name
     * @return String name
     */
    public String getUserName() {
        return this.userName;
    }

    //Gets the userType of the user
    /**
     * Gets the user's type
     * @return UserType type
     */
    public UserType getUserType() {

        return this.userType;

    }
    //Sets the userType for the user

    /**
     * Sets the user's type
     * @param userType
     */
    public void setUserType(UserType userType) {

        this.userType = userType;

    }



    /**
     * Set's the user's name
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the user's password
     * @return String password
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * Sets the user's password
     * @param passWord
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * Validates that the user exists
     * @param user
     * @return boolean exists
     */
    public boolean validate(User user) {
        if (this.getUserName().equals(user.getUserName())) {
            if (this.getPassWord().equals(user.getPassWord())) {
                return true;
            }
        }
        return false;
    }
}
