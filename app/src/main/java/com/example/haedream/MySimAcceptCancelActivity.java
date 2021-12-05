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

public class MySimAcceptCancelActivity extends AppCompatActivity {
    private String name, location, info, point, accepted, userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mysimaccepted);

        Intent callIntent = getIntent();
        name = callIntent.getStringExtra("name");
        location = callIntent.getStringExtra("location");
        info = callIntent.getStringExtra("info");
        point = callIntent.getStringExtra("point");
        accepted = callIntent.getStringExtra("accepted");
        userid = callIntent.getStringExtra("userid");

        Log.d("[TAG] 요청 디버깅", "사용자 입력값: " + name + info + location + point + accepted + "/[INTENT]");

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("[TAG] 요청 디버깅", "DB 연결 여부 " + response);
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success"); // 연결 성공시 success = true

                    if (success) { // 요청에 성공한 경우
                        Toast.makeText(getApplicationContext(), "요청에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                        Log.d("[TAG] 요청 디버깅", "(요청 성공)");
                        Intent intent = new Intent(getApplicationContext(), SimhelpList.class);
                        intent.putExtra("user_id", userid);
                        Log.d("[user_id 인텐트 전달]", userid);
                        startActivity(intent);
                        finish();
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

        SimAcceptRequest simAcceptRequest = new SimAcceptRequest(name, info, location, point, accepted, responseListener);
        RequestQueue queue = Volley.newRequestQueue(MySimAcceptCancelActivity.this);
        queue.add(simAcceptRequest);

        finish();
    }
}
