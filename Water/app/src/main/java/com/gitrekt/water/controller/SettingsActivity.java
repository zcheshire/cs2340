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
import com.gitrekt.water.model.UserType;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    private Model model;

    private EditText emailField;
    private EditText userTypeField;
    private UserType userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Get a reference to the model singleton
        model = Model.getInstance();
        //Hardcoded values for the login, will be replaced

        //Get references to the view objects we interface with
        emailField = (EditText) findViewById(R.id.loginEmail);
        userTypeField = (EditText) findViewById(R.id.loginPassword);
    }
    public void cancelEdit(View view) {
        //Just return to the parent activity (main activity)
        this.onBackPressed();
    }
    public void performEdit(View view) {
        User _user = new User();
        //Create a new user from the username and password fields
        if (model.getCurrentUser().getUserName().equals(emailField)
                && model.getCurrentUser().getUserType().equals(userTypeField) ) {

            return;
        } else if (model.getCurrentUser().getUserName().equals(emailField)
                && !model.getCurrentUser().getUserType().equals(userTypeField)) {

            _user = new User(model.getCurrentUser().getUserName(),model.getCurrentUser().getPassWord(), userType);
            model.setCurrentUser(_user);

        } else {
            boolean doesNotExist;
            ArrayList<User> uList = model.getUserList();
            for (User u : uList) {
                if (emailField.getText().toString().equals(u.getUserName())) {
                    Context context = view.getContext();
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("Username is taken");
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
                    return;

                    //Move on to the Home Screen once logged in
                    //Intent intent = new Intent(this, HomeActivity.class);
                    //startActivity(intent);

                    //If they return from HomeActivity (Logout),
                    //this will return to the parent activity (main activity)
                }
            }

            _user = new User(emailField.getText().toString(),model.getCurrentUser().getPassWord(), model.getCurrentUser().getUserType());
            model.setCurrentUser(_user);

        }

    }
}
