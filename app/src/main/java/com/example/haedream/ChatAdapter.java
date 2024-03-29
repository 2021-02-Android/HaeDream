package com.example.haedream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.CharCodeKt;

public class ChatAdapter extends BaseAdapter {
    // String username; // 사용자 이름 받아옴
    ArrayList<MessageItem> messageItems;
    LayoutInflater layoutInflater;

    public ChatAdapter(ArrayList<MessageItem> messageItems, LayoutInflater layoutInflater) {
        this.messageItems = messageItems;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return messageItems.size();
    }

    @Override
    public Object getItem(int position) {
        return messageItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        //현재 보여줄 번째의(position)의 데이터로 뷰를 생성
        MessageItem item = messageItems.get(position);

        //재활용할 뷰는 사용하지 않음
        View itemView = null;

        // 사용자 이름

        //메세지가 내 메세지인지??
        // getName = 메세지 작성자 이름 o
        // username = 시스템 사용자 이름

        if(item.getName().equals(G.UserName)){
            itemView= layoutInflater.inflate(R.layout.my_msgbox,viewGroup,false);
        }else{
            itemView= layoutInflater.inflate(R.layout.other_msgbox,viewGroup,false);
        }
        System.out.println("[ChatAdapter username] " + G.UserName);


        //만들어진 itemView에 값들 설정

        //CircleImageView image = itemView.findViewById(R.id.image);
        TextView tvName= itemView.findViewById(R.id.tv_name);
        TextView tvMsg= itemView.findViewById(R.id.tv_msg);
        TextView tvTime= itemView.findViewById(R.id.tv_time);

        tvName.setText(item.getName());
        tvMsg.setText(item.getText());
        tvTime.setText(item.getTime());

        //Glide.with(itemView).load(item.getProfile()).into(image);

        return itemView;
    }
}
