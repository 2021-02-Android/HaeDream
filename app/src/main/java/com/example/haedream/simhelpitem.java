package com.example.haedream;

// 아이템을 정의하는 클래스
// 아이템을 구성할 요소 텍스트 4개와 이미지1개, 5개의 변수 필요
public class simhelpitem {
    int resid;
    String name; //리스트 이름
    String sub; //심부름 분야 / 제목
    String substring; //심부름 내용
    String point; //포인트

    //생성
    public  simhelpitem(String name, String sub, String substring, String point,int resid){
        this.name = name;
        this.sub = sub;
        this.substring = substring;
        this.point = point;
        this.resid = resid;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getSub(){
        return sub;
    }
    public void setSub(String sub){
        this.sub = sub;
    }

    public String getSubstring(){
        return substring;
    }
    public void setSubstring(String substring){
        this.substring = substring;
    }

    public String getPoint(){
        return point;
    }
    public void setPoint(String point){
        this.point = point;
    }

    public int getResid(){
        return  resid;
    }

    @Override
    public String toString(){
        return "simhelpitem{" +
                "name='" + name + '\'' +
                ", sub='" + sub + '\'' +
                ", substring='" + substring + '\'' +
                ", point='" + point + '\'' +
                '}';
    }
}
