package com.gitrekt.water.controller;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.*;
import com.gitrekt.water.R;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

import com.gitrekt.water.model.QualityReport;

import java.util.ArrayList;

/**
 * Created by zacharycheshire on 3/12/17.
 */

public class QualityReportAdapter extends BaseAdapter {

    private Context myContext;
    private ArrayList<QualityReport> qualityReports;

    public QualityReportAdapter(Context myContext, ArrayList<QualityReport> qualityReports) {
        this.myContext = myContext;
        this.qualityReports = qualityReports;
    }

    @Override
    public int getCount() {
        return qualityReports.size();
    }

    @Override
    public Object getItem(int position) {
        return qualityReports.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(myContext, R.layout.activity_view_qp, null);
        TextView tvUser = (TextView) v.findViewById(R.id.user);
        TextView tvCondition = (TextView) v.findViewById(R.id.condition);
        TextView tvVirus = (TextView) v.findViewById(R.id.virus);
        TextView tvContaminant = (TextView) v.findViewById(R.id.contaminant);
        TextView tvLocation = (TextView) v.findViewById(R.id.location);
        //TextView tvVirus = (TextView) v.findViewById(R.id.virusPPM);
        TextView tvPostID = (TextView) v.findViewById(R.id.postID);

        tvUser.setText(qualityReports.get(position).getUser().getUserName());
        tvCondition.setText(qualityReports.get(position).getCondition().toString());
        tvVirus.setText(qualityReports.get(position).getVirusPPM().toString());
        tvContaminant.setText(qualityReports.get(position).getContaminantPPM().toString());
        tvLocation.setText(qualityReports.get(position).getLocation());
        tvPostID.setText(qualityReports.get(position).getReportNumber());

        //v.setTag(userReports.get(position).getReportNumber());

        return v;

    }
}
