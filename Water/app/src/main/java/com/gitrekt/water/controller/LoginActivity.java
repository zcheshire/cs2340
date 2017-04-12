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
import com.gitrekt.water.model.UserType;
import com.gitrekt.water.model.WaterType;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity <T> extends AppCompatActivity {

    private Model model;

    private EditText emailField;
    private EditText passwordField;
    UserReaderDbHelper mDbHelper = new UserReaderDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Get a reference to the model singleton
        model = Model.getInstance();

        //Get references to the view objects we interface with
        emailField = (EditText) findViewById(R.id.loginEmail);
        passwordField = (EditText) findViewById(R.id.loginPassword);
    }

    public void cancelLogin(View view) {
        //Just return to the parent activity (main activity)
        this.onBackPressed();
    }
/*
Called upon click and logs the user in
@param view the current view

 */
    public void performLogin(View view) {
        //Create a new user from the username and password fields
        User _user = new User(emailField.getText().toString(), passwordField.getText().toString());

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                UserReaderContract.FeedEntry._ID,
                UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME,
                UserReaderContract.FeedEntry.COLUMN_NAME_PASSWORD,
                UserReaderContract.FeedEntry.COLUMN_NAME_ADMIN

        };

// Filter results WHERE "username" is anything
        String selection = UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME + " = ?";
// How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME + " DESC";
//Query the db using a cursor
        Cursor cursor = db.query(
                UserReaderContract.FeedEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,//"",//selection,                    // The columns for the WHERE clause
                null,//selectionArgs,                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        ArrayList<User> itemIds = new ArrayList<>();
        //Grabs usernames from the db and add them to the itemIDS arrayList
        while (cursor.moveToNext()) {
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME));
            String itemPass = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_PASSWORD));
            String itemAdmin = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_ADMIN));
            User user = new User(itemId, itemPass, itemAdmin);
            itemIds.add(user);
        }
        cursor.close();
        if (!validateLogin(itemIds, _user)) {


            //Loops to see if the username is already in the db


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
        } else {

            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            model.setCurrentUser(_user);
            //If they return from HomeActivity (Logout),
            //this will return to the parent activity (main activity
            finish();

        }

    }
    /*
Validates user login through db
@param rep ArrayList of usernames
@param newUser user to be checked
@return boolean wether the user exists

 */
    public boolean validateLogin (ArrayList<User> itemIds, User newUser) {

        for (User u : itemIds) {
            if (newUser.getUserName().equals(u.getUserName())) {
                System.out.print(u.toString());
                if (newUser.getPassWord().equals(u.getPassWord())) {




                    //Move on to the Home Screen once logged in
                  return true;

                }
            }
        }
        return false;

    }

}