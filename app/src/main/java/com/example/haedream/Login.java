package com.example.haedream;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // 회원가입 버튼 누를 시 이동
        Button imageButton = (Button) findViewById(R.id.go_join);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Join.class);
                startActivity(intent);
            }
        });

        // 사용자가 입력한 값 받아옴.
        Button loginButton = (Button) findViewById(R.id.login);
        EditText user_id = findViewById(R.id.user_id);
        EditText user_pw = findViewById(R.id.user_pw);

        // 로그인 버튼 누를 시 LoginActivity.java에 값 전달. 로그인 성공시 메인 화면으로 이동
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사용자가 EditText(아이디, 비밀번호)에 입력한 값을 LoginActivity.java로 전달
                // 전달할 때 일반적인 방법X 안드로이드에서는 무조건 인텐트를 사용해서 값을 넘긴다. 값 여러 개 한 번에 가능.
                // 값 넣어서 전달할 때는 xxxExtra 사용함 (보내기 put, 받기 get)
                // LoginActivity.java에서는 인텐트에 담긴 user_id, user_pw 값 받아서 로그인여부 확인
                Intent loginintent = new Intent(getApplicationContext(), LoginActivity.class);
                loginintent.putExtra("user_id", user_id.getText().toString()); //값 넘길 때 String으로 변환하여 넘겨주자
                loginintent.putExtra("user_pw", user_pw.getText().toString());
                startActivity(loginintent); // 필수. 안넣으면 인텐트 안보내짐!

                // 사용자 아이디 인텐트 전달
                Intent userintent = new Intent(getApplicationContext(), MainSCR.class);
                userintent.putExtra("userid", user_id.getText().toString());
                startActivity(userintent);
            }
        });
    }
}