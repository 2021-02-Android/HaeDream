package com.example.haedream;

import android.content.DialogInterface;
import android.content.Intent;
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

public class Setting extends AppCompatActivity {
    String user_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        Intent userintent = getIntent();
        user_id = userintent.getStringExtra("user_id");
        Log.d("[TAG] 로그인 아이디 인텐트 전달", user_id);

        // 뒤로가기 버튼
        ImageButton back = (ImageButton) findViewById(R.id.list_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainSCR.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });

        // mypage 버튼
        ImageButton my = (ImageButton) findViewById(R.id.mypage);
        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyPage.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });

        // 프로필 변경 버튼
        Button profile = (Button) findViewById(R.id.change_profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileChange.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });

        // 비번 변경
        Button pw = (Button) findViewById(R.id.change_pw);
        pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChangePW.class);
                intent.putExtra("user_id", user_id);
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
                new AlertDialog.Builder(Setting.this)
                        .setMessage("탈퇴 하시겠습니까?") // 팝업 메시지 띄우기
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                /*
                                // "예" 버튼 클릭시 동작부
                                UserManagement.getInstance().requestUnlink(new UnLinkResponseCallback() {
                                    @Override
                                    public void onFailure(ErrorResult errorResult) {
                                        //회원탈퇴 실패 시 동작
                                    }

                                    @Override
                                    public void onSessionClosed(ErrorResult errorResult) {
                                        //세션이 닫혔을 시 동작.
                                    }

                                    @Override
                                    public void onNotSignedUp() {
                                        //가입되지 않은 계정이 회원탈퇴를 요구할 경우 동작.
                                    }

                                    @Override
                                    public void onSuccess(Long result) {
                                        //회원탈퇴 성공 시 동작.
                                        dialog.dismiss(); //팝업창 종료

                                        ActivityCompat.finishAffinity(Setting.this); // 모든 액티비티 종료

                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent); // 로그인 창 실행

                                        Toast.makeText(getApplicationContext(), "정상적으로 로그아웃되었습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                 */
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

    }
}
