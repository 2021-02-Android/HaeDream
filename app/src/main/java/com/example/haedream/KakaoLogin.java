package com.example.haedream;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class KakaoLogin extends AppCompatActivity {
    Spinner spinner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent userintent = getIntent();
        String id = userintent.getStringExtra("user_id");
        String name = userintent.getStringExtra("user_name");

        id_overlap(id);  // ID 중복 검사

        super.onCreate(savedInstanceState);
        setContentView(R.layout.kakao_join);

        spinner = findViewById(R.id.kakao_regi_depart);
        ArrayAdapter departAdapter = ArrayAdapter.createFromResource(this, R.array.departArray, android.R.layout.simple_spinner_dropdown_item);
        departAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(departAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // 사용자가 입력한 값 받아옴.
        Button joinButton = (Button) findViewById(R.id.kakao_join);
        EditText regi_pw = findViewById(R.id.kakao_regi_pw);
        EditText regi_first_num = findViewById(R.id.kakao_regi_first_num);
        EditText regi_second_num = findViewById(R.id.kakao_regi_second_num);
        EditText regi_birth = findViewById(R.id.kakao_regi_birth);

        // 회원가입 버튼 누를 시 JoinActivity.java에 값 전달. 회원가입 성공시 로그인 화면으로 이동
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사용자가 EditText(아이디, 비밀번호)에 입력한 값을 JoinActivity.java로 전달
                // 전달할 때 일반적인 방법X 안드로이드에서는 무조건 인텐트를 사용해서 값을 넘긴다. 값 여러 개 한 번에 가능.
                // 값 넣어서 전달할 때는 xxxExtra 사용함 (보내기 put, 받기 get)
                // JoinActivity.java에서는 회원가입을 위해 데이터베이스에 값 저장함

                // 사용자가 EditText에 입력한 값 String으로 변환하여 변수에 저장
                String pw = regi_pw.getText().toString();
                String fnum = regi_first_num.getText().toString();
                String snum = regi_second_num.getText().toString();
                String depart = spinner.getSelectedItem().toString();
                String birth = regi_birth.getText().toString();

                boolean fnumIsNum = fnum.matches("-?\\d*(\\.\\d+)?");
                boolean snumIsNum = snum.matches("-?\\d+(\\.\\d+)?");
                boolean birthIsNum = birth.matches("-?\\d+(\\.\\d+)?");

                Log.d("[TAG] 회원가입 디버깅", "첫 번째 전화번호: " + fnum + fnumIsNum);
                Log.d("[TAG] 회원가입 디버깅", "두 번째 전화번호: " + snum + snumIsNum);

                // 인텐트 생성하기 전 값에 대한 유효성 검사 진행
                if (pw.length() < 6 || pw.length() > 15) {
                    Toast.makeText(getApplicationContext(), "6-15자 이내의 PW를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (fnum.length() != 4 || snum.length() != 4) {
                    Toast.makeText(getApplicationContext(), "전화번호를 올바르게 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (!fnumIsNum || !snumIsNum) {
                    Toast.makeText(getApplicationContext(), "전화번호 란에는 숫자만 입력이 가능합니다.", Toast.LENGTH_SHORT).show();
                } else if (!birthIsNum) {
                    Toast.makeText(getApplicationContext(), "생년월일 란에는 숫자만 입력이 가능합니다.", Toast.LENGTH_SHORT).show();
                } else if (birth.length() != 8) {
                    Toast.makeText(getApplicationContext(), "8자리 형식의 생년월일을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent joinintent = new Intent(getApplicationContext(), JoinActivity.class);
                    joinintent.putExtra("regi_id", id);
                    joinintent.putExtra("regi_pw", pw);
                    joinintent.putExtra("regi_name", name);
                    joinintent.putExtra("regi_phonenum", "010" + fnum + snum);
                    joinintent.putExtra("regi_depart", depart);
                    joinintent.putExtra("regi_birth", birth);
                    startActivity(joinintent); // 필수. 안넣으면 인텐트 안보내짐!
                    finish();
                }
            }
        });
    }

    public void id_overlap(String id) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // 인코딩 문제때문에 한글 DB인 경우 로그인 불가
                    Log.d("[TAG] 카카오 회원가입", "회원가입 가능 여부: " + response); // 디버깅 - php 파일 전부 보여줌
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success"); // 회원가입 성공시 success = true

                    if (success) { // 회원가입에 성공한 경우
                        Toast.makeText(getApplicationContext(), "회원가입을 진행합니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    else { // 회원가입에 실패한 경우
                        Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainSCR.class);
                        intent.putExtra("user_id", id);
                        startActivity(intent);
                        finish();
                        return;
                    }

                } catch (JSONException e) { // 데이터베이스 연결 실패한 경우
                    Log.d("[TAG] 회원가입 디버깅", "(데이터베이스 연결 실패) catch exception");
                    e.printStackTrace();
                }
            }
        };
        JoinRequest joinRequest = new JoinRequest(id, responseListener);
        RequestQueue queue = Volley.newRequestQueue(KakaoLogin.this);
        queue.add(joinRequest);
    }

    public class JoinRequest extends StringRequest {
        // 서버 URL 설정 ( PHP 파일 연동 - Database 바로 접근 불가, php 중간 매체로 파싱하여 사용 )
        final static private String URL = "http://idox23.cafe24.com/Join.php";
        private Map<String, String> map;

        public JoinRequest(String userID, Response.Listener<String> listener) {
            super(Method.POST, URL, listener, null);

            map = new HashMap<>();
            map.put("userID", userID);
        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return map;
        }
    }
}