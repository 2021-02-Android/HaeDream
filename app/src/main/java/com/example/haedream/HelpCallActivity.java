package com.example.haedream;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class HelpCallActivity extends AppCompatActivity {
    private String info, location, details, point, period;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // 액티비티 시작시 처음으로 실행되는 생명주기
        // 인텐트 사용하고자 하거나, 액티비티로서 사용하려면 OnCreate 필수로 오버라이딩 해줘야한다.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helpcall);

        // HelpCall.java의 인텐트 받아옴
        Intent callIntent = getIntent();
        details = callIntent.getStringExtra("details");
        info = callIntent.getStringExtra("info");
        location = callIntent.getStringExtra("location");
        point = callIntent.getStringExtra("point");
        period = callIntent.getStringExtra("period");

        Log.d("[TAG] 요청 디버깅", "사용자 입력값: " + details + info + " " + location + point + period + "/[INTENT]");

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // 인코딩 문제때문에 한글 DB인 경우 로그인 불가
                    Log.d("[TAG] 요청 디버깅", "DB 연결 여부 " + response); // 디버깅 - php 파일 전부 보여줌
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success"); // 연결 성공시 success = true

                    if (success) { // 요청에 성공한 경우
                        String details = jsonObject.getString("details");
                        String info = jsonObject.getString("info");
                        String location = jsonObject.getString("location");
                        String point = jsonObject.getString("point");
                        String period = jsonObject.getString("period");

                        Toast.makeText(getApplicationContext(), "요청에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                        Log.d("[TAG] 요청 디버깅", "(요청 성공)");
                        Intent intent = new Intent(getApplicationContext(), SimhelpList.class);
                        startActivity(intent);
                    }

                    else { // 요청에 실패한 경우
                        Toast.makeText(getApplicationContext(), "요청에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        Log.d("[TAG] 요청 디버깅", "(요청 실패) 입력한 값 오류");
                        return;
                    }

                } catch (JSONException e) {
                    Log.d("[TAG] 요청 디버깅", "(데이터베이스 연결 실패) catch exception");
                    e.printStackTrace();
                }
            }
        };

        Toast.makeText(getApplicationContext(), "요청에 성공하였습니다.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), SimhelpList.class);
        startActivity(intent);
        // HelpCallRequest.java에 값 넘겨줌
        HelpCallRequest helpCallRequest = new HelpCallRequest(details, info, location, point, period, responseListener);
        RequestQueue queue = Volley.newRequestQueue(HelpCallActivity.this);
        queue.add(helpCallRequest);

        finish(); // 현재 액티비티 (심부름 요청 화면) 종료
    }
}
