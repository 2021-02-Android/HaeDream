package com.example.haedream;

import android.content.Intent;
import android.util.Log;

public class ChatItem {

    private String name;
    private String profile;
    private String text;
    private String time;

    public ChatItem(String name, String text, String time, String profile){
        this.name = name;
        this.text = text;
        this.time = time;
        this.profile = profile;
    }

    //firebase DB에 객체로 값을 읽어올 때..
    //파라미터가 비어있는 생성자가 핑요함.
    public ChatItem(String USER_NAME, String s) { }

    public ChatItem(){}

    // getter setter
    public void setUserID(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getProfile() {
        return profile;
    }

    public String getTime() {
        return time;
    }
}
