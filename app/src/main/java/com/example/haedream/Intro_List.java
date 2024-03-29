package com.example.haedream;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

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

public class Intro_List extends AppCompatActivity {
    ListView listView;
    ArrayList<IntroListItem> arrayList;
    String user_id;
    String username, otherusername;
    CircleImageView iv_profile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce_list);

        Intent userintent = getIntent();
        user_id = userintent.getStringExtra("user_id");
        Log.d("[user_id 인텐트 받아옴]", user_id);

        listView = findViewById(R.id.intro_listview);
        arrayList = new ArrayList<>();

        new Select_HelpList_Request().execute();

        // 리스트뷰에서 아이템 클릭 시
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {
                Intent it = new Intent(getApplicationContext(), IntroInfo.class);
                it.putExtra("profile",arrayList.get(a_position).getProfile());
                it.putExtra("name", arrayList.get(a_position).getName());
                it.putExtra("depart", arrayList.get(a_position).getDepart());
                it.putExtra("intro", arrayList.get(a_position).getIntro());
                it.putExtra("other_id", arrayList.get(a_position).getOther_id());
                it.putExtra("otherusername", otherusername);
                it.putExtra("username", username);
                Log.d("[user_name 인텐트 전달]", username);
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
            }
        });

        // 심부름 버튼 누를 시 이동
        ImageButton simbu = (ImageButton) findViewById(R.id.go_sim);
        simbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SimhelpList.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
                finish();
            }
        });
    }

    class Select_HelpList_Request extends AsyncTask<String, Integer, String> {
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
                    String profile = Content.getString("profile");
                    String userid = Content.getString("userid");
                    String name = Content.getString("name");
                    String depart = Content.getString("dept");
                    String intro = Content.getString("intro");
                    String other_id = Content.getString("userid");

                    IntroListItem item = new IntroListItem();

                    // 로그인한 사용자말고 다른 사람들만 리스트에 추가
                    if (!userid.equals(user_id)){
                        otherusername = name;

                        item.setOther_id(other_id);
                        item.setName(name);
                        item.setDepart(depart);
                        if(!intro.equals("null"))  // 소개가 null이 아닌 경우
                            item.setIntro(intro);
                        else item.setIntro("등록된 소개 글이 없습니다.");
                        item.setProfile(profile);
                        arrayList.add(item);
                    }

                    // 로그인한 사용자 이름 username 변수에 넣음
                    else if (userid.equals(user_id)){
                        username = name;
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            IntroListAdapter introListAdapter = new IntroListAdapter(Intro_List.this, arrayList);
            listView.setAdapter(introListAdapter);
        }
    }
}