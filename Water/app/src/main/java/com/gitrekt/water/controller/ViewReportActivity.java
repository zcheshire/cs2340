package com.gitrekt.water.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;
import com.gitrekt.water.model.UserReaderContract;
import com.gitrekt.water.model.UserReaderDbHelper;
import com.gitrekt.water.model.UserReport;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
/*

Creates the layout for the listView for Water Reports

 */
public class ViewReportActivity extends AppCompatActivity {


    //set content for the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);
        Model model = Model.getInstance();
        TextView user = (TextView) findViewById(R.id.user);
        TextView condition = (TextView) findViewById(R.id.condition);
        TextView type = (TextView) findViewById(R.id.type);
        TextView location = (TextView) findViewById(R.id.location);
        TextView postID = (TextView) findViewById(R.id.postID);

        UserReport ur = model.getUserReportsFromDB(this).get(0);

        user.setText(ur.getUser().getUserName());
        condition.setText(ur.getCondition().toString());
        type.setText(ur.getType().toString());
        location.setText(ur.getLocation());
        postID.setText(ur.getReportNumber());
    }
}
