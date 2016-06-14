package com.jiayuan.huawei.com.myappstore.ui.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;

import java.util.List;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class Utils {

    public static void shareAndSendAPK(Context context,String packageName) {
        if(TextUtils.isEmpty(packageName)){
             packageName = context.getPackageName();
        }
        String appDir = null;
        try {
            // 指定包名的程序源文件路径
            appDir = context.getPackageManager().getApplicationInfo(
                    packageName, 0).sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        appDir = "file://" + appDir;
        Uri uri = Uri.parse(appDir);
        // 发送
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("*/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, "发送"));
    }

    public static void shareAndSendAPK(Context context) {
        shareAndSendAPK(context,null);
    }

    public static void runAppByPackageName(Context context,String packageName) {
        Intent intent = new Intent();
        PackageManager packageManager = context.getPackageManager();
        intent = packageManager.getLaunchIntentForPackage(packageName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP) ;
        context.startActivity(intent);
    }
    public static void unInstalled(Context context,String packageName){
        Uri packageURI = Uri.parse("package:" + packageName);
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        uninstallIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(uninstallIntent);
    }
}
