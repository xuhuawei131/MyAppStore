package com.jiayuan.huawei.com.myappstore.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected abstract void initData();
    protected abstract int getActivityContentLayout();
    protected abstract void findViewByIds();
    protected abstract void requestService();
    protected abstract void onMyDestory();
    protected BaseActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initScreenFull();
        super.onCreate(savedInstanceState);
        context=this;
        initData();
        int layoutID=getActivityContentLayout();
        if(layoutID!=0){
            setContentView(layoutID);
        }
        ButterKnife.bind(this);
        findViewByIds();
        requestService();

    }
    private void initScreenFull(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // 不锁屏
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onMyDestory();
    }

}
