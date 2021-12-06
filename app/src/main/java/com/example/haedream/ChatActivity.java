package com.example.haedream;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    String user_id; // 시스템 사용자 id

    private String CHAT_NAME;
    private String USER_NAME;

    private ListView chat_view;
    private EditText chat_edit; // 메세지 입력
    private Button chat_send; // 전송 버튼

    private String otherID; // 상대 id

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

// xml id 보내기 버튼 send. 입력 text. veiw화면 listview
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatting_room);

        // 시스템 사용자 ID 받아오기
        Intent userintent = getIntent();
        user_id = userintent.getStringExtra("user_id");
        Log.d("[user_id 인텐트 받아옴]", user_id);

        chat_view = (ListView) findViewById(R.id.chat_view);
        chat_edit = (EditText) findViewById(R.id.text);
        chat_send = (Button) findViewById(R.id.send);

        // 로그인 화면에서 받아온 채팅방 이름, 유저 이름 저장
        Intent intent = getIntent();
        CHAT_NAME = intent.getStringExtra("chatName");
        USER_NAME = intent.getStringExtra("userName");

        // 채팅 방 입장
        openChat(CHAT_NAME);

        // 메시지 전송 버튼에 대한 클릭 리스너 지정
        chat_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chat_edit.getText().toString().equals(""))
                    return;

                ChatItem chat = new ChatItem(USER_NAME, chat_edit.getText().toString()); // 데이터를 묶는다.
                databaseReference.child("chat").child(CHAT_NAME).push().setValue(chat); // 데이터 푸쉬
                chat_edit.setText(""); //입력창 초기화
            }
        });
    }

    private void openChat(String chatName){

    }

        /* //수정 전
        otherID = getIntent().getStringExtra("user_ID");
        button = (Button) findViewById(R.id.send);
        editText = (EditText) findViewById(R.id.text);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ChatAdapter chatAdapter = new ChatAdapter();
                chatAdapter.userID = user_id;   // 시스템 사용자 정보를 챗정보에 넣어줌
                chatAdapter.otherID = otherID;

                // 데이터 베이스에 채팅방 개설 ( 채팅방 이름은 상대 아이디로 )
                FirebaseDatabase.getInstance().getReference().child(user_id).push().setValue(chatAdapter);
            }
        });*/

}
