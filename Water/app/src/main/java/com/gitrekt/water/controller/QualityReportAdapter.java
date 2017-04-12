package com.gitrekt.water.controller;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.*;
import com.gitrekt.water.R;
import android.widget.TextView;

import com.gitrekt.water.model.QualityReport;

import java.util.ArrayList;

/**
 * Created by zacharycheshire on 3/12/17.
 */

class QualityReportAdapter extends BaseAdapter {

    private final Context myContext;
    private final ArrayList<QualityReport> qualityReports;

    /**
     * Creates new Quality Report Adapter
     * @param myContext
     * @param qualityReports
     */
    public QualityReportAdapter(Context myContext, ArrayList<QualityReport> qualityReports) {
        this.myContext = myContext;
        this.qualityReports = qualityReports;
    }

    /**
     * Gets the number of quality reports
     * @return int num
     */
    @Override
    public int getCount() {
        return qualityReports.size();
    }

    /**
     * Gets the report in the array at a location
     * @param position
     * @return Object report
     */
    @Override
    public Object getItem(int position) {
        return qualityReports.get(position);
    }

    /**
     * Gets the report id from the array at the given position
     * @param position
     * @return long id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Generates the list view of all the reports
     * @param position
     * @param convertView
     * @param parent
     * @return View v
     */
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

        tvUser.setText(qualityReports.get(position).userr);
        tvCondition.setText(qualityReports.get(position).overallConditionn);
        tvVirus.setText(qualityReports.get(position).getVirusPPM());
        tvContaminant.setText(qualityReports.get(position).getContaminantPPM());
        tvLocation.setText(qualityReports.get(position).getLocation());
        tvPostID.setText(qualityReports.get(position).getReportNumber());

        //v.setTag(userReports.get(position).getReportNumber());

        return v;

    }
}
