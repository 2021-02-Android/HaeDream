package com.example.haedream;

/* 대화 리스트 아이템 _ 아이디
        사진 : image  상대이름 : otherUS_name
        메세지 마지막 시간 : time_diff ( 현재시간 - 마지막 메세지의 시간 )
        메세지 상태 : msg_state ( 보낸거면 '보냄 : ', 받은거면 '받음 : ')
        메세지 내용 : msg_content ( 마지막 메세지 내용 )
*/

// 채팅 리스트 목록 아이템에 필요한 것들
public class ConvertListItem {
    String name;    // 상대 이름
    String id;      // 상대 아이디
    String state;   // 수신 상태
    String content; // 마지막 내용
    String time;    // 시간
    String image;   // 상대 사진

    public ConvertListItem(){}

    public ConvertListItem (String name, String state, String content, String time) {
        this.name = name;
        this.state = state;
        this.content = content;
        this.time = time;
    }


    public ConvertListItem (String name, String state, String content, String time,String image) {
        this.name = name;
        this.state = state;
        this.content = content;
        this.time = time;
        this.image = image;
    }

    public void setId(String id) { this.id = id; }

    public String getId() { return id; }

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public String getState(){return state;}

    public void setState(String state){this.state = state;}

    public String getContent(){return content;}

    public void setContent(String content){this.content = content;}

    public String getTime(){return time;}

    public void setTime(String time){this.time = time;}

    public String getImage(){return image;}

    public void setImage(String image){this.image = image;}


}
