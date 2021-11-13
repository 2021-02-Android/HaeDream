package com.example.haedream;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

        spinner1 = findViewById(R.id.spinner1);//심부름 항목
        spinner2 = findViewById(R.id.spinner2); //포인트
        spinner3 = findViewById(R.id.spinner3);//시간

        ArrayAdapter helpAdapter = ArrayAdapter.createFromResource(this, R.array.drive_array, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter pointAdapter = ArrayAdapter.createFromResource(this, R.array.my_array, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter timeAdapter = ArrayAdapter.createFromResource(this, R.array.time_array, android.R.layout.simple_spinner_dropdown_item);

        helpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(helpAdapter); //어댑터에 연결해줍니다.
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
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
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }
}
