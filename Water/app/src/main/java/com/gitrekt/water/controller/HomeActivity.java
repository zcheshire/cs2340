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

        loginMessage.setText("Welcome, " + model.getCurrentUser().getUserName() + "!");
    }

    public void logout(View view) {
        model.setCurrentUser(null);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
