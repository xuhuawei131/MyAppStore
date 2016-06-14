package com.jiayuan.huawei.com.myappstore.ui.modules;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * $desc$
 * 蓝牙管理器
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class BlueToothModule {
    private BluetoothAdapter mBluetoothAdapter;
    private Activity context;

    public BlueToothModule(Activity context) {
        mBluetoothAdapter = BluetoothAdapter
                .getDefaultAdapter();
        this.context = context;
    }

    public void startBlueTooth() {
        if (mBluetoothAdapter == null) {//本机没有找到蓝牙硬件或驱动

        } else {
            // 如果本地蓝牙没有开启，则开启
            if (mBluetoothAdapter != null && !mBluetoothAdapter.isEnabled()) {
//                Intent mIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//                context.startActivityForResult(mIntent, 1);
                mBluetoothAdapter.enable();
            }
        }
    }

    public void stopBlueTooth() {
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();//关闭蓝牙
        }
    }
}
