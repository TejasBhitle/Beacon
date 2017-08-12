package com.codeblooded.beacon.activities.tempactivites;

import android.bluetooth.BluetoothClass;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.codeblooded.beacon.R;

import java.util.Arrays;

import uk.co.alt236.bluetoothlelib.device.BluetoothLeDevice;

/**
 * Created by tejas on 8/12/17.
 */

public class LeDeviceDetailsActivity extends AppCompatActivity {

    BluetoothLeDevice device;
    private TextView deviceInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_le_device_details);


        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_close_white_24dp));

        deviceInfo = (TextView)findViewById(R.id.device_info);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            device = bundle.getParcelable("beacon");
            String deviceInfoString = "Name : "+device.getName()+"\n"
                    +"Address: "+device.getAddress()+"\n"
                    +"Class: "+device.getBluetoothDeviceClassName()+"\n"
                    //+"Major Class: "+device.getBluetoothDeviceMajorClassName()+"\n"
                    +"Services: "+device.getBluetoothDeviceKnownSupportedServices()+"\n"
                    +"\n\n"
                    +"Scan Record: \n"+ Arrays.toString(device.getScanRecord());

            deviceInfo.setText(deviceInfoString);
        }

    }
}
