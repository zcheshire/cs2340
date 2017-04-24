package com.gitrekt.water.controller;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;
import com.gitrekt.water.model.QualityReport;
import com.gitrekt.water.model.UserReaderContract;
import com.gitrekt.water.model.UserReaderDbHelper;

import java.util.ArrayList;

public class ViewQualityReportActivity extends AppCompatActivity {
    private ListView lv;
    private QualityReportAdapter adapter;
    private Model model;
    private final UserReaderDbHelper mDbHelper = new UserReaderDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quality_report);
        model = Model.getInstance();
        lv = (ListView) findViewById(R.id.qualityReportView);
        adapter = new QualityReportAdapter(getApplicationContext(), model.getQualityReportsFromDB(this));
        lv.setAdapter(adapter);
    }

    /*
    Automatically loads the quality reports into the view upon resume
     */
    protected void onResume() {
        super.onRestart();
        adapter = new QualityReportAdapter(getApplicationContext(), model.getQualityReportsFromDB(this));
        lv.setAdapter(adapter);

    }

    /**
     * Button that cancels the user's activity
     * @param view
     */
    public void cancel (View view) {
        this.onBackPressed();
    }
}