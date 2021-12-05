package com.example.haedream;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MySimAccept extends AppCompatActivity {
    TextView name, location, info, point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mysimaccepted);

        Intent intent = getIntent();
        String it_name = intent.getStringExtra("name");
        String it_location = intent.getStringExtra("location");
        String it_info = intent.getStringExtra("info");
        String it_point = intent.getStringExtra("point");

        String it_userid = intent.getStringExtra("user_id");
        Log.d("[user_id 인텐트 받아옴]", it_userid);

        name = findViewById(R.id.name);
        location = findViewById(R.id.sub);
        info = findViewById(R.id.substring);
        point = findViewById(R.id.point);

        name.setText(it_name);
        location.setText(it_location);
        info.setText(it_info);
        point.setText(it_point);

        // 확인 버튼 클릭 시
        Button okbtn = findViewById(R.id.okbtn);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 수락 취소 버튼 클릭 시
        Button cancelbtn = findViewById(R.id.cancelbtn);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), MySimAcceptCancelActivity.class);
                it.putExtra("name", name.getText().toString());
                it.putExtra("location", location.getText().toString());
                it.putExtra("info", info.getText().toString());
                it.putExtra("point", point.getText().toString());
                it.putExtra("userid", it_userid);
                it.putExtra("accepted", "none");
                Log.d("[user_id 인텐트 전달]", it_userid);
                startActivity(it);
                finish();
            }
        });

        // 심부름 완료 버튼 클릭 시
        Button finishbtn = findViewById(R.id.finishbtn);
        finishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), MySimAcceptFinishActivity.class);
                it.putExtra("name", name.getText().toString());
                it.putExtra("location", location.getText().toString());
                it.putExtra("info", info.getText().toString());
                it.putExtra("point", point.getText().toString());
                it.putExtra("userid", it_userid);
                Log.d("[user_id 인텐트 전달]", it_userid);
                startActivity(it);
                finish();
            }
        });

    }
}
