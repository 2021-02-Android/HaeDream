package com.example.haedream;

// 리스트 아이템을 다루는 파일

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SimhelpItemAdapter extends BaseAdapter {
    Context context;
    ArrayList<simhelpitem> arrayList;

    public SimhelpItemAdapter(Context context, ArrayList<simhelpitem> arrayList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.simhelp_item_list, parent, false);
        }

        TextView sub, substring, point;
        ImageView category;

        category = convertView.findViewById(R.id.category_list);
        sub = convertView.findViewById(R.id.sub);
        substring = convertView.findViewById(R.id.substring);
        point = convertView.findViewById(R.id.point);

        if(arrayList.get(position).getCategory().equals("bae")){
            category.setImageResource(R.drawable.bae1);
        }
        if(arrayList.get(position).getCategory().equals("move")){
            category.setImageResource(R.drawable.move1);
        }
        if(arrayList.get(position).getCategory().equals("clean")){
            category.setImageResource(R.drawable.clean1);
        }
        if(arrayList.get(position).getCategory().equals("home")){
            category.setImageResource(R.drawable.home1);
        }
        if(arrayList.get(position).getCategory().equals("insect")){
            category.setImageResource(R.drawable.insect1);
        }
        if(arrayList.get(position).getCategory().equals("anything")){
            category.setImageResource(R.drawable.anything1);
        }
        sub.setText(arrayList.get(position).getDetails() + "/" + arrayList.get(position).getLocation());
        substring.setText("심부름 내용 : " + arrayList.get(position).getInfo());
        point.setText("포인트 : " + arrayList.get(position).getPoint());

        return convertView;
    }
}
