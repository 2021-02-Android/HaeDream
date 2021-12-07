package com.example.haedream;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class IntroListAdapter extends BaseAdapter {
    Context context;
    ArrayList<IntroListItem> arrayList;
    String username;

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

        TextView name, depart, intro;
        CircleImageView iv_profile;

        name = convertView.findViewById(R.id.name);
        depart = convertView.findViewById(R.id.depart);
        intro = convertView.findViewById(R.id.intro_writing);
        iv_profile = convertView.findViewById(R.id.iv);

        name.setText("" + arrayList.get(position).getName());
        depart.setText("" + arrayList.get(position).getDepart());
        intro.setText(""+arrayList.get(position).getIntro());
        Glide.with(iv_profile).load("https://idox23.cafe24.com/"+arrayList.get(position).getProfile()).into(iv_profile);

        return convertView;
    }
}
