package com.example.hasini.smartremort;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class DeviceSelector extends Activity {

    private static int id;
    private static int type;
    private DB_Helper dbHelper;
    private DBController dbController;
    private Spinner getDevice;
    private List<String> devices = new ArrayList<>();
    private Button startDevice;

    public static int getId() {
        return id;
    }

    public static int getType() {
        return type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_selector);

        dbController = new DBController(this);

        dbHelper = new DB_Helper(getApplicationContext());

        devices = dbController.getAllDevice();

        getDevice = (Spinner)findViewById(R.id.selectDevice);
        startDevice = (Button)findViewById(R.id.btn_startDevice);

        addItemsOnCategorySpinner();

        startDevice.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startBtnClicked(v);
                    }
                }
        );

    }

    private void startBtnClicked(View v) {
        System.out.println("llllllll");
        id =dbController.getDeviceID(getDevice.getSelectedItem().toString());
        String s = dbController.getDeviceType(id);
        if(s.equals("AC")){
            type = 1;
        }else if(s.equals("Non-AC")){
            type=0;
        }
        Intent intent = new Intent(".Remort");
        startActivity(intent);
    }

    public void addItemsOnCategorySpinner() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,devices);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getDevice.setAdapter(dataAdapter);
    }

}
