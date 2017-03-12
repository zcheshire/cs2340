package com.gitrekt.water.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;
import com.gitrekt.water.model.UserReport;
import com.gitrekt.water.model.UserType;
import com.gitrekt.water.model.WaterType;
import com.gitrekt.water.model.ConditionType;

import java.sql.BatchUpdateException;

public class ReportActivity extends AppCompatActivity {
    private Model model;

    private Spinner typeSpinner;
    private Spinner conditionSpinner;
    private EditText locationField;
    private EditText longitudeField;
    private EditText latitudeField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        model = Model.getInstance();

        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        conditionSpinner = (Spinner) findViewById(R.id.conditionSpinner);
        locationField = (EditText) findViewById(R.id.locationField);
        longitudeField = (EditText) findViewById(R.id.longitudeField);
        latitudeField = (EditText) findViewById(R.id.latitudeField);

        ArrayAdapter<WaterType> waterTypeAdapter =
                new ArrayAdapter<WaterType>(this, android.R.layout.simple_spinner_item, WaterType.values());

        waterTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typeSpinner.setAdapter(waterTypeAdapter);

        ArrayAdapter<ConditionType> waterConditionAdapter =
                new ArrayAdapter<ConditionType>(this, android.R.layout.simple_spinner_item, ConditionType.values());

        waterConditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        conditionSpinner.setAdapter(waterConditionAdapter);
    }
//send the report data to the model
    void submitReport(View v) {
        UserReport _report = new UserReport(model.getCurrentUser(),
                (WaterType) typeSpinner.getSelectedItem(),
                (ConditionType) conditionSpinner.getSelectedItem(),
                locationField.getText().toString(), longitudeField.getText().toString(), latitudeField.getText().toString());
        model.addUserReport(_report);
        //Intent intent = new Intent(this, ViewReportActivity.class);
        //startActivity(intent);
        finish();
    }

    void cancelReport(View v) {
        finish();
    }
}
