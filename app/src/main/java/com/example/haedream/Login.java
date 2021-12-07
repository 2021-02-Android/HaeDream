package com.example.haedream;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class Login extends AppCompatActivity {
    EditText user_id;
    private final static String TAG = "kakao debug";
    private Button kakaoAuth;

    private long backKeyPressedTime = 0;  // 뒤로가기 버튼을 누른 시간 저장
    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if (oAuthToken != null) {
                    Log.i("user", oAuthToken.getAccessToken() + " " + oAuthToken.getRefreshToken());
                }
                if (throwable != null) {
                    Log.w(TAG, "invoke: " + throwable.getLocalizedMessage());
                }
                updateKakaoLoginUi();
                return null;
            }
        };  // Kakao SDK

        kakaoAuth = findViewById(R.id.kakao_auth_button);
        kakaoAuth.setOnClickListener(new View.OnClickListener() {
            // 카카오톡 로그인 버튼 클릭 시 Login with kakao
            @Override public void onClick(View v) {
                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(Login.this)) {
                    // 스마트폰에 카카오톡 설치된 경우 - AVD로 실행할 때 없어서 else문(웹)으로 열림
                    UserApiClient.getInstance().loginWithKakaoTalk(Login.this, callback);
                } else {
                    UserApiClient.getInstance().loginWithKakaoAccount(Login.this, callback);
                }
            }
        });

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
        user_id = findViewById(R.id.user_id);
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
            }
        });
    }

    public void onKakaoLoginResult(User user){
        // callback - 미작성.. 뭐에 쓰는 거더라
    }

    void updateKakaoLoginUi() {
        // 카카오 UI 가져오는 메소드 (로그인 핵심 기능)
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if (user != null) {  // 로그인 성공시 (유저 정보가 정상 전달 되었을 경우)
                    Log.i(TAG, "id " + user.getId());  // 고유 아이디 (발급되는 번호인듯?)
                    Log.i(TAG, "invoke: nickname=" + user.getKakaoAccount().getProfile().getNickname());  // 사용자 이름 (카톡에서 설정한 거)
                    Log.i(TAG, "userimage " + user.getKakaoAccount().getProfile().getProfileImageUrl());  // 프로필 사진 URL

                    Intent intent = new Intent(getApplicationContext(), KakaoLogin.class);  // 카카오톡 회원가입으로 넘어감 DB not null이라 입력해줘야함
                    intent.putExtra("user_id", user.getId().toString());  // getId로 불러온 값 String이 아닌지 toString 안하면 오류발생
                    intent.putExtra("user_name", user.getKakaoAccount().getProfile().getNickname());
                    startActivity(intent);
                } if (throwable != null) {  // 로그인 오류 발생 - 거의 발생 안하지만 예외처리 위해 적어둠
                    // 예외사항 : 키해시 등록 되지 않은 경우.
                    // 개발자 여러 명인 경우 키해시 전부 등록해줘야 된다!
                    Log.w(TAG, "invoke: " + throwable.getLocalizedMessage());
                }
                return null;
            }
        });
    }

    @Override
    public void onBackPressed() {  // 메인 화면에서 뒤로가기 버튼 2회 누르면 앱 종료
        // 2000 milliseconds = 2 seconds
        // 가장 최근에 뒤로가기 버튼을 누른 시간에 2초를 더해 현재시간과 비교 후, 2초가 지났으면 메시지 출력
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "뒤로가기 버튼을 다시 누르면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // 2초가 지나지 않은 시점에서 뒤로가기 다시 클릭시 앱 종료
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            toast.cancel();  // 알림 팝업(Toast) 표시 해제
            ActivityCompat.finishAffinity(this);
            System.exit(0);  // 시스템 종료
        }
    }
}