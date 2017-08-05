package com.codeblooded.beacon.util;

import android.bluetooth.BluetoothAdapter;
import android.os.Handler;
import android.util.Log;

/**
 * Created by tejas on 8/5/17.
 */

public class MyBluetoothLeScanner {

    private final Handler mHandler;
    private final BluetoothAdapter.LeScanCallback mLeScanCallback;
    private final MyBluetoothUtils mBluetoothUtils;
    private boolean mScanning;

    public MyBluetoothLeScanner(final BluetoothAdapter.LeScanCallback leScanCallback, final MyBluetoothUtils bluetoothUtils) {
        mHandler = new Handler();
        mLeScanCallback = leScanCallback;
        mBluetoothUtils = bluetoothUtils;
    }

    public boolean isScanning() {
        return mScanning;
    }

    public void scanLeDevice(final int duration, final boolean enable) {
        if (enable) {
            if (mScanning) {
                return;
            }
            Log.d("TAG", "~ Starting Scan");
            // Stops scanning after a pre-defined scan period.
            if (duration > 0) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("TAG", "~ Stopping Scan (timeout)");
                        mScanning = false;
                        mBluetoothUtils.getBluetoothAdapter().stopLeScan(mLeScanCallback);
                    }
                }, duration);
            }
            mScanning = true;
            mBluetoothUtils.getBluetoothAdapter().startLeScan(mLeScanCallback);
        } else {
            Log.d("TAG", "~ Stopping Scan");
            mScanning = false;
            mBluetoothUtils.getBluetoothAdapter().stopLeScan(mLeScanCallback);
        }
    }
}
