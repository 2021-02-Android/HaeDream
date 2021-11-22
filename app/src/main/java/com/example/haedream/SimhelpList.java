package com.example.haedream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SimhelpList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helplist);

        ListView listView = findViewById(R.id.listview);
        SimhelpAdapter adapter = new SimhelpAdapter();
        adapter.addItem(new simhelpitem("변민정", "이삿짐 | 가야1치안", "5분 거리 이사 도와주실분 구합니다", "5000p", R.drawable.move1));
        adapter.addItem(new simhelpitem("박경민", "청소 | 연제구", "설거지", "1000p", R.drawable.clean1));
        listView.setAdapter(adapter);

        ImageButton simButton = (ImageButton) findViewById(R.id.callbutton);
        simButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HelpCall.class);
                startActivity(intent);
            }
        });
    }

    public class SimhelpAdapter extends BaseAdapter {
        ArrayList<simhelpitem> items = new ArrayList<simhelpitem>();
        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(simhelpitem item){
            items.add(item);
        }
        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SimhelpItemView simhelpItemView = null;

            if(convertView == null){
                simhelpItemView = new SimhelpItemView(getApplicationContext());
            } else {
                simhelpItemView = (SimhelpItemView)convertView;
            }
            simhelpitem item = items.get(position);
            simhelpItemView.setName(item.getName());
            simhelpItemView.setSub(item.getSub());
            simhelpItemView.setSubstring(item.getSubstring());
            simhelpItemView.setPoint(item.getPoint());
            simhelpItemView.setImage(item.getResid());
            return simhelpItemView;
        }
    }
}