package com.codeblooded.beacon.activities;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.codeblooded.beacon.R;
import com.codeblooded.beacon.activities.tempactivites.LeDeviceDetailsActivity;
import com.codeblooded.beacon.util.MyBluetoothLeScanner;
import com.codeblooded.beacon.util.MyBluetoothUtils;

import uk.co.alt236.bluetoothlelib.device.BluetoothLeDevice;

/**
 * Created by tejas on 8/3/17.
 */

public class MainActivity extends AppCompatActivity {

    TextView textView;
    private MyBluetoothUtils mMyBluetoothUtils;
    private MyBluetoothLeScanner mScanner;
    Toolbar toolbar;
    private ProgressDialog progressDialog;

    private static final String LOG = "MainActivity";
    private static final int BLUETOOTH_ENABLE_REQUEST = 123;
    public static final int LOCATION_REQUEST = 929;

    private final BluetoothAdapter.LeScanCallback callback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            final BluetoothLeDevice deviceLe =
                    new BluetoothLeDevice(device, rssi, scanRecord, System.currentTimeMillis());

            progressDialog.cancel();
            Log.e("Device detected",deviceLe.getName());
            textView.setText(deviceLe.getName());

            Bundle bundle = new Bundle();
            bundle.putParcelable("beacon",deviceLe);
            Intent intent = new Intent(MainActivity.this, LeDeviceDetailsActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            stopScan();
        }
    };


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.text);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Scanning");
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Stop Scanning",
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                stopScan();
                progressDialog.cancel();
            }
        });

        setSupportActionBar(toolbar);

        mMyBluetoothUtils = new MyBluetoothUtils(this);

        mScanner = new MyBluetoothLeScanner(callback, mMyBluetoothUtils);

        checkLocationPermission();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if( checkBluetooth() && checkLocationPermission()){
            startScan();
        }
    }

    private boolean checkBluetooth(){
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if(adapter != null){
            if(!adapter.isEnabled()){
                startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), BLUETOOTH_ENABLE_REQUEST);
                return false;
            }
            else return true;
        }
        else{
            Toast.makeText(MainActivity.this, "Device doesn't support Bluetooth", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Request Location Permission Access")
                        .setMessage("Location Permission is needed for the app to function")
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                        LOCATION_REQUEST);
                            }
                        })
                        .create()
                        .show();
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission. ACCESS_COARSE_LOCATION},
                        LOCATION_REQUEST);
            }
            return false;
        } else {
            return true;
        }
    }

    private void startScan() {
        final boolean isBluetoothOn = mMyBluetoothUtils.isBluetoothOn();
        final boolean isBluetoothLePresent = mMyBluetoothUtils.isBluetoothLeSupported();

        Log.e(LOG,"started Scanning");
        textView.setText("Scanning started");
        progressDialog.show();
        mMyBluetoothUtils.askUserToEnableBluetoothIfNeeded();
        if (isBluetoothOn && isBluetoothLePresent) {
            mScanner.scanLeDevice(-1, true);
            invalidateOptionsMenu();
        }
    }

    private void stopScan(){
        textView.setText("Scanning Stopped");
        mScanner.scanLeDevice(-1,false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case BLUETOOTH_ENABLE_REQUEST:
                if(resultCode != Activity.RESULT_OK)
                    showBlueToothRequestDialogIfDenied();
                else onResume();
                break;
        }
    }

    private void showBlueToothRequestDialogIfDenied(){
        new AlertDialog.Builder(MainActivity.this)
                .setMessage("App cannot function without bluetooth.")
                .setPositiveButton("Allow app to access bluetooth", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                     checkBluetooth();
                    }
                })
                .setNegativeButton("Exit App", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.finish();
                    }
                })
        .create().show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //location permission granted
                } else {
                    Toast.makeText(MainActivity.this,"App cannot function without location permission",Toast.LENGTH_SHORT)
                            .show();
                }
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopScan();
    }
}
