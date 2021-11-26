package com.example.haedream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Intro_List extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce_list);

        // 말풍선 버튼 누를 시 이동
        ImageButton list = (ImageButton) findViewById(R.id.list_btn);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Intro_List.class);
                startActivity(intent);
            }
        });

        // 심부름 버튼 누를 시 이동
        ImageButton simbu = (ImageButton) findViewById(R.id.go_sim);
        simbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SimhelpList.class);
                startActivity(intent);
            }
        });

        // 기부 버튼 누를 시 이동
/*        ImageButton give = (ImageButton) findViewById(R.id.go_give);
        give.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), .class);
                startActivity(intent);
            }
        });

        // 거래 버튼 누를 시 이동
        ImageButton imageButton = (ImageButton) findViewById(R.id.go_garae);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), .class);
                startActivity(intent);
            }
        });*/

        // 소개 버튼 누를 시 이동
        ImageButton intro = (ImageButton) findViewById(R.id.go_intro);
        intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Intro_List.class);
                startActivity(intent);
            }
        });

    }
}
