package com.example.haedream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 로딩 화면 출력

        // getHashKey(); // 앱 해시 키 얻는 방법. API 사용 허가 위해 해시 키 받아 사용하였음

        // 4초 뒤 Login 화면 출력함
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
                finish();
            }
        }, 3000); // 4초 대기
    }

    /*
    카카오 로그인 구현하기 위해서는 키 해실를 카카오 개발자 홈페이지에 등록해야 된다.
    키 해시 얻는 방법은 CMD에서 하거나 Android에서 직접 출력하는 방법 두 가지가 있는데
    개발자들이 cmd를 추천하긴 하지만 우린 상용화 단계 아니고 프로젝트여서 안드로이드 방법 사용했음.
    아래 주석처리된 부분 풀고, 위 getHashKey() 함수 호출하는 부분 실행하면
    디버깅 툴 Logcat에 자기 키해시값 얻을 수 있음. 그거 나한테 보내줘야 등록하고 실행까지 가능
    키해시 등록 안하면 프로그램 실행 안된다!
     */

    /*
    private void getHashKey(){ // 해시 얻는 함수. 보안성 문제로 주석처리
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }
    */
}