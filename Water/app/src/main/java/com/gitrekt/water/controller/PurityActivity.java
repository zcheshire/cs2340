package com.gitrekt.water.controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.gitrekt.water.model.Model;
import com.gitrekt.water.model.QualityReport;
//import com.gitrekt.water.model.UserReport;
import com.gitrekt.water.model.UserReaderContract;
import com.gitrekt.water.model.UserReaderDbHelper;
//import com.gitrekt.water.model.WaterType;
import com.gitrekt.water.model.OverallCondition;

import com.gitrekt.water.R;
//Controller for Activity_Purity.XML file
//This is where users create purity report
public class PurityActivity extends AppCompatActivity {
    private Model model;
    private Spinner conditionSpinner;
    private EditText locationField;
    private EditText longitudeField;
    private EditText latitudeField;
    private EditText virusField;
    private EditText contaminantField;
    private final UserReaderDbHelper mDbHelper = new UserReaderDbHelper(this);

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
        Button submitReport = (Button) findViewById(R.id.submitReport);

        ArrayAdapter<OverallCondition> waterConditionAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, OverallCondition.values());

        waterConditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        conditionSpinner.setAdapter(waterConditionAdapter);

    }
/*
Submits user report to the db
@param v the current view
 */

    /**
     * Button that a user presses to submit a report once prompted to fill
     * in information needed for a user report
     * @param v
     */
    public void submitReport(View v) {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Calendar calobj = Calendar.getInstance();
        System.out.println(df.format(calobj.getTime()));

        QualityReport _report = new QualityReport(model.getCurrentUser(),
                (OverallCondition) conditionSpinner.getSelectedItem(),
                locationField.getText().toString(), longitudeField.getText().toString(), latitudeField.getText().toString(),
        calobj, virusField.getText().toString(), contaminantField.getText().toString());
        model.addQualityReport(_report);
        // Gets the data repository in write mode
        SQLiteDatabase data = mDbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_USERNAME, model.getCurrentUser().getUserName());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_PASSWORD, model.getCurrentUser().getPassWord());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_VPPM, virusField.getText().toString());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_CPPM, contaminantField.getText().toString());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_WC, conditionSpinner.getSelectedItem().toString());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_LON, longitudeField.getText().toString());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_LAT, latitudeField.getText().toString());
        values.put(UserReaderContract.FeedEntry.COLUMN_NAME_LOC, locationField.getText().toString());



// Insert the new row, returning the primary key value of the new row
        long newRowId = data.insert(UserReaderContract.FeedEntry.TABLE_NAME, null, values);
        //model.addQualityReport(_report);
        //Intent intent = new Intent(this, ViewReportActivity.class);
        //startActivity(intent);
        finish();
    }

    /**
     * Button that cancels the submission process
     * @param v
     */
    void cancel(View v) {
        finish();
    }
}
