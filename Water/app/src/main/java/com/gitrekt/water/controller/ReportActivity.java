package com.gitrekt.water.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;
import com.gitrekt.water.model.UserType;

import java.sql.BatchUpdateException;

public class ReportActivity extends AppCompatActivity {
    private Model model;

    private Spinner typeSpinner;
    private Spinner conditionSpinner;
    private EditText locationField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        model = Model.getInstance();

        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        conditionSpinner = (Spinner) findViewById(R.id.conditionSpinner);
        locationField = (EditText) findViewById(R.id.locationField);

        ArrayAdapter<UserType> waterTypeAdapter =
                new ArrayAdapter<UserType>(this, android.R.layout.simple_spinner_item, WaterType.values());

        waterTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typeSpinner.setAdapter(waterTypeAdapter);

        ArrayAdapter<UserType> waterConditionAdapter =
                new ArrayAdapter<UserType>(this, android.R.layout.simple_spinner_item, WaterCondition.values());

        waterConditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        conditionSpinner.setAdapter(waterConditionAdapter);
    }

    submitReport(View v) {
        UserReport _report = new UserReport(locationField.getText(), typeSpinner.getSelectedItem(), conditionSpinner.getSelectedItem());
        model.addUserReport(_report);
    }

    cancelReport(View v) {
        finish();
    }
}
