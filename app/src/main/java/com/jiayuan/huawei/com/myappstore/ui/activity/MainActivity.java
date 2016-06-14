package com.jiayuan.huawei.com.myappstore.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.jiayuan.huawei.com.myappstore.R;
import com.jiayuan.huawei.com.myappstore.ui.activity.BaseActivity;
import com.jiayuan.huawei.com.myappstore.ui.adapters.InstalledAdapter;
import com.jiayuan.huawei.com.myappstore.ui.beans.AppLocalBean;
import com.jiayuan.huawei.com.myappstore.ui.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private List<AppLocalBean> arrayList;
    private InstalledAdapter adapter;
    @Bind(R.id.listView)
    ListView listView;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void initData() {
        arrayList = new ArrayList<AppLocalBean>();
        adapter = new InstalledAdapter(this, arrayList);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    protected int getActivityContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViewByIds() {
        listView.setAdapter(adapter);
    }

    @Override
    protected void requestService() {
        Observable.just("")
                .doOnSubscribe(new Action0() {//准备
                    @Override
                    public void call() {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .map(new Func1<String, List<AppLocalBean>>() {
                    @Override
                    public List<AppLocalBean> call(String s) {
                        return scanLocalApps();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        progressBar.setVisibility(View.GONE);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<AppLocalBean>>() {
                    @Override
                    public void call(List<AppLocalBean> list) {
                        arrayList.clear();
                        arrayList.addAll(list);
                        adapter.notifyDataSetChanged();
                    }
                });

    }


    private void uninstallPackage(String packagename) {
        for (AppLocalBean bean : arrayList) {
            if (bean.packageName.equals(packagename)) {
                arrayList.remove(bean);
                adapter.notifyDataSetChanged();
                return;
            }
        }
    }

    @Override
    protected void onMyDestory() {
        unregisterReceiver(mBroadcastReceiver);
    }

    private List<AppLocalBean> scanLocalApps() {
        List<AppLocalBean> arrayList = new ArrayList<>();
        List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                AppLocalBean tmpInfo = new AppLocalBean();
                tmpInfo.appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                tmpInfo.packageName = packageInfo.packageName;
                tmpInfo.versionName = packageInfo.versionName;
                tmpInfo.versionCode = packageInfo.versionCode;
                tmpInfo.appIcon = packageInfo.applicationInfo.loadIcon(getPackageManager());
                arrayList.add(tmpInfo);
            }
        }
        return arrayList;
    }




    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        String packageName=arrayList.get(position).packageName;
        switch (v.getId()){
            case R.id.btn_shared:
                Utils.shareAndSendAPK(this,packageName);
                break;
            case R.id.btn_uninstalled:
                Utils.unInstalled(this,packageName);
                break;
            case R.id.layout_content:
                AppLocalBean bean=arrayList.get(position);
                if(bean.isShow){
                    bean.isShow=false;
                }else{
                    bean.isShow=true;
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_open:
                Utils.runAppByPackageName(this,packageName);
                break;
        }
    }

    /**
     * 监听应用的广播
     */
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
                String packageName = intent.getDataString().substring(8);
                requestService();
            }
            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                String packageName = intent.getDataString().substring(8);
                if (packageName != null && packageName.length() > 0) {
                    uninstallPackage(packageName);
                }
            }
        }
    };

}
