package com.gitrekt.water.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;

public class ViewQualityReportActivity extends AppCompatActivity {
    private ListView lv;
    private QualityReportAdapter adapter;
    private Model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quality_report);
        model = Model.getInstance();
        lv = (ListView) findViewById(R.id.qualityReportView);
        //adapter = new QualityReportAdapter(getApplicationContext(), model.getQualityReports());
        adapter = new QualityReportAdapter(getApplicationContext(), model.getQualityReports());
        lv.setAdapter(adapter);
    }
    protected void onResume() {
        super.onRestart();

        adapter = new QualityReportAdapter(getApplicationContext(), model.getQualityReports());
        lv.setAdapter(adapter);

    }
    public void cancel (View view) {

        this.onBackPressed();

    }
}
