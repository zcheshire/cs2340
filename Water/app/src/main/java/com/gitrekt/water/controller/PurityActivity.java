package com.gitrekt.water.controller;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.gitrekt.water.R;
import com.gitrekt.water.model.ConditionType;
import com.gitrekt.water.model.Model;
import com.gitrekt.water.model.QualityReport;
//import com.gitrekt.water.model.UserReport;
import com.gitrekt.water.model.UserType;
//import com.gitrekt.water.model.WaterType;
import com.gitrekt.water.model.OverallCondition;

import java.sql.BatchUpdateException;

import com.gitrekt.water.R;

public class PurityActivity extends AppCompatActivity {
    private Model model;
    private Spinner conditionSpinner;
    private EditText locationField;
    private EditText longitudeField;
    private EditText latitudeField;
    private EditText virusField;
    private EditText contaminantField;
    private Button submitReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purity);
        model = Model.getInstance();
        conditionSpinner = (Spinner) findViewById(R.id.conditionSpinner);
        locationField = (EditText) findViewById(R.id.locationField);
        longitudeField = (EditText) findViewById(R.id.longitudeField);
        latitudeField = (EditText) findViewById(R.id.latitudeField);
        virusField = (EditText) findViewById(R.id.virusField);
        contaminantField = (EditText) findViewById(R.id.contaminantField);
        submitReport = (Button) findViewById(R.id.submitReport);

        ArrayAdapter<OverallCondition> waterConditionAdapter =
                new ArrayAdapter<OverallCondition>(this, android.R.layout.simple_spinner_item, OverallCondition.values());

        waterConditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        conditionSpinner.setAdapter(waterConditionAdapter);

    }

    public void submitReport(View v) {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Calendar calobj = Calendar.getInstance();
        System.out.println(df.format(calobj.getTime()));

        QualityReport _report = new QualityReport(model.getCurrentUser(),
                (OverallCondition) conditionSpinner.getSelectedItem(),
                locationField.getText().toString(), longitudeField.getText().toString(), latitudeField.getText().toString(),
        calobj, virusField.getText().toString(), contaminantField.getText().toString());
        model.addQualityReport(_report);
        System.out.print("Hello");
        System.out.print(model.getQualityReports().get(0).getLongitude().toString());
        //Intent intent = new Intent(this, ViewReportActivity.class);
        //startActivity(intent);
        finish();
    }
    void cancelReport(View v) {
        finish();
    }
}
