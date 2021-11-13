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
    private EditText user_id, user_pw;
    private Button btn_login, btn_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //액티비티 시작시 처음으로 실행되는 생명주기
        Log.d("[TAG] 로그인 디버깅", "LoginActivity 실행");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        user_id = findViewById(R.id.user_id);
        user_pw = findViewById(R.id.user_pw);
        btn_join = findViewById(R.id.go_join);
        btn_login = findViewById(R.id.login);

        // 회원가입 버튼 클릭 시 수행
        /*btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });*/

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 사용자가 EditText에 입력한 값을 가져옴
                String userID = user_id.getText().toString(); //ID
                String userPW = user_pw.getText().toString(); //PW

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // TODO : 인코딩 문제때문에 한글 DB인 경우 로그인 불가
                            System.out.println("hongchul" + response);
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 로그인에 성공한 경우
                                String userID = jsonObject.getString("userID");
                                String userPW = jsonObject.getString("userPW");

                                Toast.makeText(getApplicationContext(),"로그인에 성공하였습니다.",Toast.LENGTH_SHORT).show();
                                Log.d("[TAG] 로그인 디버깅", "로그인 성공");
                                //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                //intent.putExtra("userID", userID);
                                //intent.putExtra("userPW", userPW);
                                //startActivity(intent);
                            } else { // 로그인에 실패한 경우
                                Toast.makeText(getApplicationContext(),"로그인에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                Log.d("[TAG] 로그인 디버깅", "로그인 실패");
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPW, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });


    }
}