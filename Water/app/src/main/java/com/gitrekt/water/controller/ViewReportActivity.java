package com.gitrekt.water.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;
<<<<<<< HEAD
import com.gitrekt.water.model.UserReaderContract;
import com.gitrekt.water.model.UserReaderDbHelper;
import com.gitrekt.water.model.UserReport;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
/*
=======
>>>>>>> 2aa37df7640bed18e7c9d8a1d44653d4ce49edc5

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

        user.setText(model.getUserReports().get(0).getUser().getUserName());
        condition.setText(model.getUserReports().get(0).getCondition().toString());
        type.setText(model.getUserReports().get(0).getType().toString());
        location.setText(model.getUserReports().get(0).getLocation());
        postID.setText(model.getUserReports().get(0).getReportNumber());




    }
}
