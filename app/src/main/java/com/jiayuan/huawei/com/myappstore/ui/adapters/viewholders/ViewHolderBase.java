package com.jiayuan.huawei.com.myappstore.ui.adapters.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public abstract class ViewHolderBase<T> extends RecyclerView.ViewHolder {
    public View view;
    protected Context context;
    public abstract void findViewByIds();
    public abstract void bindData(int position,T obj);

    public ViewHolderBase(View view) {
        super(view);
        this.view = view;
        this.context = view.getContext();
        findViewByIds();
    }

    protected View findViewById(int resId) {
        if (view == null) {
            throw new RuntimeException("getView is null!");
        }
        return view.findViewById(resId);
    }

    protected void setOnClickListener(View view,int position){
        if(context instanceof View.OnClickListener ){
            view.setOnClickListener((View.OnClickListener)context);
            view.setTag(position);
        }
    }


}
