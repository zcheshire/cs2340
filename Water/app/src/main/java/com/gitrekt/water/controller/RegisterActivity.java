package com.gitrekt.water.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;
import com.gitrekt.water.model.User;
import com.gitrekt.water.model.UserType;

public class RegisterActivity extends AppCompatActivity {
    private Model model;
    private UserType userType;
    private Spinner selected;
    private EditText emailRegisterField;
    private EditText passwordRegisterField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Get a reference to the model singleton
        model = Model.getInstance();
        //Hardcoded values for the login, will be replaced
        model.setCurrentUser(new User("xxx", "yyy"));

        //Get references to the view objects we interface with
        emailRegisterField = (EditText) findViewById(R.id.registerEmail);
        passwordRegisterField = (EditText) findViewById(R.id.registerPassword);
    }


    public void cancelRegister(View view) {
        //Just return to the parent activity (main activity)
        this.onBackPressed();
    }

    public void performRegister(View view) {
        //Create a new user from the username and password fields
        User _user = new User(emailRegisterField.getText().toString(), passwordRegisterField.getText().toString(), userType);

        //For now, it just compares to the values hardcoded in onCreate
        if (_user.validate(model.getCurrentUser())) {
            model.setCurrentUser(_user);

            //Move on to the Home Screen once logged in
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);

            //If they return from HomeActivity (Logout),
            //this will return to the parent activity (main activity)
            finish();
        } else {
            //If username/pass do not match, create a dialog to let them know
            Context context = view.getContext();
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("Username is already taken");
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
