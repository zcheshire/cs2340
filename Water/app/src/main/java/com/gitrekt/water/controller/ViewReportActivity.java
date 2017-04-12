package com.gitrekt.water.controller;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
    private TextView user;
    private TextView type;
    private TextView condition;
    private TextView location;
    private TextView postID;


    private Model model;
    //set content for the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);
        model = Model.getInstance();
        user = (TextView) findViewById(R.id.user);
        condition = (TextView) findViewById(R.id.condition);
        type = (TextView) findViewById(R.id.type);
        location = (TextView) findViewById(R.id.location);
        postID = (TextView) findViewById(R.id.postID);

        user.setText(model.getUserReports().get(0).getUser().getUserName().toString());
        condition.setText(model.getUserReports().get(0).getCondition().toString());
        type.setText(model.getUserReports().get(0).getType().toString());
        location.setText(model.getUserReports().get(0).getLocation().toString());
        postID.setText(model.getUserReports().get(0).getReportNumber().toString());




    }
}
