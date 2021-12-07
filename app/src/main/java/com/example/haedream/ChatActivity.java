package com.example.haedream;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class ChatActivity extends AppCompatActivity {
    String user_id; // 시스템 사용자 id
    String other_id; // 상대방 id
    String other_name, user_name;

    private String CHAT_NAME;   // 채팅방 이름

    private ListView listView; // 채팅 화면
    private EditText chat_edit; // 메세지 입력
    //private Button chat_send;   // 전송 버튼
    private TextView textView;  // 채팅방 맨 위 글자(상대 이름 표시)
    private ImageView imageView; // 채팅방 맨 위 상대 이미지 띄우기

    ArrayList<MessageItem> messageItems = new ArrayList<>();
    ChatAdapter adapter;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    // xml=@id : 보내기 버튼 send, 입력 text, view 화면 listview
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatting_room);

        // 시스템 사용자 ID 받아오기
        Intent user_intent = getIntent();
        user_id = user_intent.getStringExtra("user_id");
        Log.d("[ChatActAivity user_id 인텐트 받아옴]", user_id);

        // 상대 ID 받아오기
        other_id = user_intent.getStringExtra("other_id");
        Log.d("[ChatActivity other_id 인텐트 받아옴]", other_id);

        // 상대 이름 받음
        Intent info = getIntent();
        other_name = info.getStringExtra("other_user_name");
        Log.d("[ChatActivity name 인텐트 받아옴]", other_name);

        user_name = info.getStringExtra("user_name");
        Log.d("[ChatActivity user_name 인텐트 받아옴]", user_name);

        // 상단바 채팅방 이름 상대이름으로
        textView = (TextView) findViewById(R.id.othername);
        textView.setText(other_name);

        // imageView = (ImageView) findViewById(R.id.otherimage); // 이미지
        // imageView.setText(image);
        // CHAT_NAME = name;

        listView = (ListView) findViewById(R.id.listview);
        chat_edit = (EditText) findViewById(R.id.text);

        adapter = new ChatAdapter(messageItems, getLayoutInflater());
        // ChatAdapter로 사용자 이름 넘겨줌
        adapter.user_name = user_name;
        listView.setAdapter(adapter);

        //Firebase DB 관리 객체, chat 노드 참조 객체 얻어오기
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("chat");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
                // 새로 추가된 데이터 값 : MessageItem 객체 가져오기
                MessageItem messageItem = snapshot.getValue(MessageItem.class);

                // 새로운 메세지를 리스트뷰에 추가하기 위해 ArrayList에 추가
                messageItems.add(messageItem);

                // 리스트 뷰 갱신
                adapter.notifyDataSetChanged();

                //리스트뷰의 마지막 위치로 스크롤 위치 이동
                listView.setSelection(messageItems.size()-1);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // 전송
        Button chat_send = findViewById(R.id.send);
        chat_send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //메세지 작성 시간 문자열로
                String message = chat_edit.getText().toString();

                //현재 시간을 가지고 있는 객체
                Calendar calendar = Calendar.getInstance();
                String time = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);

                //firebase DB에 저장할 값(MessageItem객체) 설정
                // MessageItem messageItem= new MessageItem(name ,text, time, pofile);
                MessageItem messageItem= new MessageItem(other_name, message, time, user_id, other_id, user_name);
                // 위처럼 해버리면 매번 메시지 보낼때마다 user_id, other_id 다 들어감...

                //'chat'노드에 내용 저장
                databaseReference.push().setValue(messageItem);

                //EditText에 있는 글씨 지우기
                chat_edit.setText("");

                //소프트키패드를 안보이도록
                InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
            }
        });

        // 뒤로가기 버튼
        ImageButton back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Intro_List.class);
                intent.putExtra("user_id", user_id);
                intent.putExtra("user_name", user_name);
                startActivity(intent);
                finish();
            }
        });


    }

}
