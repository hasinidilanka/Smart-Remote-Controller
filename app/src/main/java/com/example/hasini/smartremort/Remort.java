package com.example.hasini.smartremort;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;
import android.os.Handler;
import java.util.logging.LogRecord;

public class Remort extends AppCompatActivity {
    private int id ;
    private String mode = ModeSelector.getMode();
    private String type;
    private int bttn;

    private Button onButton;
    private Button offButton;
    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;

    private final String address = "20:16:09:19:50:28";

    BluetoothThread btt;
    Handler writeHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remort);
        connectBluetooth();


        onButton = (Button)findViewById(R.id.btnOn);
        zero = (Button)findViewById(R.id.btnZero);
        one = (Button)findViewById(R.id.btnOne);
        two = (Button)findViewById(R.id.btnTwo);
        three = (Button)findViewById(R.id.btnThree);
        four = (Button)findViewById(R.id.btnFour);
        five = (Button)findViewById(R.id.btnFive);
        six = (Button)findViewById(R.id.btnSix);
        seven = (Button)findViewById(R.id.btnSeven);
        eight = (Button)findViewById(R.id.btnEight);
        nine = (Button)findViewById(R.id.btnNine);

        if(mode.equals("training")){
            one.setEnabled(false);
            two.setEnabled(false);
            three.setEnabled(false);
            four.setEnabled(false);
            five.setEnabled(false);
            six.setEnabled(false);
            seven.setEnabled(false);
            eight.setEnabled(false);
            nine.setEnabled(false);
        }

        zero.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BtnClicked(v,zero);
                        if(mode.equals("training")){
                            one.setEnabled(true);
                        }
                    }
                }
        );

        one.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BtnClicked(v,one);
                        if(mode.equals("training")){
                            two.setEnabled(true);
                        }
                    }
                }
        );
        two.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BtnClicked(v,two);
                        if(mode.equals("training")){
                            three.setEnabled(true);
                        }
                    }
                }
        );
        three.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BtnClicked(v,three);
                        if(mode.equals("training")){
                            four.setEnabled(true);
                        }
                    }
                }
        );
        four.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BtnClicked(v,four);
                        if(mode.equals("training")){
                            five.setEnabled(true);
                        }
                    }
                }
        );
        five.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BtnClicked(v,five);
                        if(mode.equals("training")){
                            six.setEnabled(true);
                        }
                    }
                }
        );
        six.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BtnClicked(v,six);
                        if(mode.equals("training")){
                            seven.setEnabled(true);
                        }
                    }
                }
        );
        seven.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BtnClicked(v,seven);
                        if(mode.equals("training")){
                            eight.setEnabled(true);
                        }
                    }
                }
        );
        eight.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BtnClicked(v,eight);
                        if(mode.equals("training")){
                            nine.setEnabled(true);
                        }
                    }
                }
        );
        nine.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BtnClicked(v,nine);
                    }
                }
        );
//        one.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        BtnClicked(v);
//                    }
//                }
//        );


        System.out.println(id);
        System.out.println(mode);

    }

    private void BtnClicked(View v,Button b) {
        if(mode.equals("training")){
            b.setEnabled(false);
        }
        writeData("bttn="+b.getText()+"\r");

    }

    public void writeData(String data) {
//        Log.v(TAG, "Data passed" + data);

        Message msg = Message.obtain();
        msg.obj = data;
        writeHandler.sendMessage(msg);
    }


    public void connectBluetooth() {
//        Log.v(TAG, "Bluetooth connected...");

        // Only one thread at a time
        if (btt != null) {
//            Log.w(TAG, "Already connected!");
            return;
        }

        // Initialize the Bluetooth thread, passing in a MAC address
        // and a Handler that will receive incoming messages
        btt = new BluetoothThread(address, new Handler() {


           @Override
            public void handleMessage(Message message) {

                String s = (String) message.obj;

                // Do something with the message
                if (s.equals("CONNECTED")) {
                    Toast.makeText(Remort.this, "Connection stable", Toast.LENGTH_SHORT).show();
                    if(mode.equals("training")){


                        writeData("mode=0\r");
                        writeData("dvid="+SaveDevice.getId()+"\r");
                        writeData("type="+SaveDevice.getType()+"\r");

                    }else if(mode.equals("user")){

                        writeData("mode=1\r");
                        writeData("dvid="+DeviceSelector.getId()+"\r");
                        writeData("type="+DeviceSelector.getType()+"\r");



                    }


                } else if (s.equals("DISCONNECTED")) {
                    Toast.makeText(Remort.this, "Disconnected", Toast.LENGTH_SHORT).show();

                } else if (s.equals("CONNECTION FAILED")) {
                    Toast.makeText(Remort.this, "Connection failed", Toast.LENGTH_LONG).show();

                } else if (s.equals("ADAPTER 404")){
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//                    startActivityForResult(enableBtIntent, REQUEST_E);

                } else {

                }
            }
        });

        // Get the handler that is used to send messages
        writeHandler = btt.getWriteHandler();
        System.out.println("write "+writeHandler);

        // Run the thread
        btt.start();

    }
    public void disconnectBluetooth() {
//        Log.v(TAG, "Bluetooth Disconnected...");

        if(btt != null) {
            btt.interrupt();
            btt = null;
        }
    }

    protected void onPause() {
        super.onPause();

        disconnectBluetooth();
    }

    @Override
    protected void onResume() {
        super.onResume();

        connectBluetooth();
    }

}
