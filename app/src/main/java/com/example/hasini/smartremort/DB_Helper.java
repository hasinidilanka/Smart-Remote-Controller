package com.example.hasini.smartremort;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hasini on 6/24/2017.
 */

public class DB_Helper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static   final String DATABASE_NAME = "smartremote.db";

    //Database Tables
    private static   final String TABLE_Device = "Device_table";
//    private static  final String TABLE_Channel = "Channel_table";
//    private static final String

    //Column names
    private static   final String ID = "ID";
    private static   final String deviceName = "deviceName";
    private static   final String remortName = "remoteName";
//    private static   final String numberZero = "numberZero";
//    private static   final String numberTwo = "numberTwo";
//    private static   final String numberThree = "numberThree";
//    private static   final String numberFour = "numberFour";
//    private static   final String numberFive = "numberFive";
//    private static   final String numberSix = "numberSix";
//    private static   final String numberSeven = "numberSeven";
//    private static   final String numberEight = "numberEight";
//    private static   final String numberNine = "numberNine";
//    private static   final String on = "on";
//    private static   final String off = "off";


    private static final String CREATE_TABLE_Device = "CREATE TABLE "
            + TABLE_Device + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + deviceName + " TEXT,"
            + remortName + " TEXT"
           + ")";

    public DB_Helper(Context context) {
        super(context,"smartremort.db" , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_Device);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_Device);
        onCreate(db);
    }


    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    public String getTABLE_Device() {
        return TABLE_Device;
    }

    public  String getID() {
        return ID;
    }

    public  String getDeviceName() {
        return deviceName;
    }

    public  String getRemortName() {
        return remortName;
    }
}
