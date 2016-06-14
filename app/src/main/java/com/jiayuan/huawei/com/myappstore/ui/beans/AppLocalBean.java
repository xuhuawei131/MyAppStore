package com.jiayuan.huawei.com.myappstore.ui.beans;

import android.graphics.drawable.Drawable;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class AppLocalBean {private static final long serialVersionUID = 1L;
    /** 应用名称 **/
    public String appName="";
    /** 包名 **/
    public String packageName="";
    /**  版本号 **/
    public String versionName="";
    /** 版本 **/
    public int versionCode=0;
    /** 图标**/
    public Drawable appIcon=null;
    /**是否显示拓展**/
    public boolean isShow=false;


    @Override
    public boolean equals(Object o) {
        AppLocalBean other=(AppLocalBean)o;
        return packageName.equals(other.packageName);
    }
}
