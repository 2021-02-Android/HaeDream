package com.example.haedream;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
    }
}