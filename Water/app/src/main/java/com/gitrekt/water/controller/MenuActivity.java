package com.gitrekt.water.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;

public class MenuActivity extends AppCompatActivity {

    private Model model;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        model = Model.getInstance();
        lv = (ListView) findViewById(R.id.reportListView);
    }

    protected void onResume() {
        super.onResume();
        /*ArrayList<UserReport> temp = new ArrayList<>();
        boolean num = getRep(temp);
        if (num) {*/
        WaterReportAdapter adapter = new WaterReportAdapter(this, model.getUserReportsFromDB(this));
        lv.setAdapter(adapter);
        System.out.println(model.getUserReportsFromDB(this).toString());
        //}
    }

    public void goHome (View view) {
        //Intent intent = new Intent(this, HomeActivity.class);
        //startActivity(intent);
        super.onBackPressed();
        overridePendingTransition(R.animator.hold, R.animator.slide_out);
    }
}
