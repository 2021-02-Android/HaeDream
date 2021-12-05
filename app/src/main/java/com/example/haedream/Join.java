package com.example.haedream;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Join extends AppCompatActivity {
    Spinner spinner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        spinner = findViewById(R.id.regi_depart);
        ArrayAdapter departAdapter = ArrayAdapter.createFromResource(this, R.array.departArray, android.R.layout.simple_spinner_dropdown_item);
        departAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(departAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        // 사용자가 입력한 값 받아옴.
        Button joinButton = (Button) findViewById(R.id.join);
        EditText regi_id = findViewById(R.id.regi_id);
        EditText regi_pw = findViewById(R.id.regi_pw);
        EditText regi_name = findViewById(R.id.user_name);
        EditText regi_first_num = findViewById(R.id.regi_first_num);
        EditText regi_second_num = findViewById(R.id.regi_second_num);
        EditText regi_birth = findViewById(R.id.regi_birth);

        // 회원가입 버튼 누를 시 JoinActivity.java에 값 전달. 회원가입 성공시 로그인 화면으로 이동
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사용자가 EditText(아이디, 비밀번호)에 입력한 값을 JoinActivity.java로 전달
                // 전달할 때 일반적인 방법X 안드로이드에서는 무조건 인텐트를 사용해서 값을 넘긴다. 값 여러 개 한 번에 가능.
                // 값 넣어서 전달할 때는 xxxExtra 사용함 (보내기 put, 받기 get)
                // JoinActivity.java에서는 회원가입을 위해 데이터베이스에 값 저장함

                // 사용자가 EditText에 입력한 값 String으로 변환하여 변수에 저장
                String id = regi_id.getText().toString();
                String pw = regi_pw.getText().toString();
                String name = regi_name.getText().toString();
                String fnum = regi_first_num.getText().toString();
                String snum = regi_second_num.getText().toString();
                String depart = spinner.getSelectedItem().toString();
                String birth = regi_birth.getText().toString();

                boolean fnumIsNum =  fnum.matches("-?\\d*(\\.\\d+)?");
                boolean snumIsNum =  snum.matches("-?\\d+(\\.\\d+)?");
                boolean birthIsNum =  birth.matches("-?\\d+(\\.\\d+)?");

                Log.d("[TAG] 회원가입 디버깅", "첫 번째 전화번호: " + fnum + fnumIsNum);
                Log.d("[TAG] 회원가입 디버깅", "두 번째 전화번호: " + snum + snumIsNum);

                // 인텐트 생성하기 전 값에 대한 유효성 검사 진행
                if (id.length() < 4 || id.length() > 15){
                    Toast.makeText(getApplicationContext(), "4-15자 이내의 ID를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if (pw.length() < 6 || pw.length() > 15){
                    Toast.makeText(getApplicationContext(), "6-15자 이내의 PW를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if (fnum.length() != 4 || snum.length() != 4){
                    Toast.makeText(getApplicationContext(), "전화번호를 올바르게 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if (!fnumIsNum || !snumIsNum){
                    Toast.makeText(getApplicationContext(), "전화번호 란에는 숫자만 입력이 가능합니다.", Toast.LENGTH_SHORT).show();
                }
                else if (!birthIsNum){
                    Toast.makeText(getApplicationContext(), "생년월일 란에는 숫자만 입력이 가능합니다.", Toast.LENGTH_SHORT).show();
                }
                else if (birth.length() != 8){
                    Toast.makeText(getApplicationContext(), "8자리 형식의 생년월일을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent joinintent = new Intent(getApplicationContext(), JoinActivity.class);
                    joinintent.putExtra("regi_id", id);
                    joinintent.putExtra("regi_pw", pw);
                    joinintent.putExtra("regi_name", name);
                    joinintent.putExtra("regi_phonenum", "010" + fnum + snum);
                    joinintent.putExtra("regi_depart", depart);
                    joinintent.putExtra("regi_birth", birth);
                    startActivity(joinintent); // 필수. 안넣으면 인텐트 안보내짐!
                    finish();
                }
            }
        });
    }
}