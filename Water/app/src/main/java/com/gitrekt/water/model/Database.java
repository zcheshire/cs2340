package com.gitrekt.water.model;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by John on 4/23/2017.
 */

public interface Database {
    void setContext(Context context);
    void reset();

    void insertUser(User user);
    void insertQR(QualityReport qr, User currentUser);
    void insertUR(UserReport ur, User currentUser);

    ArrayList<User> getUsers();
    ArrayList<QualityReport> getQRs();
    ArrayList<UserReport> getURs();
}
