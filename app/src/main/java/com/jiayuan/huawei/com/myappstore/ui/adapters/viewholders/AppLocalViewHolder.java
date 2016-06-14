package com.jiayuan.huawei.com.myappstore.ui.adapters.viewholders;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jiayuan.huawei.com.myappstore.R;
import com.jiayuan.huawei.com.myappstore.ui.beans.AppLocalBean;
import com.jiayuan.huawei.com.myappstore.ui.customview.roundimage.RoundedImageView;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class AppLocalViewHolder extends ViewHolderBase<AppLocalBean> {
    private RoundedImageView image_icon;

    private View layout_operate;
    private View btn_uninstalled;
    private View btn_shared;
    private View btn_open;


    private View layout_content;
    private TextView text_name;
    private TextView text_version;
    private TextView text_packageName;

    public AppLocalViewHolder(View view) {
        super(view);
    }


    @Override
    public void findViewByIds() {
        image_icon = (RoundedImageView) findViewById(R.id.image_icon);

        layout_content = findViewById(R.id.layout_content);
        layout_operate = findViewById(R.id.layout_operate);

        btn_uninstalled = findViewById(R.id.btn_uninstalled);
        btn_shared = findViewById(R.id.btn_shared);
        btn_open = findViewById(R.id.btn_open);

        text_name = (TextView) findViewById(R.id.text_name);
        text_version = (TextView) findViewById(R.id.text_version);
        text_packageName = (TextView) findViewById(R.id.text_packageName);
    }

    @Override
    public void bindData(int position, AppLocalBean obj) {
        setOnClickListener(btn_uninstalled, position);
        setOnClickListener(btn_shared, position);
        setOnClickListener(layout_content, position);
        setOnClickListener(btn_open, position);

        text_name.setText(obj.appName);
        Log.v("xhw", "position " + position + " name " + obj.appName);
        text_version.setText(obj.versionName);
        text_packageName.setText(obj.packageName);

        image_icon.setImageDrawable(obj.appIcon);

        if (obj.isShow) {
            layout_operate.setVisibility(View.VISIBLE);
        } else {
            layout_operate.setVisibility(View.GONE);
        }
    }


}
