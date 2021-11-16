package com.example.haedream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HelpCall extends AppCompatActivity {
    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;
    int count = 1;
    String str = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helpcall);

        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        spinner1 = findViewById(R.id.spin_details);// 심부름 항목
        spinner2 = findViewById(R.id.spin_point); // 포인트
        spinner3 = findViewById(R.id.spin_period);// 시간

        ArrayAdapter helpAdapter = ArrayAdapter.createFromResource(this, R.array.drive_array, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter pointAdapter = ArrayAdapter.createFromResource(this, R.array.my_array, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter timeAdapter = ArrayAdapter.createFromResource(this, R.array.time_array, android.R.layout.simple_spinner_dropdown_item);

        helpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(helpAdapter); //어댑터에 연결해줍니다.
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        pointAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(pointAdapter); //어댑터에 연결
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(timeAdapter); //어댑터에 연결해줍니다.
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ImageButton baebtn = findViewById(R.id.driving);
        baebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if (count%2==0) { // 배달 선택
                    str = "bae";
                    baebtn.setImageResource(R.drawable.bae2);
                }
                else { // 배달 선택 안 함
                    baebtn.setImageResource(R.drawable.bae1);
                    str = null;
                }
            }
        });

        ImageButton movebtn = findViewById(R.id.move);
        movebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if (count%2==0) {
                    str = "move";
                    movebtn.setImageResource(R.drawable.move2);
                }
                else {
                    movebtn.setImageResource(R.drawable.move1);
                    str = null;
                }
            }
        });

        ImageButton cleanbtn = findViewById(R.id.clean);
        cleanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if (count%2==0) {
                    str = "clean";
                    cleanbtn.setImageResource(R.drawable.clean2);
                }
                else {
                    cleanbtn.setImageResource(R.drawable.clean1);
                    str = null;
                }
            }
        });

        ImageButton homebtn = findViewById(R.id.homework);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if (count%2==0) {
                    str = "home";
                    homebtn.setImageResource(R.drawable.home2);
                }
                else {
                    homebtn.setImageResource(R.drawable.home1);
                    str = null;
                }
            }
        });

        ImageButton insectbtn = findViewById(R.id.insect);
        insectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if (count%2==0) {
                    str = "insect";
                    insectbtn.setImageResource(R.drawable.insect2);
                }
                else {
                    insectbtn.setImageResource(R.drawable.insect1);
                    str = null;
                }
            }
        });

        ImageButton anybtn = findViewById(R.id.anything);
        anybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if (count%2==0) {
                    str = "anything";
                    anybtn.setImageResource(R.drawable.anything2);
                }
                else {
                    anybtn.setImageResource(R.drawable.anything1);
                    str = null;
                }
            }
        });

        // 사용자가 입력한 값 받아옴.
        ImageButton helpButton = (ImageButton) findViewById(R.id.helpBut);
        EditText info = findViewById(R.id.info);
        EditText location = findViewById(R.id.location);

        // helpCall 버튼 누를 시 HelpCallActivity.java에 인텐트를 넘겨줌
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callintent = new Intent(getApplicationContext(), HelpCallActivity.class);
                callintent.putExtra("category", str);
                callintent.putExtra("details", spinner1.getSelectedItem().toString()); //값 넘길 때 String으로 변환하여 넘겨주자
                callintent.putExtra("info", info.getText().toString());
                callintent.putExtra("location", location.getText().toString());
                callintent.putExtra("point", spinner2.getSelectedItem().toString());
                callintent.putExtra("period", spinner3.getSelectedItem().toString());
                startActivity(callintent);
            }
        });
    }
}
