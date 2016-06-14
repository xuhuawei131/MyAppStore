package com.jiayuan.huawei.com.myappstore.ui.adapters.viewholders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public abstract class ViewHolderBase<T> {
    public View view;
    public int position;
    protected Context context;
    public abstract View getLayoutInfalerView();
    public abstract void findViewByIds();
    public abstract void bindData(int position,T obj);

    public ViewHolderBase(Context context) {
        this.context = context;
        this.view=getLayoutInfalerView();
        findViewByIds();
    }

    protected View findViewById(int resId) {
        if (view == null) {
            throw new RuntimeException("getView is null!");
        }
        return view.findViewById(resId);
    }

    protected  View inflateViewById(int resource){
        LayoutInflater inflater=LayoutInflater.from(context);
        return inflater.inflate(resource, null);
    }

    protected void setOnClickListener(View view,int position){
        if(context instanceof View.OnClickListener ){
            view.setOnClickListener((View.OnClickListener)context);
            view.setTag(position);
        }
    }


}
