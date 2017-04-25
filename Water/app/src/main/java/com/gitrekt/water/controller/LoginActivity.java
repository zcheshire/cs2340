package com.gitrekt.water.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;
import com.gitrekt.water.model.User;
import com.gitrekt.water.model.UserReaderContract;
import com.gitrekt.water.model.UserReaderDbHelper;

import java.util.ArrayList;

public class LoginActivity <T> extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;
    Model model = Model.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Get references to the view objects we interface with
        emailField = (EditText) findViewById(R.id.loginEmail);
        passwordField = (EditText) findViewById(R.id.loginPassword);
    }

    /**
     * Button that allows a user to cancel their login attempt
     * @param view
     */
    public void cancelLogin(View view) {
        //Just return to the parent activity (main activity)
        this.onBackPressed();
    }

    /**
     * Button that checks the above fields for proper username and password
     * and allows a user to login if the information is correct
     * @param view
     */
    public void performLogin(View view) {
        //Create a new user from the username and password fields
        User _user = new User(emailField.getText().toString(), passwordField.getText().toString());

        ArrayList<User> userList = model.getUsersFromDB(this);

        if (model.validateUser(userList, _user)) {
            //model.setCurrentUser(_user);
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            //If they return from HomeActivity (Logout),
            //this will return to the parent activity (main activity)
            finish();
        } else {
            //If username/pass do not match, create a dialog to let them know
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