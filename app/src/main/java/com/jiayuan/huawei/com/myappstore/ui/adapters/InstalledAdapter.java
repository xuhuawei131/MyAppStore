package com.jiayuan.huawei.com.myappstore.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiayuan.huawei.com.myappstore.R;
import com.jiayuan.huawei.com.myappstore.ui.activity.MainActivity;
import com.jiayuan.huawei.com.myappstore.ui.adapters.viewholders.AppLocalViewHolder;
import com.jiayuan.huawei.com.myappstore.ui.beans.AppLocalBean;

import java.util.List;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class InstalledAdapter extends RecyclerView.Adapter<AppLocalViewHolder> {

    private List<AppLocalBean> list;
    private MainActivity context;

    public InstalledAdapter(MainActivity context, List<AppLocalBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public AppLocalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_installed_layout, parent, false);
        AppLocalViewHolder holder = new AppLocalViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AppLocalViewHolder holder, int position) {
        holder.bindData(position, list.get(position));
    }

    @Override
    public int getItemCount() {
        int length = list == null ? 0 : list.size();
        return length;
    }
}
