package com.example.haedream;

import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/*
*  사진 image, 이름 name, 학과 depart, 소개 intro
*  뒤로가기 버튼 back, 대화하기 버튼 startChat
*/

// 소개해드림에서 사용 자아이템 클릭시 사용자 정보 화면 나타내줌
public class IntroInfo extends AppCompatActivity {
    TextView name, depart, intro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_check);

        Intent intent = getIntent();
        String it_name = intent.getStringExtra("name");
        Log.d("[IntroInfo it_name 인텐트 받음]", it_name); // 상대 이름

        String it_depart = intent.getStringExtra("depart");
        String it_intro = intent.getStringExtra("intro");

        String username = intent.getStringExtra("username");
        Log.d("[IntroInfo username 인텐트 받음]", username); // 시스템 사용자

        G.UserName = username;

        String other_id = intent.getStringExtra("other_id");
        Log.d("[IntroInfo other_id 인텐트 받음]", other_id); // 상대 아이디

        String user_id = intent.getStringExtra("user_id");
        Log.d("[IntroInfo user_id 인텐트 받아옴]", user_id); // 시스템 아이디

        name = findViewById(R.id.name);
        depart = findViewById(R.id.depart);
        intro = findViewById(R.id.intro);

        name.setText(it_name);
        depart.setText(it_depart);
        intro.setText(it_intro);

        // 뒤로가기
        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Intro_List.class);
                intent.putExtra("user_id", user_id);
                Log.d("[user_id 인텐트 전달]", user_id);
                startActivity(intent);
                finish();
            }
        });

        // 대화하기
        Button chat = findViewById(R.id.startChat);
        chat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ChatActivity.class);
                it.putExtra("name", name.getText().toString());// 상대방 이름
                it.putExtra("user_id",user_id); // 현재 사용자
                it.putExtra("other_id",other_id); // 상대방 아이디
                it.putExtra("username",username); // 사용자 이름
                Log.d("[info에서 chat으로 user_id 인텐트 전달]", user_id);
                Log.d("[info에서 chat으로 other_id 인텐트 전달]", other_id);
                startActivity(it);
                finish();
            }
        });

    }
}
