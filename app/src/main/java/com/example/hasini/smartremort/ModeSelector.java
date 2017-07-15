package com.example.hasini.smartremort;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Set;

public class ModeSelector extends AppCompatActivity {

    private static String mode = "";
    private Button training;
    private Button user;



    public static String getMode() {
        return mode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_selector);


        training = (Button)findViewById(R.id.btn_trainingMode);
        user = (Button)findViewById(R.id.btn_userMode);

        training.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        trainingBtnClicked(v);
                    }
                }
        );

        user.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userBtnClicked(v);
                    }
                }
        );
    }

    private void trainingBtnClicked(View v) {
        mode = "training";
        Intent intent = new Intent(".SaveDevice");
        startActivity(intent);
    }

    private void userBtnClicked(View v) {
        mode = "user";
        Intent intent = new Intent(".DeviceSelector");
        startActivity(intent);
    }
}
