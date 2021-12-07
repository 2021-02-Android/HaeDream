package com.example.haedream;

import android.content.Intent;
import android.util.Log;

public class MessageItem {

    private String name;
    private String profile;
    private String text;
    private String time;
    private String user_id;
    private String other_id;
    private String user_name;

    public MessageItem(String name, String text, String time, String user_id, String other_id, String user_name){
        this.name = name;
        this.text = text;
        this.time = time;
        this.user_id = user_id;
        this.other_id = other_id;
        this.user_name = user_name;
    }

/*
    public MessageItem(String name, String text, String time, String profile){
        this.name = name;
        this.text = text;
        this.time = time;
        this.profile = profile;
    }

    public MessageItem(String name, String text, String time){
        this.name = name;
        this.text = text;
        this.time = time;
    }
*/


    public MessageItem(){}

    // getter setter

    public void setUser_name(String user_name) { this.user_name = user_name;    }

    public String getUser_name() { return user_name;    }

    public void setOther_id(String other_id) { this.other_id = other_id;  }

    public String getOther_id() { return other_id; }

    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getUser_id() {  return user_id;  }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getProfile() {
        return profile;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getTime() {
        return time;
    }


}
