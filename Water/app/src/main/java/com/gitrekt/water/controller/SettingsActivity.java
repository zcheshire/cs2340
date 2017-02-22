package com.gitrekt.water.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;
import com.gitrekt.water.model.User;
import com.gitrekt.water.model.UserType;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    private Model model;

    private EditText emailField;
    private EditText passwordField;
    private Spinner selectedType;

    ArrayAdapter<UserType> userTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Get a reference to the model singleton
        model = Model.getInstance();
        //Hardcoded values for the login, will be replaced

        //Get references to the view objects we interface with
        emailField = (EditText) findViewById(R.id.editEmail);
        passwordField = (EditText) findViewById(R.id.editPassword);

        selectedType = (Spinner) findViewById(R.id.userTypeSpinner);
        userTypeAdapter =
                new ArrayAdapter<UserType>(this, android.R.layout.simple_spinner_item, UserType.values());

        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        selectedType.setAdapter(userTypeAdapter);
    }
    public void cancelEdit(View view) {
        //Just return to the parent activity (main activity)
        this.onBackPressed();
    }
    public void performEdit(View view) {
        User _user = model.getCurrentUser();

        emailField.setText(_user.getUserName());
        passwordField.setText(_user.getPassWord());
        selectedType.setSelection(userTypeAdapter.getPosition(_user.getUserType()));

        ArrayList<User> uList = model.getUserList();
        for (User u: uList) {
            if (_user.getUserName().equals(u.getUserName())) {

                //If username is taken, then notify user
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
                emailField.setText("");
                passwordField.setText("");
                return;
            }


        }
        model.removeUser(_user);

        _user = new User(emailField.getText().toString(),
                passwordField.getText().toString(),
                (UserType) selectedType.getSelectedItem());

        model.addUser(_user);
        model.setCurrentUser(_user);
        finish();
    }
}
