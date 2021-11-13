package com.example.haedream;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private String user_id, user_pw;
    private boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //액티비티 시작시 처음으로 실행되는 생명주기
        Log.d("[TAG] 로그인 디버깅", "LoginActivity 실행");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Intent loginIntent = getIntent();
        user_id = loginIntent.getStringExtra("user_id");
        user_pw = loginIntent.getStringExtra("user_pw");
        Log.d("[TAG] 로그인 디버깅", "인텐트 받아옴: " + user_id + " " + user_pw + "/");

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // TODO : 인코딩 문제때문에 한글 DB인 경우 로그인 불가
                    //System.out.println("hongchul" + response);
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) { // 로그인에 성공한 경우
                        String userID = jsonObject.getString("userID");
                        String userPW = jsonObject.getString("userPW");

                        Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                        Log.d("[TAG] 로그인 디버깅", "로그인 성공");
                        isLogin = true;
                    } else { // 로그인에 실패한 경우
                        Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        Log.d("[TAG] 로그인 디버깅", "로그인 실패");
                        return;
                    }
                } catch (JSONException e) {
                    Log.d("[TAG] 로그인 디버깅", "catch: 연결 실패");
                    e.printStackTrace();
                }
            }
        };
        LoginRequest loginRequest = new LoginRequest(user_id, user_pw, responseListener);
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        queue.add(loginRequest);

        if (isLogin) {
            Intent intent = new Intent(getApplicationContext(), MainSCR.class);
            startActivity(intent);
        } else {
            Log.d("[TAG] 로그인 디버깅", "로그인 실패2");
        }
    }
}