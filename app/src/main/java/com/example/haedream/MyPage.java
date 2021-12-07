package com.example.haedream;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyPage extends AppCompatActivity {
    String user_id;
    TextView tvname, tvdept, tvuserid, tvbirth, tvinfo;
    CircleImageView iv_profile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        Intent userintent = getIntent();
        user_id = userintent.getStringExtra("user_id");
        Log.d("[TAG] 로그인 아이디 인텐트 전달", user_id);

        tvname = findViewById(R.id.user_name);
        tvdept = findViewById(R.id.user_depart);
        tvuserid = findViewById(R.id.user_id);
        tvbirth = findViewById(R.id.user_birth);
        tvinfo = findViewById(R.id.user_info);
        iv_profile = findViewById(R.id.iv);

        new MyPage.Select_MyPage_Request().execute();

        // 설정 버튼
        ImageButton set = (ImageButton) findViewById(R.id.setting);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Setting.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
                finish();
            }
        });

        // 뒤로가기
        ImageButton back = (ImageButton) findViewById(R.id.list_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainSCR.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
                finish();
            }
        });

        // 정보 수정 버튼
        Button changePW = (Button) findViewById(R.id.change_btn);
        changePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChangeInfo.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
                finish();
            }
        });
    }

    class Select_MyPage_Request extends AsyncTask<String, Integer, String> {
        String result = null;
        @Override
        protected String doInBackground(String... rurls) {
            try {
                URL url = new URL("https://idox23.cafe24.com/user_result.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                if(conn.getResponseCode()==HttpURLConnection.HTTP_OK) {
                    InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    result = stringBuilder.toString();
                } else {
                    result = "error";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        protected void onPostExecute(String _result) {
            try {
                JSONObject root = new JSONObject(_result);
                JSONArray results = new JSONArray(root.getString("results"));

                for (int index = 0; index < results.length(); index++) {
                    JSONObject Content = results.getJSONObject(index);
                    String userid = Content.getString("userid");
                    String profile = Content.getString("profile");
                    String name = Content.getString("name");
                    String dept = Content.getString("dept");
                    String birth = Content.getString("birth");
                    String intro = Content.getString("intro");

                    if (userid.equals(user_id)){
                        Glide.with(iv_profile).load("https://idox23.cafe24.com/"+profile).into(iv_profile);
                        tvname.setText(name);
                        tvdept.setText(dept);
                        tvuserid.setText(userid);
                        tvbirth.setText(birth);
                        tvinfo.setText(intro);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}