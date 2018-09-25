package com.google.emarket;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    Activity activity;
    List<Item> litem;
    LayoutInflater inflater;

    public ListViewAdapter(Activity activity, List<Item> litem) {
        this.activity = activity;
        this.litem = litem;
    }

    @Override
    public int getCount() {
        return litem.size();
    }

    @Override
    public Object getItem(int position) {
        return litem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = (LayoutInflater) activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.listview_item,null);
        TextView txtitem=(TextView)itemView.findViewById(R.id.list_name);
        TextView txtprice =(TextView)itemView.findViewById(R.id.list_price);
        txtitem.setText(litem.get(position).getName());
        txtprice.setText(litem.get(position).getPrice());
        return itemView;
    }
}
