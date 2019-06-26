package com.bestom.huanyu.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestom.huanyu.R;

import java.util.List;
import java.util.Map;

public class fileadapter extends BaseAdapter {
    private  List<Map<String, Object>> mData;
    private Context context;

    public fileadapter( List<Map<String, Object>> data, Context context) {
        mData = data;
        this.context = context;
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
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.file_item,null);
            holder = new ViewHolder();
            holder.apkimg=convertView.findViewById(R.id.img);
            Log.i("adapter--",postion+mData.get(postion).get("img").toString());
//            if (!(mData.get(postion).get("img").equals(""))){
//                holder.apkimg.setBackgroundResource((Integer) mData.get(postion).get("img"));
//            }
            holder.filename=convertView.findViewById(R.id.name);
            holder.filename.setText((CharSequence) mData.get(postion).get("name"));
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        return convertView;
    }

    public class ViewHolder{
        ImageView apkimg;
        TextView filename;
    }

}
