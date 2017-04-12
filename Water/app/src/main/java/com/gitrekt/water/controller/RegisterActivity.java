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
    UserReaderDbHelper mDbHelper = new UserReaderDbHelper(this);
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
                new ArrayAdapter<UserType>(this, android.R.layout.simple_spinner_item, UserType.values());

        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        selectedType.setAdapter(userTypeAdapter);
    }

/*
Takes the user to the previous scree
@param view current view

 */
    public void cancelRegister(View view) {
        //Just return to the parent activity (main activity)
        this.onBackPressed();
    }
/*
* registers a new user if the username is not taken
* @param view current view
*/
    public void performRegister(View view) {
        //Create a new user from the username and password fields
        User _user = new User(emailRegisterField.getText().toString(),
                passwordRegisterField.getText().toString(),
                (UserType) selectedType.getSelectedItem());


        SQLiteDatabase db = mDbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                UserReaderContract.FeedEntry._ID,
                UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME,
                UserReaderContract.FeedEntry.COLUMN_NAME_PASSWORD
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
        List<String> itemIds = new ArrayList<>();
        //Grabs usernames from the db and add them to the itemIDS arrayList
        while (cursor.moveToNext()) {
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME));

            itemIds.add(itemId);
        }
        cursor.close();
        if (checkUser(itemIds, emailRegisterField.getText().toString()) == true) {


            //Loops to see if the username is already in the db


            //If username does not already exist, then the user is added to User List

            // Gets the data repository in write mode
            SQLiteDatabase data = mDbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME, emailRegisterField.getText().toString());

            values.put(UserReaderContract.FeedEntry.COLUMN_NAME_PASSWORD, passwordRegisterField.getText().toString());
            values.put(UserReaderContract.FeedEntry.COLUMN_NAME_WT, "");
            values.put(UserReaderContract.FeedEntry.COLUMN_NAME_WC, "");
            values.put(UserReaderContract.FeedEntry.COLUMN_NAME_VPPM, "-1");
            values.put(UserReaderContract.FeedEntry.COLUMN_NAME_ADMIN, selectedType.getSelectedItem().toString());
            values.put(UserReaderContract.FeedEntry.COLUMN_NAME_LON, "-1");
            values.put(UserReaderContract.FeedEntry.COLUMN_NAME_LAT, "");
            values.put(UserReaderContract.FeedEntry.COLUMN_NAME_LOC, "");

// Insert the new row, returning the primary key value of the new row
            long newRowId = data.insert(UserReaderContract.FeedEntry.TABLE_NAME, null, values);
            //Move on to the Home Screen once logged in
            model.addUser(_user);
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
    /*
    Checks if the username is already taken
    @param itemIds list of usernames from db
    @param _user the name to be checked
    @return boolean whether the username already exists or not
     */
    public boolean checkUser(List<String> itemIds, String _user) {
        boolean bool = true;
        for (String u : itemIds) {
            if (_user.equals(u)) {
                return false;
                //If username is taken, then notify user


            }
        }
        return true;
    }
}

