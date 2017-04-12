package com.gitrekt.water.controller;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.*;
import com.gitrekt.water.R;
import android.widget.TextView;

import com.gitrekt.water.model.UserReport;

import java.util.ArrayList;

/**
 * Created by jerodray on 3/7/17.
 * Adapter for Water Report list view
 */

class WaterReportAdapter extends BaseAdapter {

    private final Context myContext;
    private final ArrayList<UserReport> userReports;

    /**
     * Creates new Water Report Adapter
     * @param myContext
     * @param userReports
     */

    public WaterReportAdapter(Context myContext, ArrayList<UserReport> userReports) {
        this.myContext = myContext;
        this.userReports = userReports;
    }

    /**
     * Gets the number of user reports
     * @return int size
     */
    @Override
    public int getCount() {
        return userReports.size();
    }

    /**
     * Gets the repost at a certain position within the user report array
     * @param position
     * @return Object report
     */
    @Override
    public Object getItem(int position) {
        return userReports.get(position);
    }

    /**
     * Gets the id of a report at a certain position within array
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Generates the list view that displays the reports
     * @param position
     * @param convertView
     * @param parent
     * @return View v
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(myContext, R.layout.activity_view_report, null);
        TextView tvUser = (TextView) v.findViewById(R.id.user);
        TextView tvType = (TextView) v.findViewById(R.id.type);
        TextView tvCondition = (TextView) v.findViewById(R.id.condition);
        TextView tvLocation = (TextView) v.findViewById(R.id.location);
        TextView tvPostID = (TextView) v.findViewById(R.id.postID);

        tvUser.setText(userReports.get(position).getUserr());
        tvType.setText(userReports.get(position).getWt());
        tvCondition.setText(userReports.get(position).getWc());
        tvLocation.setText(userReports.get(position).getLocation());
        tvPostID.setText(userReports.get(position).getReportNumber());

        //v.setTag(userReports.get(position).getReportNumber());

        return v;

    }
}
