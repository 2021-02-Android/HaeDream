package com.example.haedream;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePW extends AppCompatActivity {

    EditText nowPW, newPW1, newPW2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pw);

        nowPW = findViewById(R.id.nowPW);
        newPW1 = findViewById(R.id.newPW);
        newPW2 = findViewById(R.id.check_newPW);

        Intent userintent = getIntent();
        String user_id = userintent.getStringExtra("user_id");

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
        ImageButton back = (ImageButton) findViewById(R.id.list_btn);
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
                Intent intent = new Intent(getApplicationContext(), MyPage.class);
                startActivity(intent);
            }
        });

        // 비번 변경 버튼
        Button changePW = (Button) findViewById(R.id.change_btn);
        changePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 지금 비번 id : nowPW      새 비번 : newPW   비번 확인 : check_newPW
                String str_nowPW = nowPW.getText().toString();  // 현재 비밀번호 Text 받아옴
                String str_newPW1 = newPW1.getText().toString();  // 변경할 비밀번호 Text 받아옴
                String str_newPW2 = newPW2.getText().toString();  // 변경할 비밀번호(재입력) Text 받아옴

                if (str_newPW1.equals(str_newPW2)) {  // 비밀번호, 비밀번호 확인 일치하는 경우
                    if (str_newPW1.length() < 6 || str_newPW1.length() > 15)  // 비밀번호 글자수 기준 미달시
                        Toast.makeText(getApplicationContext(), "6-15자 이내의 PW를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    else pw_overlap(user_id, str_nowPW, str_newPW1);  // 모든 조건 충족, 비밀번호 변경 함수 호출
                }
                else Toast.makeText(getApplicationContext(), "비밀번호 확인이 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void pw_overlap(String id, String pw, String newPW) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success"); // 연결 성공시 success = true

                    if (success) { // 로그인에 성공한 경우
                        Log.d("[TAG] 비밀번호 변경", "현재 비밀번호 일치");
                        pw_change(id, newPW);
                    }
                    else { // 로그인에 실패한 경우
                        Toast.makeText(getApplicationContext(), "현재 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                        Log.d("[TAG] 비밀번호 변경", "현재 비밀번호 불일치");
                        return;
                    }
                } catch (JSONException e) { // 데이터베이스 연결 실패한 경우
                    Log.d("[TAG] 비밀번호 변경", "(데이터베이스 연결 실패) catch exception");
                    e.printStackTrace();
                }
            }
        };
        ChangePW.CheckPWRequest checkPWRequest = new ChangePW.CheckPWRequest(id, pw, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ChangePW.this);
        queue.add(checkPWRequest);
    }

    public class CheckPWRequest extends StringRequest {
        // 서버 URL 설정 ( PHP 파일 연동 - Database 바로 접근 불가, php 중간 매체로 파싱하여 사용 )
        final static private String URL = "http://idox23.cafe24.com/Login.php";
        private Map<String, String> map;

        public CheckPWRequest(String userID, String userPW, Response.Listener<String> listener) {
            super(Method.POST, URL, listener, null);

            map = new HashMap<>();
            map.put("userID", userID);
            map.put("userPW", userPW);
        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return map;
        }
    }

    public void pw_change(String id, String pw) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "비밀번호 변경이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                Log.d("[TAG] 비밀번호 변경", "비밀번호 변경 완료");
                finish();
            }
        };
        ChangePW.ChangePWRequest changePWRequest = new ChangePW.ChangePWRequest(id, pw, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ChangePW.this);
        queue.add(changePWRequest);
    }

    public class ChangePWRequest extends StringRequest {
        // 서버 URL 설정 ( PHP 파일 연동 - Database 바로 접근 불가, php 중간 매체로 파싱하여 사용 )
        final static private String URL = "http://idox23.cafe24.com/pw_update.php";
        private Map<String, String> map;

        public ChangePWRequest(String userID, String userPW, Response.Listener<String> listener) {
            super(Method.POST, URL, listener, null);

            map = new HashMap<>();
            map.put("userID", userID);
            map.put("userPW", userPW);
        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return map;
        }
    }
}
