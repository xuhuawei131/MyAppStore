package com.jiayuan.huawei.com.myappstore.ui.adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.jiayuan.huawei.com.myappstore.ui.adapters.viewholders.AppLocalViewHolder;
import com.jiayuan.huawei.com.myappstore.ui.beans.AppLocalBean;

import java.util.List;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class InstalledAdapter extends MyBaseAdapter<AppLocalViewHolder,AppLocalBean> {

    public InstalledAdapter(Context context, List<AppLocalBean> arrayList) {
        super(context, arrayList);
    }

    @Override
    protected AppLocalViewHolder createViewHolder(int position, AppLocalBean bean, ViewGroup viewgroup) {
        AppLocalViewHolder viewHolder=new AppLocalViewHolder(context);
        return viewHolder;
    }

    @Override
    protected void bindViewHolder(int position, AppLocalBean bean, AppLocalViewHolder holder) {
        holder.bindData(position,bean);
    }
}
