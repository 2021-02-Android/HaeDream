package com.example.haedream;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
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
    int count_bae = 0, count_clean = 0, count_ins = 0, count_move = 0, count_home = 0, count_any = 0;
    int[] count_arr = {0, 0, 0, 0, 0, 0};
    boolean checked = false;
    String str;


    /*void func(int count, int[] count_arr, ImageButton btn, ImageButton[] arr) {
        for(int i=0; i<6; i++) {
            if(count_arr[i]%2 == 1) {
                if(count%2==1) {
                    if(btn.equals(arr[i]) || count==count_arr[i]){
                        btn.setImageResource(Integer.parseInt("R.drawable."+btn+"2"));
                        Log.d("[TAG]", String.valueOf(btn));
                        // str = btn;
                    } else {
                        arr[i].setImageResource(Integer.parseInt("R.drawable."+btn+"1"));
                        count_arr[i] = 0;
                        Log.d("[TAG]", String.valueOf(count_arr[i]));
                    }
                }
            }
            else {
                if(count%2==1){
                    btn.setImageResource(Integer.parseInt("R.drawable."+btn+"2"));
                    // str = btn;
                }
                else {
                    btn.setImageResource(Integer.parseInt("R.drawable."+btn+"1"));
                    // str = null;
                }
               // Log.d("[TAG]", String.valueOf(btn));
            }
        }
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helpcall);

        String user_id;
        Intent userintent = getIntent();
        user_id = userintent.getStringExtra("user_id");
        Log.d("[user_id 인텐트 받아옴]", user_id);

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
        ImageButton helpButton = findViewById(R.id.helpBut);
        EditText info = findViewById(R.id.info);
        EditText location = findViewById(R.id.location);
        ImageButton movebtn = findViewById(R.id.move);
        ImageButton baebtn = findViewById(R.id.driving);
        ImageButton cleanbtn = findViewById(R.id.clean);
        ImageButton homebtn = findViewById(R.id.homework);
        ImageButton insectbtn = findViewById(R.id.insect);
        ImageButton anybtn = findViewById(R.id.anything);
        ImageButton backbtn = findViewById(R.id.backhome);


        // 뒤로가기 버튼 클릭 시
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        baebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_bae++;
                // 다른 카테고리 선택되어있을 때
                if (count_clean%2 == 1 || count_any%2 == 1 || count_ins%2 == 1 || count_home%2 == 1 || count_move%2 == 1) { // 다른 버튼 선택 시
                    if (count_bae%2 == 1) { // 배달 선택하면 다른 카테고리 값 초기화
                        movebtn.setImageResource(R.drawable.move1);
                        cleanbtn.setImageResource(R.drawable.clean1);
                        insectbtn.setImageResource(R.drawable.insect1);
                        homebtn.setImageResource(R.drawable.home1);
                        anybtn.setImageResource(R.drawable.anything1);
                        baebtn.setImageResource(R.drawable.bae2);
                        count_clean = 0;
                        count_ins = 0;
                        count_move = 0;
                        count_home = 0;
                        count_any = 0;
                        str = "bae";
                        Log.d("[TAG] str 디버깅 ", str);
                        checked = true;
                    }
                }
                else { // 배달 선택 안 했을 때
                    if (count_bae%2 == 1) { // 배달 선택
                        baebtn.setImageResource(R.drawable.bae2);
                        str = "bae";
                        Log.d("[TAG] str 디버깅 ", str);
                        checked = true;
                    }
                    else { // 배달 선택 취소
                        baebtn.setImageResource(R.drawable.bae1);
                        str = null;
                        checked = false;
                    }
                }
            }
        });


        movebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_move++;
                if (count_clean%2 == 1 || count_any%2 == 1 || count_ins%2 == 1 || count_home%2 == 1 || count_bae%2 == 1) {
                    if (count_move % 2 == 1) {
                        str = "move";
                        movebtn.setImageResource(R.drawable.move2);
                        cleanbtn.setImageResource(R.drawable.clean1);
                        insectbtn.setImageResource(R.drawable.insect1);
                        homebtn.setImageResource(R.drawable.home1);
                        anybtn.setImageResource(R.drawable.anything1);
                        baebtn.setImageResource(R.drawable.bae1);
                        count_bae = 0;
                        count_clean = 0;
                        count_ins = 0;
                        count_home = 0;
                        count_any = 0;
                        Log.d("[TAG] str 디버깅 ",str);
                        checked = true;
                    }
                }
                else {
                    if (count_move % 2 == 1) {
                        str = "move";
                        movebtn.setImageResource(R.drawable.move2);
                        Log.d("[TAG] str 디버깅 ",str);
                        checked = true;
                    }
                    else {
                        movebtn.setImageResource(R.drawable.move1);
                        str = null;
                        checked = false;
                    }
                }
            }
        });


        cleanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_clean++;
                if (count_move%2 == 1 || count_any%2 == 1 || count_ins%2 == 1 || count_home%2 == 1 || count_bae%2 == 1) {
                    if (count_clean % 2 == 1) {
                        str = "clean";
                        cleanbtn.setImageResource(R.drawable.clean2);
                        movebtn.setImageResource(R.drawable.move1);
                        insectbtn.setImageResource(R.drawable.insect1);
                        homebtn.setImageResource(R.drawable.home1);
                        anybtn.setImageResource(R.drawable.anything1);
                        baebtn.setImageResource(R.drawable.bae1);
                        count_bae = 0;
                        count_ins = 0;
                        count_move = 0;
                        count_home = 0;
                        count_any = 0;
                        Log.d("[TAG] str 디버깅 ",str);
                        checked = true;
                    }
                }
                else {
                    if(count_clean % 2 == 1){
                        str = "clean";
                        cleanbtn.setImageResource(R.drawable.clean2);
                        Log.d("[TAG] str 디버깅 ",str);
                        checked = true;
                    }
                    else {
                        cleanbtn.setImageResource(R.drawable.clean1);
                        str = null;
                        checked = false;
                    }
                }
            }
        });


        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_home++;
                if (count_move%2 == 1 || count_any%2 == 1 || count_ins%2 == 1 || count_clean%2 == 1 || count_bae%2 == 1) {
                    if (count_home % 2 == 1) {
                        str = "home";
                        homebtn.setImageResource(R.drawable.home2);
                        cleanbtn.setImageResource(R.drawable.clean1);
                        movebtn.setImageResource(R.drawable.move1);
                        insectbtn.setImageResource(R.drawable.insect1);
                        anybtn.setImageResource(R.drawable.anything1);
                        baebtn.setImageResource(R.drawable.bae1);
                        count_bae = 0;
                        count_clean = 0;
                        count_ins = 0;
                        count_move = 0;
                        count_any = 0;
                        Log.d("[TAG] str 디버깅 ",str);
                        checked = true;
                    }
                }
                else {
                    if(count_home%2 == 1) {
                        str = "home";
                        homebtn.setImageResource(R.drawable.home2);
                        Log.d("[TAG] str 디버깅 ",str);
                        checked = true;
                    }
                    else {
                        homebtn.setImageResource(R.drawable.home1);
                        str = null;
                        checked = false;
                    }
                }
            }
        });


        insectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_ins++;
                if (count_move%2 == 1 || count_any%2 == 1 || count_home%2 == 1 || count_clean%2 == 1 || count_bae%2 == 1) {
                    if (count_ins % 2 == 1) {
                        str = "insect";
                        insectbtn.setImageResource(R.drawable.insect2);
                        homebtn.setImageResource(R.drawable.home1);
                        cleanbtn.setImageResource(R.drawable.clean1);
                        movebtn.setImageResource(R.drawable.move1);
                        anybtn.setImageResource(R.drawable.anything1);
                        baebtn.setImageResource(R.drawable.bae1);
                        count_bae = 0;
                        count_clean = 0;
                        count_move = 0;
                        count_home = 0;
                        count_any = 0;
                        Log.d("[TAG] str 디버깅 ",str);
                        checked = true;
                    }
                }
                else {
                    if (count_ins % 2 == 1) {
                        str = "insect";
                        insectbtn.setImageResource(R.drawable.insect2);
                        Log.d("[TAG] str 디버깅 ",str);
                        checked = true;
                    }
                    else {
                        insectbtn.setImageResource(R.drawable.insect1);
                        str = null;
                        checked = false;
                    }
                }
            }
        });


        anybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_any++;
                if (count_move%2 == 1 || count_ins%2 == 1 || count_home%2 == 1 || count_clean%2 == 1 || count_bae%2 == 1) {
                    if (count_any % 2 == 1) {
                        str = "anything";
                        anybtn.setImageResource(R.drawable.anything2);
                        insectbtn.setImageResource(R.drawable.insect1);
                        homebtn.setImageResource(R.drawable.home1);
                        cleanbtn.setImageResource(R.drawable.clean1);
                        movebtn.setImageResource(R.drawable.move1);
                        baebtn.setImageResource(R.drawable.bae1);
                        count_bae = 0;
                        count_clean = 0;
                        count_ins = 0;
                        count_move = 0;
                        count_home = 0;
                        Log.d("[TAG] str 디버깅 ",str);
                        checked = true;
                    }
                }
                else {
                    if (count_any % 2 == 1) {
                        str = "anything";
                        anybtn.setImageResource(R.drawable.anything2);
                        Log.d("[TAG] str 디버깅 ",str);
                        checked = true;
                    }
                    else {
                        anybtn.setImageResource(R.drawable.anything1);
                        str = "none";
                        checked = false;
                        Log.d("[TAG] str 디버깅 ",str);
                    }
                }
            }
        });


        // helpCall 버튼 누를 시 HelpCallActivity.java에 인텐트를 넘겨줌
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinner1.getSelectedItem().toString().equals("세부 항목을 선택해주세요.")
                        || spinner2.getSelectedItem().toString().equals("포인트를 정해주세요.")
                        || spinner3.getSelectedItem().toString().equals("시간을 선택해주세요.")
                        || info.getText().toString().length()==0 || location.getText().toString().length()==0){
                    Toast.makeText(getApplicationContext(), "정보를 입력해주세요", Toast.LENGTH_SHORT).show();
                    Log.d("info 또는 location 또는 세부사항 또는 포인트 또는 시간", "입력 안 됨");
                }
                else {
                    Intent callintent = new Intent(getApplicationContext(), HelpCallActivity.class);
                    callintent.putExtra("category", str);
                    callintent.putExtra("details", spinner1.getSelectedItem().toString()); //값 넘길 때 String으로 변환하여 넘겨주자
                    callintent.putExtra("info", info.getText().toString());
                    callintent.putExtra("location", location.getText().toString());
                    callintent.putExtra("point", spinner2.getSelectedItem().toString());
                    callintent.putExtra("period", spinner3.getSelectedItem().toString());
                    callintent.putExtra("user_id", user_id);
                    callintent.putExtra("accepted", "none");
                    startActivity(callintent);
                    finish();
                }
            }
        });
    }
}
