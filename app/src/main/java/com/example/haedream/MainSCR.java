package com.example.haedream;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainSCR extends AppCompatActivity {

    private long backKeyPressedTime = 0;  // 뒤로가기 버튼을 누른 시간 저장
    private Toast toast;

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
            }
        });
    }

    @Override
    public void onBackPressed() {  // 메인 화면에서 뒤로가기 버튼 2회 누르면 앱 종료
        // 2000 milliseconds = 2 seconds
        // 가장 최근에 뒤로가기 버튼을 누른 시간에 2초를 더해 현재시간과 비교 후, 2초가 지났으면 메시지 출력
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "뒤로가기 버튼을 다시 누르면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // 2초가 지나지 않은 시점에서 뒤로가기 다시 클릭시 앱 종료
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            toast.cancel();  // 알림 팝업(Toast) 표시 해제
            ActivityCompat.finishAffinity(this);
            System.exit(0);  // 시스템 종료
        }
    }
}
