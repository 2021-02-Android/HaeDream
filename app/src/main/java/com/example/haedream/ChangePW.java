package com.example.haedream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChangePW extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pw);


        // 설정 버튼
        ImageButton set = (ImageButton) findViewById(R.id.setting);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Setting.class);
                startActivity(intent);
            }
        });

        // 뒤로가기 버튼
        ImageButton back = (ImageButton) findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Setting.class);
                startActivity(intent);
            }
        });

        // 마이페이지 버튼
        ImageButton my = (ImageButton) findViewById(R.id.mypage);
        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.teamplay.MyPage.class);
                startActivity(intent);
            }
        });

        // 비번 변경 버튼
        Button changePW = (Button) findViewById(R.id.change_btn);
        changePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 지금 비번 id : nowPW      새 비번 : newPW   비번 확인 : check_newPW


            }
        });
    }
}
