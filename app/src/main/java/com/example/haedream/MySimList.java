package com.example.haedream;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MySimList extends AppCompatActivity {
    ListView listView, acceptsimList;
    ArrayList<simhelpitem> arrayList1, arrayList2;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycall);

        Intent userintent = getIntent();
        user_id = userintent.getStringExtra("user_id");
        Log.d("[user_id 인텐트 받아옴]", user_id);

        listView = findViewById(R.id.callsimList);
        acceptsimList = findViewById(R.id.acceptsimList);
        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();

        new MySimList.Select_MySimList_Request().execute();

        // 리스트뷰에서 아이템 클릭 시
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {
                Intent it = new Intent(getApplicationContext(), MySimCall.class);
                it.putExtra("name", arrayList1.get(a_position).getName());
                it.putExtra("location", arrayList1.get(a_position).getLocation());
                it.putExtra("info", arrayList1.get(a_position).getInfo());
                it.putExtra("point", arrayList1.get(a_position).getPoint());
                it.putExtra("user_id", user_id);
                Log.d("[user_id 인텐트 전달]", user_id);
                startActivity(it);
                finish();
            }
        });

        acceptsimList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {
                Intent it = new Intent(getApplicationContext(), MySimAccept.class);
                it.putExtra("name", arrayList2.get(a_position).getName());
                it.putExtra("location", arrayList2.get(a_position).getLocation());
                it.putExtra("info", arrayList2.get(a_position).getInfo());
                it.putExtra("point", arrayList2.get(a_position).getPoint());
                it.putExtra("user_id", user_id);
                Log.d("[user_id 인텐트 전달]", user_id);
                startActivity(it);
                finish();
            }
        });

        // 설정 버튼
        ImageButton setButton = (ImageButton) findViewById(R.id.setting);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Setting.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
                finish();
            }
        });

        // 마이페이지 버튼 누를 시 마이페이지 화면 이동
        ImageButton myButton = (ImageButton) findViewById(R.id.mypage);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyPage.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
                finish();
            }
        });

    }

    class Select_MySimList_Request extends AsyncTask<String, Integer, String> {
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
                    String name = Content.getString("name");
                    String category = Content.getString("category");
                    String location = Content.getString("location");
                    String info = Content.getString("info");
                    String userid = Content.getString("userid");
                    String accepted = Content.getString("accepted");
                    String point = Content.getString("point");

                    simhelpitem item = new simhelpitem();

                    if(userid.equals(user_id)) {
                        item.setCategory(category);
                        item.setLocation(location);
                        item.setInfo(info);
                        item.setPoint(point);
                        item.setName(name);
                        arrayList1.add(item);
                    }
                    if(accepted.equals(user_id)){
                        item.setCategory(category);
                        item.setLocation(location);
                        item.setInfo(info);
                        item.setPoint(point);
                        item.setName(name);
                        arrayList2.add(item);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            MySimItemAdapter mySimItemAdapter1 = new MySimItemAdapter(MySimList.this, arrayList1);
            listView.setAdapter(mySimItemAdapter1);
            MySimItemAdapter mySimItemAdapter2 = new MySimItemAdapter(MySimList.this, arrayList2);
            acceptsimList.setAdapter(mySimItemAdapter2);
        }
    }
}
