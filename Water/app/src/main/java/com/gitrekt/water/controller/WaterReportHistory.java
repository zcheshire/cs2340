package com.gitrekt.water.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;
import com.gitrekt.water.model.UserReaderDbHelper;
import com.gitrekt.water.model.UserReport;

public class WaterReportHistory extends AppCompatActivity {
    private EditText searchField;
    private TextView nameShow;
    private TextView locationShow;
    private TextView wtShow;
    private TextView wcShow;
    private Model model;
    private Button submitButton;
    UserReaderDbHelper mDbHelper = new UserReaderDbHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_report_history);
        searchField = (EditText) findViewById(R.id.searchText);
        nameShow = (TextView) findViewById(R.id.nameShow);
        locationShow = (TextView) findViewById(R.id.locationShow);
        wtShow = (TextView) findViewById(R.id.wtShow);
        wcShow = (TextView) findViewById(R.id.wcShow);

        model = Model.getInstance();


    }

    /*
    Sumbits the managers request and finds the report in the database
    @param view the current view
     */
    public void submit(View view) {
        model.setSearchType(searchField.getText().toString());
        for (UserReport rep : model.getUserReportsFromDB(this)) {
            if (rep.getLocation().equals(searchField.getText().toString())) {
                nameShow.setText(rep.getUsername());
                locationShow.setText(rep.getLocation());
                wtShow.setText(rep.getWt());
                wcShow.setText(rep.getWc());
            }
        }
    }
}
