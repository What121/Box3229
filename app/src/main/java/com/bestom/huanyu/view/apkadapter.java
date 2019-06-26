package com.bestom.huanyu.view;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestom.huanyu.R;

import java.util.List;

public class apkadapter extends BaseAdapter {
    private  List<PackageInfo> mData;
    private Context context;
    private Activity mActivity;

    public apkadapter(List<PackageInfo> data, Context context,Activity mActivity) {
        mData = data;
        this.context = context;
        this.mActivity=mActivity;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int postion, View convertView, ViewGroup viewGroup) {
        View item=null;
        ViewHolder holder = null;
        PackageInfo packageInfo= mData.get(postion);

        PackageManager pManager = context.getPackageManager();
        ApplicationInfo applicationInfo= packageInfo.applicationInfo;
        if(convertView == null){
            item = LayoutInflater.from(context).inflate(R.layout.apk_item,null);
            holder = new ViewHolder();
            holder.apkimg=item.findViewById(R.id.apk_img);
            holder.apkname=item.findViewById(R.id.apk_name);
            item.setTag(holder);
        }else{
            //复用的item
            item=convertView;
            holder = (ViewHolder)item.getTag();
        }
        holder.apkimg.setImageDrawable(pManager.getApplicationIcon(applicationInfo));
        holder.apkname.setText(pManager.getApplicationLabel(applicationInfo).toString());

        return item;
    }

    public class ViewHolder{
        ImageView apkimg;
        TextView apkname;
    }

}
