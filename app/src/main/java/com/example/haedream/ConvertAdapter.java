package com.example.haedream;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

/*
     대화 리스트 아이템 _ 아이디
        사진 : image  상대이름 : otherUS_name
        메세지 마지막 시간 : time_diff ( 현재시간 - 마지막 메세지의 시간 )
        메세지 상태 : msg_state ( 보낸거면 '보냄 : ', 받은거면 '받음 : ')
        메세지 내용 : msg_content ( 마지막 메세지 내용 )
*/

public class ConvertAdapter extends BaseAdapter {
    Context context;
    ArrayList<ConvertListItem> arrayList;

    public ConvertAdapter(Context context, ArrayList<ConvertListItem> arrayList){
        this.context = context;
        this.arrayList = arrayList;

    }

    @Override
    public int getCount() { return arrayList.size(); }

    @Override
    public Object getItem(int position) { return arrayList.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.convert_item_list, parent, false);
        }

        TextView state, content, name;
        CircleImageView image;

        image = convertView.findViewById(R.id.image);    // 상대 이미지
        name = convertView.findViewById(R.id.name); // 상대이름
        state = convertView.findViewById(R.id.msg_state);   // 받음 or 보냄
        content = convertView.findViewById(R.id.msg_content); // 소개

        name.setText("" + arrayList.get(position).getName());
        state.setText("" + arrayList.get(position).getState());
        content.setText("" + arrayList.get(position).getContent());
        // Glide.with(image).load("https://idox23.cafe24.com/"+arrayList.get(position).getImage()).into(image);



        return convertView;



    }



}
