package com.example.haedream;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MySimCallRequest extends StringRequest {
    // 서버 URL 설정 ( PHP 파일 연동 - Database 바로 접근 불가, php 중간 매체로 파싱하여 사용 )
    // php 파일 변경 시 수정 해야 함
    final static private String URL = "http://idox23.cafe24.com/task_delete.php";
    private Map<String, String> map;


    public MySimCallRequest(String name, String info, String location, String point, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userid", name);
        map.put("info", info);
        map.put("location", location);
        map.put("point", point);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}