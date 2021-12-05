package com.example.haedream;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MyPage extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        Intent intent = getIntent();
        String it_userid = intent.getStringExtra("user_id");
        Log.d("[user_id 인텐트 받아옴]", it_userid);

        /*
        * setText로 EditText칸에 (user_name) 값 넣어줄 수 있대
        * */

        // 설정 버튼
        ImageButton set = (ImageButton) findViewById(R.id.setting);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Setting.class);
                startActivity(intent);
            }
        });

        // 뒤로가기
        ImageButton back = (ImageButton) findViewById(R.id.list_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainSCR.class);
                startActivity(intent);
            }
        });

        // 정보 수정 버튼
        Button changePW = (Button) findViewById(R.id.change_btn);
        changePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChangeInfo.class);
                intent.putExtra("accepted", it_userid);
                startActivity(intent);
            }
        });


    }
}
