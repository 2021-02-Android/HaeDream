package com.example.haedream;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ConvertList extends AppCompatActivity {
    ListView listView;
    ArrayList<ConvertListItem> arrayList;
    String user_id, username;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convert_list);

        listView = findViewById(R.id.chat_view);
        arrayList = new ArrayList<>();
        /* 원리
         *  - firebase에서 Users에서 id에 해당되는 그아래 값 출력
         *  - 출력된 값은 채팅방 아이디임. 채팅방을 불러와서 정보 출력하기. 챗뷰 chat_view
         *       - 상대 이름 (name), 마지막 내용 (msg_content),이미지 image, 상태 (msg_state)
         *           상태는 '받음 : ' or '보냄 : ' 형식
         *
         * */

        Intent userintent = getIntent();
        user_id = userintent.getStringExtra("user_id");
        Log.d("[Convert user_id 인텐트 받아옴]", user_id);

        username = userintent.getStringExtra("username");
        Log.d("[Convert username 인텐트 받아옴]", username);

        //new ConvertList.Select_HelpList_Request().execute();
/*
        // 현재 사용자 ID에 해당하는 채팅방 이름 출력하기.
        databaseReference.child("Users").child(user_id).child("Room")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        // = value;
                        System.out.println("결과 : " + value);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
*/
        // 심부름 요청 버튼 클릭 시
        ImageButton simButton = (ImageButton) findViewById(R.id.callbutton);
        simButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HelpCall.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
                finish();
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
                finish();
            }
        });

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

        // 마이페이지 버튼
        ImageButton my = (ImageButton) findViewById(R.id.mypage);
        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyPage.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
                finish();
            }
        });

        // 리스트뷰에서 아이템 클릭 시
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {
                Intent it = new Intent(getApplicationContext(), SimAccept.class);
                it.putExtra("name", arrayList.get(a_position).getName());   // 상대 이름
                it.putExtra("id", arrayList.get(a_position).getId());   // 상대 아이디
                it.putExtra("user_id", user_id);        // 시스템 사용자 아이디
                Log.d("[user_id 인텐트 전달]", user_id);
                startActivity(it);
                finish();
            }
        });

    }

}























