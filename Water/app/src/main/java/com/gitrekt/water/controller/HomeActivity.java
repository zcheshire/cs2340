package com.gitrekt.water.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;

public class HomeActivity extends AppCompatActivity {

    private Model model;

    private TextView loginMessage;
    private Button logoutButton;
    private Button reportButton;
    private ListView lv;
    private WaterReportAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        model = Model.getInstance();

        loginMessage = (TextView) findViewById(R.id.loginMessage);
        logoutButton = (Button) findViewById(R.id.logoutButton);
        reportButton = (Button) findViewById(R.id.reportButton);
        lv = (ListView) findViewById(R.id.reportListView);

        loginMessage.setText("Welcome !");
    }

    protected void onResume() {
        super.onRestart();

        adapter = new WaterReportAdapter(getApplicationContext(), model.getUserReports());
        lv.setAdapter(adapter);

    }

    protected void onPause() {
        super.onPause();

        loginMessage.setText("");
    }

    //Overriding back so user can't return to login screen without logging out
    @Override
    public void onBackPressed() {
    }

    public void logout(View view) {
        model.setCurrentUser(null);
        super.onBackPressed();
    }

    public void settings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    public void createReport(View view) {
        Intent intent = new Intent(this, ReportActivity.class);
        startActivity(intent);
    }
}
