package com.example.haedream;

public class IntroListItem {
    // other_id : 상대 아이디 = 이 아이템에 나타내는 사람의 아이디
    // name : 아이템에 나타내는 사람의 이름
    // userid : 시스템 사용자 아이디
    // username : 시스템 사용자 이름
    String name, depart, userid, intro, other_id, username, profile;

    public String getUsername() { return username;  }

    public void setUsername(String username) { this.username = username;  }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDepart() { return depart; }

    public void setDepart(String depart) { this.depart = depart; }

    public String getUserid() { return userid; }

    public void setUserid(String userid) { this.userid = userid; }

    public void setIntro(String intro) { this.intro = intro; }

    public String getIntro() { return intro; }

    public void setOther_id(String other_id) {
        this.other_id = other_id;
    }

    public String getOther_id() {
        return other_id;
    }

    public String getProfile() { return profile; }

    public void setProfile(String profile) { this.profile = profile; }
}
