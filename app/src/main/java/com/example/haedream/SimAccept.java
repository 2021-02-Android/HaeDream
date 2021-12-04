package com.example.haedream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SimAccept extends AppCompatActivity {
    TextView name, location, info, point;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simaccept);

         Intent intent = getIntent();
         String it_userid = intent.getStringExtra("user_id");
         String it_location = intent.getStringExtra("location");
         String it_info = intent.getStringExtra("info");
         String it_point = intent.getStringExtra("point");

         name = findViewById(R.id.name);
         location = findViewById(R.id.sub);
         info = findViewById(R.id.substring);
         point = findViewById(R.id.point);

         name.setText(it_userid);
         location.setText(it_location);
         info.setText(it_info);
         point.setText(it_point);

         Button cancelbtn = findViewById(R.id.cancelbtn);
         cancelbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                finish();
            }
         });
    }
}
