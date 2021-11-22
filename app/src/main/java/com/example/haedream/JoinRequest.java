package com.example.haedream;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class JoinRequest extends StringRequest {
    // 서버 URL 설정 ( PHP 파일 연동 - Database 바로 접근 불가, php 중간 매체로 파싱하여 사용 )
    final static private String URL = "http://idox23.cafe24.com/Join.php";
    private Map<String, String> map;


    public JoinRequest(String userID, String userPW, String dept, String tel, String birth, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPW", userPW);
        map.put("dept", dept);
        map.put("tel", tel);
        map.put("birth", birth);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
