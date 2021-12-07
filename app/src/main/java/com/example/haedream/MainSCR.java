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
        user_id = userintent.getStringExtra("user_id");
        Log.d("[TAG] 로그인 아이디 인텐트 전달", user_id);

        // 마이페이지 버튼
        ImageButton my = (ImageButton) findViewById(R.id.mypage);
        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyPage.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
                finish();
            }
        });

        // 설정 버튼
        ImageButton setButton = (ImageButton) findViewById(R.id.setting);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Setting.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
                finish();
            }
        });

        // 마이페이지 버튼 누를 시 마이페이지 화면 이동
        ImageButton myButton = (ImageButton) findViewById(R.id.mypage);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyPage.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
                finish();
            }
        });

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
                intent.putExtra("user_id", user_id);
                startActivity(intent);
                finish();
            }
        });

    }
}
