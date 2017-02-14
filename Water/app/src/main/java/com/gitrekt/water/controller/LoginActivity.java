package com.gitrekt.water.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;
import com.gitrekt.water.model.User;

public class LoginActivity extends AppCompatActivity {

    private Model model;

    private EditText emailField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        model = Model.getInstance();
        //Hardcoded values for the login, will be replaced
        model.setCurrentUser(new User("xxx", "yyy"));

        emailField = (EditText) findViewById(R.id.loginEmail);
        passwordField = (EditText) findViewById(R.id.loginPassword);
    }

    public void cancelLogin(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void performLogin(View view) {
        User _user = new User(emailField.getText().toString(), passwordField.getText().toString());
        if (_user.getUserName().equals(model.getCurrentUser().getUserName())
                && _user.getPassWord().equals(model.getCurrentUser().getPassWord())) {
            model.setCurrentUser(_user);
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else {
            Context context = view.getContext();
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("Username and password do not match");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }
}
