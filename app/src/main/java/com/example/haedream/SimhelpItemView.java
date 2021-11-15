package com.example.haedream;

// 리스트 아이템을 다루는 파일

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SimhelpItemView extends LinearLayout {
    TextView textView1, textView2, textView3, textView4;
    //1 - 이름, 2 - 심부름 분야, 3 -심부름 내용, 4 - 포인트
    ImageView imageView;
    public SimhelpItemView(Context context) {
        super(context);
        init(context); //인플레이션해서 붙여주는 역
    }
    public SimhelpItemView (Context context, @Nullable AttributeSet attrs){
        super(context,attrs);
    }

    // 객체(xml 레이아웃)을 인플레이션화해서 붙여줌
    //LayoutInflater를 아용해 시스템 서비스를 참조하 수 있다
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.simhelp_item_list,this, true);

        //텍스트 뷰와 이미지뷰 변수에 xml의 해당 값의 id를 연결한다.
        textView1 = findViewById(R.id.name);
        textView2 = findViewById(R.id.sub);
        textView3 = findViewById(R.id.substring);
        textView4 = findViewById(R.id.point);
        imageView = findViewById(R.id.imageview);
    }

    //set 메서드를 통해 정의만 되어 있는 아이템의 이미지와 텍스트를 넣을 수 있게 만듬
    public void setName(String name){
        textView1.setText(name);
    }
    public void setSub(String sub){
        textView2.setText(sub);
    }
    public void setSubstring(String substring){
        textView3.setText(substring);
    }
    public void setPoint(String point){ textView4.setText(point); }
    public void setImage(int resid){
        imageView.setImageResource(resid);
    }

}
