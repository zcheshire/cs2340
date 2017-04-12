package com.gitrekt.water.model;

/**
 * Created by zacharycheshire on 2/14/17.
 */

public class User {
    private String userName;
    private String passWord;
    private UserType userType;
<<<<<<< HEAD
    public String userTypee;
=======
>>>>>>> 2aa37df7640bed18e7c9d8a1d44653d4ce49edc5


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
     * Creates a user object for database
     * @param userName
     * @param passWord
     */
    public User(String userName, String passWord, String userType) {
        this.userName = userName;
        this.passWord = passWord;
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
<<<<<<< HEAD
    //Gets the username of te user
=======

    /**
     * Gets the user name
     * @return String name
     */
>>>>>>> 2aa37df7640bed18e7c9d8a1d44653d4ce49edc5
    public String getUserName() {
        return this.userName;
    }

<<<<<<< HEAD
    //Gets the userType of the user
=======
    /**
     * Gets the user's type
     * @return UserType type
     */
>>>>>>> 2aa37df7640bed18e7c9d8a1d44653d4ce49edc5
    public UserType getUserType() {

        return this.userType;

    }
<<<<<<< HEAD
    //Sets the userType for the user
=======

    /**
     * Sets the user's type
     * @param userType
     */
>>>>>>> 2aa37df7640bed18e7c9d8a1d44653d4ce49edc5
    public void setUserType(UserType userType) {

        this.userType = userType;

    }


<<<<<<< HEAD
    //Sets the username for the user
    public void setUserName(String userName) {
        this.userName = userName;
    }
    //Gets the password for the user
    public String getPassWord() {
        return passWord;
    }
    //Sets the password for the user
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    //Validates the user
=======
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
>>>>>>> 2aa37df7640bed18e7c9d8a1d44653d4ce49edc5
    public boolean validate(User user) {
        if (this.getUserName().equals(user.getUserName())) {
            if (this.getPassWord().equals(user.getPassWord())) {
                return true;
            }
        }
        return false;
    }
}
