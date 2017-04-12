package com.gitrekt.water.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;

public class ViewQP extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_qp);
        Model model = Model.getInstance();
        TextView user = (TextView) findViewById(R.id.user);
        TextView condition = (TextView) findViewById(R.id.condition);
        TextView virus = (TextView) findViewById(R.id.virus);
        TextView contaminant = (TextView) findViewById(R.id.contaminant);
        TextView location = (TextView) findViewById(R.id.location);
        TextView postID = (TextView) findViewById(R.id.postID);

        user.setText(model.getQualityReports().get(0).getUser().getUserName());
        condition.setText(model.getQualityReports().get(0).getCondition().toString());
        virus.setText(model.getQualityReports().get(0).getVirusPPM());
        contaminant.setText(model.getQualityReports().get(0).getContaminantPPM());
        location.setText(model.getQualityReports().get(0).getLocation());
        postID.setText(model.getQualityReports().get(0).getReportNumber());
    }

}
