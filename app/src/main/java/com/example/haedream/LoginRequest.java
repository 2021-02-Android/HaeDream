package com.example.haedream;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    // 서버 URL 설정 (PHP 파일 연동 - Database 바로 접근 불가, php 중간 매체로 파싱하여 사용)
    // php 파일은 호스팅서버 www 폴더 아래에 위치 (폴더 변경X)
    final static private String URL = "http://idox23.cafe24.com/Login.php"; // php URL 지정
    private Map<String, String> map; // 여기부터 php 파일 파싱하여 사용하기 위한 소스 작성


    public LoginRequest(String userID, String userPW, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID",userID);
        map.put("userPW", userPW);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}