package com.example.haedream;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileChangeActivity extends AppCompatActivity {
    private String name, location, info, point, userid, imgPath, msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilechange);

        Intent callIntent = getIntent();
        imgPath = callIntent.getStringExtra("img");
        Log.d("imgPath 받아옴", imgPath);
        name = "imgname";
        msg = "imgmsg";

        Log.d("[TAG] 요청 디버깅", "사용자 입력값: " + imgPath + "/[INTENT]");

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("[TAG] 요청 디버깅", "DB 연결 여부 " + response);
                    JSONObject jsonObject = new JSONObject(response);

                } catch (JSONException e) {
                    Log.d("[TAG] 요청 디버깅", "(데이터베이스 연결 실패) catch exception");
                    e.printStackTrace();
                }
            }
        };

        ProfileChangeRequest myPageRequest = new ProfileChangeRequest(name, msg, imgPath, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ProfileChangeActivity.this);
        queue.add(myPageRequest);

        finish(); // 현재 액티비티 종료
    }
}
