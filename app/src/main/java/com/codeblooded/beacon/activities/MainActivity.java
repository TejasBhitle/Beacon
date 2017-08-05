package com.codeblooded.beacon.activities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codeblooded.beacon.R;
import com.codeblooded.beacon.util.MyBluetoothLeScanner;
import com.codeblooded.beacon.util.MyBluetoothUtils;

import uk.co.alt236.bluetoothlelib.device.BluetoothLeDevice;

/**
 * Created by tejas on 8/3/17.
 */

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    private MyBluetoothUtils mMyBluetoothUtils;
    private MyBluetoothLeScanner mScanner;

    private final BluetoothAdapter.LeScanCallback callback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            final BluetoothLeDevice deviceLe =
                    new BluetoothLeDevice(device, rssi, scanRecord, System.currentTimeMillis());

            textView.setText(deviceLe.getName());
        }
    };


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.text);
        button = (Button)findViewById(R.id.scan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startScan();
            }
        });

        mMyBluetoothUtils = new MyBluetoothUtils(this);

        mScanner = new MyBluetoothLeScanner(callback, mMyBluetoothUtils);

    }

    private void startScan() {
        final boolean isBluetoothOn = mMyBluetoothUtils.isBluetoothOn();
        final boolean isBluetoothLePresent = mMyBluetoothUtils.isBluetoothLeSupported();


        mMyBluetoothUtils.askUserToEnableBluetoothIfNeeded();
        if (isBluetoothOn && isBluetoothLePresent) {
            mScanner.scanLeDevice(-1, true);
            invalidateOptionsMenu();
        }
    }


}
