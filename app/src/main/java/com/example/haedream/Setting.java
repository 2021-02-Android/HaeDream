package com.example.haedream;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Setting extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        // 뒤로가기 버튼
        ImageButton back = (ImageButton) findViewById(R.id.list_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainSCR.class);
                startActivity(intent);
            }
        });

        // mypage 버튼
        ImageButton my = (ImageButton) findViewById(R.id.mypage);
        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyPage.class);
                startActivity(intent);
            }
        });

        // 프로필 변경 버튼
        Button profile = (Button) findViewById(R.id.change_profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyPage.class);
                startActivity(intent);
            }
        });

        // 비번 변경
        Button pw = (Button) findViewById(R.id.change_pw);
        pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChangePW.class);
                startActivity(intent);
            }
        });

        // 로그아웃
        Button logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(Setting.this)
                        .setMessage("로그아웃 하시겠습니까?") // 팝업 메시지 띄우기
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // "예" 버튼 클릭시 동작부
                                dialog.dismiss(); //팝업창 종료

                                ActivityCompat.finishAffinity(Setting.this); // 모든 액티비티 종료

                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent); // 로그인 창 실행

                                Toast.makeText(getApplicationContext(), "정상적으로 로그아웃되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // "아니오" 버튼 클릭시 동작부
                                dialog.dismiss(); //팝업창 종료
                            }
                        }).show();
            }
        });

        // 탈퇴
        Button withdraw = (Button) findViewById(R.id.withdrawal);
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
