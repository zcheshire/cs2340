package com.gitrekt.water.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;

import org.w3c.dom.Text;

public class ViewReportActivity extends AppCompatActivity {
    private TextView user;
    private TextView type;
    private TextView condition;
    private TextView location;
    private Model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);
        model = Model.getInstance();
        user = (TextView) findViewById(R.id.user);
        condition = (TextView) findViewById(R.id.condition);
        type = (TextView) findViewById(R.id.type);
        location = (TextView) findViewById(R.id.location);

        user.setText(model.getUserReports().get(0).getUser().getUserName());
        condition.setText(model.getUserReports().get(0).getCondition().toString());
        type.setText(model.getUserReports().get(0).getType().toString());
        location.setText(model.getUserReports().get(0).getLocation().toString());

    }
}
