package com.gitrekt.water.model;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by John on 4/24/2017.
 */

public class FirebaseDatabase implements Database {
    private DatabaseReference mDatabase;
    private DatabaseReference userReference;
    private DatabaseReference qrReference;
    private DatabaseReference urReference;

    ArrayList<User> users;
    ArrayList<QualityReport> qrs;
    ArrayList<UserReport> urs;

    //Helper methods -- parse objects from hashmaps (firebase)
    /*public User parseUser(HashMap<String, String> hm) {
        System.out.println(hm.entrySet().toArray());
        String ut = hm.get("userType");
        return new User(hm.get("userName"),
                hm.get("passWord"),
                UserType.valueOf(ut));
    }
    public QualityReport parseQR(HashMap<String, String> hm) {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date parsedDate = new Date();
        try {
            parsedDate = df.parse(hm.get("date"));
        } catch (Exception e) {
            System.out.println("Error parsing date for QR");
        }
        return new QualityReport(hm.get("username"),
                OverallCondition.valueOf(hm.get("overallCondition")),
                hm.get("location"),
                hm.get("longitude"),
                hm.get("latitude"),
                parsedDate,
                hm.get("virusPPM"),
                hm.get("contaminantPPM"));
    }

    public UserReport parseUR(HashMap<String, String> hm) {
        return new UserReport(hm.get("username"),
                hm.get("waterTypee"),
                hm.get("conditionTypee"),
                hm.get("location"),
                hm.get("longitude"),
                hm.get("latitude"));
    }*/

    public FirebaseDatabase() {
        users = new ArrayList<User>();
        qrs = new ArrayList<QualityReport>();
        urs = new ArrayList<UserReport>();

        mDatabase = com.google.firebase.database.FirebaseDatabase.getInstance().getReference();
        userReference = mDatabase.child("users");
        qrReference = mDatabase.child("qrs");
        urReference = mDatabase.child("urs");

        userReference.addValueEventListener(new ValueEventListener(){
            public void onDataChange(DataSnapshot ds) {
                users = new ArrayList<User>();
                for (DataSnapshot u: ds.getChildren()) {
                    System.out.println(u.getValue());

                    //Workaround since Firebase doesn't support enums
                    User user = new User();
                    user.setUserName((String) u.child("userName").getValue());
                    user.setPassWord((String) u.child("passWord").getValue());
                    user.setUserType(UserType.valueOf((String) u.child("userType").getValue()));
                    users.add(user);
                }
            }
            public void onCancelled(DatabaseError de) { }
        });
        qrReference.addValueEventListener(new ValueEventListener(){
            public void onDataChange(DataSnapshot ds) {
                qrs = new ArrayList<QualityReport>();
                for (DataSnapshot qr: ds.getChildren()) {
                    System.out.println(qr.getValue());

                    //Workaround since Firebase doesn't support enums
                    //DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                    Date parsedDate = qr.child("date").getValue(Date.class);//new Date();
                    /*try {
                        parsedDate = df.parse((String) qr.child("date").getValue(Date.class));
                    } catch (Exception e) {
                        System.out.println("Error parsing date for QR");
                    }*/

                    QualityReport qualityReport
                            = new QualityReport((String) qr.child("username").getValue(),
                            OverallCondition.valueOf((String) qr.child("condition").getValue()),
                            (String) qr.child("location").getValue(),
                            (String) qr.child("longitude").getValue(),
                            (String) qr.child("latitude").getValue(),
                            parsedDate,
                            (String) qr.child("virusPPM").getValue(),
                            (String) qr.child("contaminantPPM").getValue());

                    qrs.add(qualityReport);
                }
            }

            public void onCancelled(DatabaseError de) { }
        });
        urReference.addValueEventListener(new ValueEventListener(){
            public void onDataChange(DataSnapshot ds) {
                urs = new ArrayList<UserReport>();
                for (DataSnapshot ur: ds.getChildren()) {
                    System.out.println(ur.getValue());

                    //Workaround since Firebase doesn't support enums
                    UserReport userReport
                            = new UserReport((String) ur.child("username").getValue(),
                            WaterType.valueOf((String) ur.child("type").getValue()),
                            ConditionType.valueOf((String) ur.child("condition").getValue()),
                            (String) ur.child("location").getValue(),
                            (String) ur.child("longitude").getValue(),
                            (String) ur.child("latitude").getValue());
                    urs.add(userReport);
                }
            }
            public void onCancelled(DatabaseError de) { }
        });
    }

    public void setContext(Context context){
        //Nothing to do for firebase
    }
    public void reset(){
        userReference.setValue(null);
        qrReference.setValue(null);
        urReference.setValue(null);
    }

    public void insertUser(User user){
        userReference.child(user.getUserName()).setValue(user);
    }
    public void insertQR(QualityReport qr, User currentUser){
        qrReference.child(Integer.toString(qr.hashCode())).setValue(qr);
    }
    public void insertUR(UserReport ur, User currentUser){
        urReference.child(Integer.toString(ur.hashCode())).setValue(ur);
    }

    public ArrayList<User> getUsers(){
        return users;
    }
    public ArrayList<QualityReport> getQRs(){
        return qrs;
    }
    public ArrayList<UserReport> getURs(){
        return urs;
    }
}
