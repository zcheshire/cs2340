package com.gitrekt.water;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailField = (EditText) findViewById(R.id.loginEmail);
        passwordField = (EditText) findViewById(R.id.loginPassword);
    }

    public void cancelLogin(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void performLogin(View view) {
        //Get a reference to the model
        Model model = Model.getInstance();

        User _user = new User(emailField.getText().toString(), passwordField.getText().toString());
        if (_user.getUsername().equals(model.currentUser.getUsername())
                && _user.getPassword().equals(model.currentUser.getPassword())) {
            model.setCurrentUser(_user);
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else {
            Context context = view.getContext();
            new AlertDialog.Builder(context)
                    .setTitle("Login Error")
                    .setMessage("Username and password do not match")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // User clicked OK button
                        }
                    })
                    //.setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
}
