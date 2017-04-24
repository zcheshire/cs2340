package com.gitrekt.water.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;
import com.gitrekt.water.model.QualityReport;

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

        QualityReport qr = model.getQualityReportsFromDB(this).get(0);

        user.setText(qr.getUsername());
        condition.setText(qr.getCondition().toString());
        virus.setText(qr.getVirusPPM());
        contaminant.setText(qr.getContaminantPPM());
        location.setText(qr.getLocation());
        postID.setText(qr.getReportNumber());
    }

}
