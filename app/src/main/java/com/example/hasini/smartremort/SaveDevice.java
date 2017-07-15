package com.example.hasini.smartremort;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SaveDevice extends Activity {

    private Spinner selectDevice ;
    private TextView deviceName;
    private Button saveDevice;
    private List<String> deviceTypes = new ArrayList<>();
    private DBController dbcontroller;

    private static int id;
    private static int type;

    public static int getId() {
        return id;
    }

    public static int getType() {
        return type;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_device);
        selectDevice = (Spinner)findViewById(R.id.selectType);
        deviceName = (TextView)findViewById(R.id.input_name);
        saveDevice = (Button)findViewById(R.id.btn_saveDevice);

        dbcontroller = new DBController(this);

        deviceTypes.add("AC");
        deviceTypes.add("Non-AC");
//        deviceTypes.add("bbbbb");

        addItemsOnCategorySpinner();

        saveDevice.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveBtnClicked(v);
                    }
                }
        );
    }

    private void saveBtnClicked(View v) {
        String name = deviceName.getText().toString();
        String devtype = String.valueOf(selectDevice.getSelectedItem());

        if(name.equals("")){
            displayError("Fill device name","Error");
        }
        else if(!dbcontroller.checkDeviceName(name)){
            displayError("This device already exist","Error");
        }
        else{
            dbcontroller.insertDevice(name,devtype);
            Toast.makeText(this,"Successfully Added",Toast.LENGTH_SHORT).show();
            id =dbcontroller.getDeviceID(name);
            String s = dbcontroller.getDeviceType(id);
            if(s.equals("AC")){
                type =1;
            }else if(s.equals("Non-AC")){
                type=0;
            }
            Intent i = new Intent(".Remort");
            startActivity(i);

        }
    }


    public void addItemsOnCategorySpinner() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,deviceTypes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectDevice.setAdapter(dataAdapter);
    }


    private void displayError(String message,String title){
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

        dlgAlert.setMessage(message);
        dlgAlert.setTitle(title);
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();

        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

    }

}
