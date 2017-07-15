package com.example.hasini.smartremort;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Hasini on 6/27/2017.
 */

public class DBController {
    private DB_Helper dbHelper;

    private SQLiteDatabase database;

    public DBController(Context context) {
        dbHelper = new DB_Helper(context);
    }

    public  boolean checkDeviceName(String deviceName){
        database =dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + dbHelper.getTABLE_Device()+" where "+dbHelper.getDeviceName()+" = '" + deviceName + "'";
        Cursor c = database.rawQuery(selectQuery, null);
        String type="";

        while (c.moveToNext()) {
            type = c.getString(c.getColumnIndex(dbHelper.getRemortName()));
        }
        if(type.equals("")){
            return true;
        }else{
            return false;
        }

    }

    public  boolean insertDevice(String deviceName,String remortName){
        database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.getDeviceName(),deviceName);
        contentValues.put(dbHelper.getRemortName(),remortName);

        long result = database.insert(dbHelper.getTABLE_Device(),null,contentValues);
        if(result==-1){
            System.out.println("**************************Failed");
            return false;
        }else{
            System.out.println("entered device ***********************************");
            return  true;
        }
    }


    public String getDeviceType(int id){
        database =dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + dbHelper.getTABLE_Device()+" where ID = '" + id + "'";
        Cursor c = database.rawQuery(selectQuery, null);
        String type="";

        while (c.moveToNext()) {
            type = c.getString(c.getColumnIndex(dbHelper.getRemortName()));
        }
//        System.out.println("id******************id"+id);
        return type;
    }

    public ArrayList<String> getAllDevice(){
        ArrayList<String> devices = new ArrayList<>();
        database =dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + dbHelper.getTABLE_Device();
        Cursor c = database.rawQuery(selectQuery, null);


        while (c.moveToNext()) {
            String type = c.getString(c.getColumnIndex(dbHelper.getDeviceName()));
            devices.add(type);
        }
//        System.out.println("id******************id"+id);
        return devices;
    }

    public int getDeviceID(String deviceName){
        database =dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + dbHelper.getTABLE_Device()+" where "+dbHelper.getDeviceName()+"= '" + deviceName + "'";
        Cursor c = database.rawQuery(selectQuery, null);
        int id=0;

        while (c.moveToNext()) {
            id = c.getInt(c.getColumnIndex(dbHelper.getID()));
        }
//        System.out.println("id******************id"+id);
        return id;
    }


}
