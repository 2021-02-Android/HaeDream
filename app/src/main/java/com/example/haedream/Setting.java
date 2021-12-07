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

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

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
                                delete_user(user_id);
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

    public void delete_user(String id) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "탈퇴가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                Log.d("[TAG] 탈퇴", "탈퇴 완료");

                ActivityCompat.finishAffinity(Setting.this); // 모든 액티비티 종료

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent); // 로그인 창 실행
            }
        };
        Setting.DeleteRequest deleteRequest = new Setting.DeleteRequest(id, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Setting.this);
        queue.add(deleteRequest);
    }

    public class DeleteRequest extends StringRequest {
        // 서버 URL 설정 ( PHP 파일 연동 - Database 바로 접근 불가, php 중간 매체로 파싱하여 사용 )
        final static private String URL = "http://idox23.cafe24.com/user_delete.php";
        private Map<String, String> map;

        public DeleteRequest(String userID, Response.Listener<String> listener) {
            super(Method.POST, URL, listener, null);

            map = new HashMap<>();
            map.put("userID", userID);
            // map.put("userPW", userPW); // 추후 비밀번호 확인하여 탈퇴하도록 추가 예정
        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return map;
        }
    }
}
