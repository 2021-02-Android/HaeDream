package com.example.haedream;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SimhelpList extends AppCompatActivity {
    ListView listView;
    ArrayList<simhelpitem> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helplist);

        String user_id;
        Intent userintent = getIntent();
        user_id = userintent.getStringExtra("user_id");
        Log.d("[user_id 인텐트 받아옴]", user_id);

        listView = findViewById(R.id.listview);
        arrayList = new ArrayList<>();

        new SimhelpList.Select_HelpList_Request().execute();

        // 심부름 요청 버튼 클릭 시
        ImageButton simButton = (ImageButton) findViewById(R.id.callbutton);
        simButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HelpCall.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });

        // 소개 버튼 클릭 시
        ImageButton go_intro = findViewById(R.id.go_intro);
        go_intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Intro_List.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });

        // 말풍선 버튼(내 심부름 리스트 확인) 클릭 시
        ImageButton mysim_btn = findViewById(R.id.mysim_btn);
        mysim_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MySimList.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });

        SimhelpItemAdapter helpListViewAdapter = new SimhelpItemAdapter(SimhelpList.this, arrayList);
        // 리스트뷰에서 아이템 클릭 시
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {
                Intent it = new Intent(getApplicationContext(), SimAccept.class);
                it.putExtra("name", arrayList.get(a_position).getName());
                it.putExtra("location", arrayList.get(a_position).getLocation());
                it.putExtra("info", arrayList.get(a_position).getInfo());
                it.putExtra("point", arrayList.get(a_position).getPoint());
                it.putExtra("user_id", user_id);
                Log.d("[user_id 인텐트 전달]", user_id);
                startActivity(it);
            }
        });
    }

    class Select_HelpList_Request extends AsyncTask<String, Integer, String> {
        String result = null;
        @Override
        protected String doInBackground(String... rurls) {
            try {
                URL url = new URL("https://idox23.cafe24.com/task_result.php");
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
                    String category = Content.getString("category");
                    String details = Content.getString("details");
                    String location = Content.getString("location");
                    String info = Content.getString("info");
                    String point = Content.getString("point");
                    String userid = Content.getString("userid");

                    simhelpitem item = new simhelpitem();

                    item.setCategory(category);
                    item.setDetails(details);
                    item.setLocation(location);
                    item.setInfo(info);
                    item.setPoint(point);
                    item.setName(userid);
                    arrayList.add(item);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            SimhelpItemAdapter helpListViewAdapter = new SimhelpItemAdapter(SimhelpList.this, arrayList);
            listView.setAdapter(helpListViewAdapter);
        }
    }

}