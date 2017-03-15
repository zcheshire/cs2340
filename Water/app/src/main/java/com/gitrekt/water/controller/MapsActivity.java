package com.gitrekt.water.controller;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.gitrekt.water.R;
import com.gitrekt.water.model.Model;
import com.gitrekt.water.model.UserReport;
import com.gitrekt.water.model.QualityReport;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //Get a reference to the model singleton
        model = Model.getInstance();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ArrayList<UserReport> list = model.getUserReports();
        ArrayList<QualityReport> qpList = model.getQualityReports();
        //Loops through reports and adds them to the mapView
        for (UserReport report: list) {
            double longitude = Double.parseDouble(report.getLongitude());
            double latitude = Double.parseDouble(report.getLatitude());
            LatLng point = new LatLng(longitude, latitude);
            mMap.addMarker(new MarkerOptions().position(point).title(report.getType().toString() + report.getCondition()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
        }
        for (QualityReport qreport: qpList) {
            DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            double longitude = Double.parseDouble(qreport.getLongitude());
            double latitude = Double.parseDouble(qreport.getLatitude());
            LatLng point = new LatLng(longitude, latitude);
            String tmp = df.format(qreport.getDate().getTime());
            mMap.addMarker(new MarkerOptions().position(point).title("Condition: " + qreport.getCondition().toString() + " " + tmp));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
        }
    }
}
