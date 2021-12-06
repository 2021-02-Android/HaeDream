package com.example.haedream;

// 아이템을 정의하는 클래스
// 아이템을 구성할 요소 텍스트 4개와 이미지1개, 5개의 변수 필요
public class simhelpitem {
    String category, details, info, location, point, period, name;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) { this.location = location; }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
