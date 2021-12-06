package com.example.haedream;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class GlobalApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String kakao_app_key = getResources().getString(R.string.kakao_app_key);
        KakaoSdk.init(this, kakao_app_key);
    }
}

/*

카카오 설정 초기화하는 부분

안드로이드 앱 전역에서 공유할 수 있는 Global Application.
Application 상속한 클래스 없어서 새로 만들고, 상속 연결해줌
-> 매니페스트 Application 태그 안에 android:name 속성으로 연결해줘야 한다.

Kakao SDK 사용하려면 초기화 해줘야하는데, 이 공유 클래스 만들어서 앱 수준에서 관리함.
버전마다 차이가 있는데 현재(12/7) 기준 카카오에서 v2만 지원해서 Android SDK 설치 필요.
카카오 공식 홈페이지에서 제공하는 개발자 툴에 코틀린 설명밖에 없어서 애 많이 먹었다,,

 */