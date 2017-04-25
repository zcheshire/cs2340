package com.gitrekt.water.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;
import com.gitrekt.water.model.User;
import com.gitrekt.water.model.UserType;

import java.util.ArrayList;
/*

Controller for the setting activity




 */
public class SettingsActivity <T> extends AppCompatActivity {

    private Model model;
    private TextView textView5;
    private EditText emailField;
    private EditText passwordField;
   // private Spinner selectedType;

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
        emailField.setText(model.getCurrentUser().getUserName());
        passwordField = (EditText) findViewById(R.id.editPassword);
        passwordField.setText(model.getCurrentUser().getPassWord());
        textView5 = (TextView) findViewById(R.id.textView5);
        TextView userTypeLabel = (TextView) findViewById(R.id.userTypeLabel);
        userTypeLabel.setText("User Type: " + model.getCurrentUser().getUserType());

        //selectedType = (Spinner) findViewById(R.id.userTypeSpinner);
        //userTypeAdapter =
           //     new ArrayAdapter<UserType>(this, android.R.layout.simple_spinner_item, UserType.values());

        //userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       // selectedType.setAdapter(userTypeAdapter);
    }
    /**
     * Returns to previous view
     * @param view view the user is on
     */
    public void cancelEdit(View view) {
        //Just return to the parent activity (main activity)
        this.onBackPressed();
        overridePendingTransition(R.animator.hold, R.animator.x_slide_out);
    }

    /**
     * Edits the users account
     * @param view view the user is on
     */
    public void performEdit(View view) {
        User _user = model.getCurrentUser();

        ArrayList<User> uList = model.getUsersFromDB(this);

        String pass = passwordField.getText().toString();
        String check = emailField.getText().toString();
        if (!changeName(check,_user, uList)) {

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

        } else {


            //model.removeUser(_user);

            _user = new User(check, pass, model.getCurrentUser().getUserType());
            model.setCurrentUser(_user);
            emailField.setText(_user.getUserName());
            passwordField.setText(_user.getPassWord());
            model.addUserToDB(this, _user);
            textView5.setText("Username Changed to " + _user.getUserName() + " and profile has been updated.");
            //System.out.print("Username Changed!");
        }

    }

    /**
     * Checks to see if the username a user wishes to change to
     * already exists
     * @param check
     * @param _user
     * @param uList
     * @return boolean
     */
    public boolean changeName (String check, User _user, ArrayList<User> uList) {
        for (User u : uList) {
            if (u != _user) {
                if (check.equals(u.getUserName())) {

                  return false;
                }
            }


        }
        return true;
    }
}
