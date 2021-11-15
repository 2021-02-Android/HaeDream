package com.example.haedream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HelpCall extends AppCompatActivity {
    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;

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


        // 사용자가 입력한 값 받아옴.
        ImageButton helpButton = (ImageButton) findViewById(R.id.helpBut);
        EditText info = findViewById(R.id.info);
        EditText location = findViewById(R.id.location);

        // helpCall 버튼 누를 시 HelpCallActivity.java에 인텐트를 넘겨줌
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callintent = new Intent(getApplicationContext(), HelpCallActivity.class);
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
