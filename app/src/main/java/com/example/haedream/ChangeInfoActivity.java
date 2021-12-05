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

public class ChangeInfoActivity extends AppCompatActivity {
    private String user_id, intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_userinfo);

        // SimAccept.java의 인텐트 받아옴
        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        intro = intent.getStringExtra("intro");

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("[TAG] 소개 수정 디버깅", "DB 연결 여부 " + response);
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success"); // 연결 성공시 success = true

                    if (success) { // 요청에 성공한 경우
                        Toast.makeText(getApplicationContext(), "소개 수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(getApplicationContext(), MyPage.class);
                        intent2.putExtra("user_id", user_id);
                        startActivity(intent2);
                        Log.d("[TAG] 소개 수정 디버깅", "(성공)");
                        return;
                    }

                    else { // 요청에 실패한 경우
                        Toast.makeText(getApplicationContext(), "소개 수정에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        Log.d("[TAG] 소개 수정 디버깅", "(실패)");
                        return;
                    }

                } catch (JSONException e) {
                    Log.d("[TAG] 소개 수정 디버깅", "(데이터베이스 연결 실패) catch exception");
                    e.printStackTrace();
                }
            }
        };

        ChangeInfoRequest changeInfoRequest = new ChangeInfoRequest(user_id, intro, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ChangeInfoActivity.this);
        queue.add(changeInfoRequest);

        finish();
    }
}
