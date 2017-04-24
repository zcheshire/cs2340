package com.gitrekt.water.controller;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import java.util.List;

import com.gitrekt.water.model.UserReaderContract;
import com.gitrekt.water.model.UserReaderDbHelper;
/*
Controller for the register activity



 */
public class RegisterActivity <T> extends AppCompatActivity {
    private Model model;

    private Spinner selectedType;
    private EditText emailRegisterField;
    private EditText passwordRegisterField;
    private final UserReaderDbHelper mDbHelper = new UserReaderDbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Get a reference to the model singleton
        model = Model.getInstance();



        //Get references to the view objects we interface with
        emailRegisterField = (EditText) findViewById(R.id.registerEmail);
        passwordRegisterField = (EditText) findViewById(R.id.registerPassword);
        selectedType = (Spinner) findViewById(R.id.userTypeSpinner);

        ArrayAdapter<UserType> userTypeAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, UserType.values());

        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        selectedType.setAdapter(userTypeAdapter);
    }

    /**
     * Takes the user to the previous scree
     * @param view current view
     */
    public void cancelRegister(View view) {
        //Just return to the parent activity (main activity)
        this.onBackPressed();
    }
/*
* registers a new user if the username is not taken
* @param view current view
*/

    /**
     * Registers a new user if the username is not taken
     * @param view current view
     */
    public void performRegister(View view) {
        //Create a new user from the username and password fields
        User _user = new User(emailRegisterField.getText().toString(),
                passwordRegisterField.getText().toString(),
                (UserType) selectedType.getSelectedItem());

        System.out.println("GGGG");
        ArrayList<User> userList = model.getUsersFromDB(this);
        System.out.println("GGGG");

        if (!model.validateUser(userList, _user)) {

            System.out.println("AAAA");
            model.addUserToDB(this, _user);
            System.out.println("AAAA");

            model.setCurrentUser(_user);

            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else {
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
            emailRegisterField.setText("");
            passwordRegisterField.setText("");
            return;
        }

        //If they return from HomeActivity (Logout),
        //this will return to the parent activity (main activity)
        finish();


    }
}

