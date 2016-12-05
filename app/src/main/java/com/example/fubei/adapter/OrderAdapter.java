package com.example.fubei.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fubei.R;
import com.example.fubei.bean.ItemBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class OrderAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<ItemBean> mItemBeans;

    public OrderAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
//        mItemBeans = ItemDataUtils.getItemBeans();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_order_layout,null);
            holder.item_img = (ImageView) convertView.findViewById(R.id.image_lin);
            holder.item_type = (TextView) convertView.findViewById(R.id.text_type);
            holder.item_time = (TextView) convertView.findViewById(R.id.text_time_stu);
            holder.item_amt = (TextView) convertView.findViewById(R.id.text_amt);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == 1)
            holder.item_img.setVisibility(View.GONE);


        return convertView;
    }

    class ViewHolder {
        ImageView item_img;
        TextView item_type;
        TextView item_time;
        TextView item_amt;
    }
}
