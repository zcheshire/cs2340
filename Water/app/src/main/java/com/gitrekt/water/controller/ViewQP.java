package com.gitrekt.water.controller;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;

public class ViewQP extends AppCompatActivity {
    private TextView user;
    private TextView virus;
    private TextView contaminant;
    private TextView condition;
    private TextView location;
    private TextView postID;
    private Model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_qp);
        model = Model.getInstance();
        user = (TextView) findViewById(R.id.user);
        condition = (TextView) findViewById(R.id.condition);
        virus = (TextView) findViewById(R.id.virus);
        contaminant = (TextView) findViewById(R.id.contaminant);
        location = (TextView) findViewById(R.id.location);
        postID = (TextView) findViewById(R.id.postID);

        user.setText(model.getQualityReports().get(0).getUser().getUserName());
        condition.setText(model.getQualityReports().get(0).getCondition().toString());
        virus.setText(model.getQualityReports().get(0).getVirusPPM().toString());
        contaminant.setText(model.getQualityReports().get(0).getContaminantPPM().toString());
        location.setText(model.getQualityReports().get(0).getLocation().toString());
        postID.setText(model.getQualityReports().get(0).getReportNumber());
    }

}
