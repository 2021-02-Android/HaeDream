package com.example.haedream;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class IntroListAdapter extends BaseAdapter {
    Context context;
    ArrayList<IntroListItem> arrayList;

    public IntroListAdapter(Context context, ArrayList<IntroListItem> arrayList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.intro_item_list, parent, false);
        }

        TextView name, depart;

        name = convertView.findViewById(R.id.name);
        depart = convertView.findViewById(R.id.depart);

        name.setText("" + arrayList.get(position).getName());
        depart.setText("" + arrayList.get(position).getDepart());

        return convertView;
    }
}
