package com.example.haedream;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MySimItemAdapter extends BaseAdapter {
    Context context;
    ArrayList<MySimItem> arrayList;

    public MySimItemAdapter(Context context, ArrayList<MySimItem> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.mycall_item_list, parent, false);
        }

        TextView name, category, location, info;
        ImageView category_img;

        name = convertView.findViewById(R.id.my);
        category = convertView.findViewById(R.id.category);
        location = convertView.findViewById(R.id.sub);
        info = convertView.findViewById(R.id.substring);
        category_img = convertView.findViewById(R.id.category_list);

        if(arrayList.get(position).getCategory().equals("bae")){
            category_img.setImageResource(R.drawable.bae1);
        }
        if(arrayList.get(position).getCategory().equals("move")){
            category_img.setImageResource(R.drawable.move1);
        }
        if(arrayList.get(position).getCategory().equals("clean")){
            category_img.setImageResource(R.drawable.clean1);
        }
        if(arrayList.get(position).getCategory().equals("home")){
            category_img.setImageResource(R.drawable.home1);
        }
        if(arrayList.get(position).getCategory().equals("insect")){
            category_img.setImageResource(R.drawable.insect1);
        }
        if(arrayList.get(position).getCategory().equals("anything")){
            category_img.setImageResource(R.drawable.anything1);
        }

        location.setText(arrayList.get(position).getLocation());
        info.setText(arrayList.get(position).getInfo());
        category.setText(arrayList.get(position).getCategory());
        name.setText("" + arrayList.get(position).getName());

        return convertView;
    }
}
