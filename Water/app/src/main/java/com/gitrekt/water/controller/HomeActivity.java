package com.gitrekt.water.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;

public class HomeActivity extends AppCompatActivity {

    private Model model;

    private TextView loginMessage;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        model = Model.getInstance();

        loginMessage = (TextView) findViewById(R.id.loginMessage);
        logoutButton = (Button) findViewById(R.id.logoutButton);

        loginMessage.setText("Welcome !");
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
}
