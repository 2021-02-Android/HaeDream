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
* */
// 소개해드림에서 사용 자아이템 클릭시 사용자 정보 화면 나타내줌
public class IntroInfo extends AppCompatActivity {
    TextView name, depart, intro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_check);

        Intent intent = getIntent();
        String it_name = intent.getStringExtra("name");
        String it_depart = intent.getStringExtra("depart");
        String it_intro = intent.getStringExtra("intro");

        String user_name = intent.getStringExtra("user_name");
        Log.d("[IntroInfo user_name 인텐트 받음]", user_name);

        String other_id = intent.getStringExtra("other_id");
        Log.d("[IntroInfo other_id 인텐트 받음]", other_id);

        String it_userid = intent.getStringExtra("user_id");
        Log.d("[IntroInfo user_id 인텐트 받아옴]", it_userid);

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
                intent.putExtra("user_id", it_userid);
                Log.d("[user_id 인텐트 전달]", it_userid);
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
                it.putExtra("name", name.getText().toString());
                it.putExtra("user_id",it_userid); // 현재 사용자
                it.putExtra("other_id",other_id); // 상대방 아이디
                it.putExtra("user_name",user_name); // 사용자 이름
                Log.d("[info에서 chat으로 user_id 인텐트 전달]", it_userid);
                Log.d("[info에서 chat으로 other_id 인텐트 전달]", other_id);
                startActivity(it);
                finish();
            }
        });

    }
}
