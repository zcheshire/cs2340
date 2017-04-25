package com.gitrekt.water.controller;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;
import com.gitrekt.water.model.UserReaderContract;
import com.gitrekt.water.model.UserReaderDbHelper;
import com.gitrekt.water.model.UserReport;
import com.gitrekt.water.model.UserType;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Objects;

public class HomeActivity <T> extends AppCompatActivity {

    private Model model;

    private TextView loginMessage;
    private Button logoutButton;
    private Button history;

    private Button reportButton;
    private Button addQuality;
    private Button viewQualityReport;
    private final UserReaderDbHelper mDbHelper = new UserReaderDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        model = Model.getInstance();
        loginMessage = (TextView) findViewById(R.id.loginMessage);

        logoutButton = (Button) findViewById(R.id.logoutButton);
        history = (Button) findViewById(R.id.history);
        reportButton = (Button) findViewById(R.id.reportButton);
        addQuality = (Button) findViewById(R.id.addQuality);
        viewQualityReport = (Button) findViewById(R.id.viewQualityReport);
        Button logoutButton = (Button) findViewById(R.id.logoutButton);
        Button reportButton = (Button) findViewById(R.id.reportButton);
        Button addQuality = (Button) findViewById(R.id.addQuality);
        Button viewQualityReport = (Button) findViewById(R.id.viewQualityReport);

        loginMessage.setText("Welcome !");

        //Intent intent = new Intent(this, MenuActivity.class);
        //android.os.SystemClock.sleep(1000);
        //startActivity(intent);
        //overridePendingTransition(R.animator.slide_in, R.animator.slide_out);
    }

    protected void onPause() {
        loginMessage.setText("");
        super.onPause();
    }

    /**
     * Overriding back so user can't return to login screen without logging out
     */
    @Override
    public void onBackPressed() {
    }

    /**
     * Button that allows a user to logout
     */
    public void logout() {
        model.setCurrentUser(null);
        super.onBackPressed();
    }

    /*
    Checks if user is manager before sending them to history page
    @param view the current view
    */
    public void history(View view) {
        if (model.getCurrentUser().getUserType().equals(UserType.MANAGER)
                || model.getCurrentUser().getUserType().equals(UserType.ADMIN)) {
            Intent intent = new Intent(this, WaterReportHistory.class);
            startActivity(intent);
        } else {
            loginMessage.setText("Only Managers and Admins can view history");
        }

    }

    /**
     * Button that allows a user to see their settings page
     * @param view
     */
    public void settings(View view) {
        //Change back to SettingsActivity from MapsActivity
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.animator.x_slide_in, R.animator.hold);
    }

    /**
     * Button that allows a user to see their menu page
     * @param view
     */
    public void goMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        overridePendingTransition(R.animator.slide_in, R.animator.hold);
    }

    /**
     * Button that allows a user to add a user report
     * @param view
     */
    public void createReport(View view) {
        Intent intent = new Intent(this, ReportActivity.class);
        startActivity(intent);
    }

    /**
     * Button that allows a user to view the map
     * @param view
     */
    public void viewMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    /**
     * Button that allows the user to create quality reports
     * @param view
     */
    //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void viewQualityReport(View view) {
        if (model.getCurrentUser().getUserType().equals(UserType.WORKER)
                || model.getCurrentUser().getUserType().equals(UserType.MANAGER)
                || model.getCurrentUser().getUserType().equals(UserType.ADMIN)) {
            Intent intent = new Intent(this, PurityActivity.class);
            startActivity(intent);
        } else {
            loginMessage.setText("Only Managers, Workers, and Admins can create Purity Reports");
        }


    }

    /**
     * Button that allows the user to view quality reports
     * @param view
     */
    public void viewQualityReports(View view) {
        if (model.getCurrentUser().getUserType().equals(UserType.MANAGER)
                || model.getCurrentUser().getUserType().equals(UserType.ADMIN)) {
            Intent intent = new Intent(this, ViewQualityReportActivity.class);
            startActivity(intent);
        } else {
            loginMessage.setText("Only Managers can view History Reports");
        }
    }

    public void logout(View view) {
        model.setCurrentUser(null);
        super.onBackPressed();
    }
}
