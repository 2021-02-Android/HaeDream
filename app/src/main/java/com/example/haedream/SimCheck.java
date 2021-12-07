package com.example.haedream;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class SimCheck extends AppCompatActivity {
    TextView name, location, info, point;
    String it_userid;
    CircleImageView iv_profile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simcheck);

        Intent intent = getIntent();
        String it_name = intent.getStringExtra("name");
        String it_location = intent.getStringExtra("location");
        String it_info = intent.getStringExtra("info");
        String it_point = intent.getStringExtra("point");

        it_userid = intent.getStringExtra("user_id");
        Log.d("[user_id 인텐트 받아옴]", it_userid);

        name = findViewById(R.id.name);
        location = findViewById(R.id.sub);
        info = findViewById(R.id.substring);
        point = findViewById(R.id.point);
        iv_profile = findViewById(R.id.iv);

        name.setText(it_name);
        location.setText(it_location);
        info.setText(it_info);
        point.setText(it_point);

        new SimCheck.Select_SimCheck_Request().execute();

        // 확인 버튼 클릭 시
        Button cancelbtn = findViewById(R.id.okbtn);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SimhelpList.class);
                intent.putExtra("user_id", it_userid);
                Log.d("[user_id 인텐트 전달]", it_userid);
                startActivity(intent);
                finish();
            }
        });

    }

    class Select_SimCheck_Request extends AsyncTask<String, Integer, String> {
        String result = null;
        @Override
        protected String doInBackground(String... rurls) {
            try {
                URL url = new URL("https://idox23.cafe24.com/info_result.php");
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
                    String intro = Content.getString("intro");

                    if(userid.equals(it_userid)){
                        Glide.with(iv_profile).load("https://idox23.cafe24.com/"+profile).into(iv_profile);
                        Log.d("이미지 경로 : ", profile);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
