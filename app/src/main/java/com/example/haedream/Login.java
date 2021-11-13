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

        // 로그인 버튼 누를 시 메인 화면으로 이동
        Button loginButton = (Button) findViewById(R.id.login);
        EditText user_id = findViewById(R.id.user_id);
        EditText user_pw = findViewById(R.id.user_pw);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginintent = new Intent(getApplicationContext(), LoginActivity.class);
                loginintent.putExtra("user_id", user_id.getText().toString());
                loginintent.putExtra("user_pw", user_pw.getText().toString());
                startActivity(loginintent);
            }
        });
    }
}