package com.example.haedream;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainSCR extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        String user_id;
        Intent userintent = getIntent();
        user_id = userintent.getStringExtra("userid");
        Log.d("[TAG] 로그인 아이디 인텐트 전달", user_id);

        // 설정 버튼
        ImageButton set = (ImageButton) findViewById(R.id.setting);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Setting.class);
                startActivity(intent);
            }
        });

        // 마이페이지 버튼
        ImageButton my = (ImageButton) findViewById(R.id.mypage);
        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyPage.class);
                startActivity(intent);
            }
        });
/*
     //   *** 각 함수에 Intent intent = new Intent(getApplicationContext(), javaname.class); 여기에서
     //   *** 'javaname'에 설정, 마이페이지, 심부름, 기부, 거래, 소개 java class 이름 그대로 갖다 붙이면 됨 !!

        // 설정 버튼 누를 시 설정 화면 이동
        ImageButton setButton = (ImageButton) findViewById(R.id.setting);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), javaname.class);
                startActivity(intent);
            }
        });

        // 마이페이지 버튼 누를 시 마이페이지 화면 이동
        ImageButton myButton = (ImageButton) findViewById(R.id.mypage);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), javaname.class);
                startActivity(intent);
            }
        });
*/
        // 심부름 버튼 누를 시 심부름 화면 이동
        ImageButton simButton = (ImageButton) findViewById(R.id.simbu_button);
        simButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SimhelpList.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });

        // 소개 버튼 누를 시 소개 화면 이동
        ImageButton introButton = (ImageButton) findViewById(R.id.intro_button);
        introButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Intro_List.class);
                startActivity(intent);
            }
        });

    /*    // 기부 버튼 누를 시 기부 화면 이동
        ImageButton donaButton = (ImageButton) findViewById(R.id.dona_button);
        donaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), javaname.class);
                startActivity(intent);
            }
        });

        // 거래 버튼 누를 시 거래 화면 이동
        ImageButton transButton = (ImageButton) findViewById(R.id.trans_button);
        transButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), javaname.class);
                startActivity(intent);
            }
        });


*/
    }
}
