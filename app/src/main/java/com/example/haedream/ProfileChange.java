package com.example.haedream;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileChange extends AppCompatActivity {
    String user_id;
    ImageView imgview;
    TextView tv_name;

    public String uploadFilePath;
    public String uploadFileName;
    private int REQ_CODE_PICK_PICTURE = 1;
    // 파일을 업로드 하기 위한 변수 선언
    private int serverResponseCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilechange);

        Button selectbtn, changebtn;
        ImageButton backbtn;
        imgview = findViewById(R.id.iv);
        tv_name = findViewById(R.id.tv_name);

        Intent userintent = getIntent();
        user_id = userintent.getStringExtra("user_id");
        Log.d("[TAG] 로그인 아이디 인텐트 전달", user_id);

        tv_name.setText(user_id + "님 프로필 사진");

        // 동적퍼미션 작업
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            int permissionResult= checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(permissionResult== PackageManager.PERMISSION_DENIED){
                String[] permissions= new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions,10);
            }
        }

        new ProfileChange.Select_ProfileChange_Request().execute();


        // 이미지 선택 버튼 클릭 시
        selectbtn = findViewById(R.id.selectbtn);
        selectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType(MediaStore.Images.Media.CONTENT_TYPE);
                i.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI); // images on the SD card.
                // 결과를 리턴하는 Activity 호출
                startActivityForResult(i, REQ_CODE_PICK_PICTURE);
            }
        });

        // 이미지 변경하기 버튼 클릭 시
        changebtn = findViewById(R.id.changebtn);
        changebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uploadFilePath != null) {
                    // 서버에 이미지 올리고 디비에 경로 저장
                    UploadImageToServer uploadimagetoserver = new UploadImageToServer();
                    uploadimagetoserver.execute("http://idox23.cafe24.com/ImageUploadToServer.php");
                } else {
                    Toast.makeText(ProfileChange.this, "You didn't insert any image", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 뒤로가기 버튼 클릭 시
        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainSCR.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
                finish();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_PICK_PICTURE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                String path = getPath(uri);
                String name = getName(uri);

                uploadFilePath = path;
                uploadFileName = name;

                Log.d("[onActivityResult] uploadFilePath:", uploadFilePath + ", uploadFileName:" + uploadFileName);

                Bitmap bit = BitmapFactory.decodeFile(path);
                imgview.setImageBitmap(bit);
            }
        }
    }

    // 실제 경로 찾기
    private String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    // 파일명 찾기
    private String getName(Uri uri) {
        String[] projection = {MediaStore.Images.ImageColumns.DISPLAY_NAME};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    // uri 아이디 찾기
    private String getUriId(Uri uri) {
        String[] projection = {MediaStore.Images.ImageColumns._ID};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    private class UploadImageToServer extends AsyncTask<String, String, String> {
        ProgressDialog mProgressDialog;
        String fileName = uploadFilePath;
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 10240 * 10240;
        File sourceFile = new File(uploadFilePath);

        @Override
        protected void onPreExecute() {
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(ProfileChange.this);
            mProgressDialog.setTitle("Loading...");
            mProgressDialog.setMessage("Image uploading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();

        }

        @Override
        protected String doInBackground(String... serverUrl) {
            if (!sourceFile.isFile()) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Log.d("[UploadImageToServer] Source File not exist :", uploadFilePath);
                    }
                });
                return null;
            } else {
                try {
                    // open a URL connection to the Servlet
                    FileInputStream fileInputStream = new FileInputStream(sourceFile);
                    URL url = new URL(serverUrl[0]);

                    // Open a HTTP  connection to  the URL
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true); // Allow Inputs
                    conn.setDoOutput(true); // Allow Outputs
                    conn.setUseCaches(false); // Don't use a Cached Copy
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                    conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                    conn.setRequestProperty("uploaded_file", fileName);
                    Log.d("fileName: ", fileName);

                    dos = new DataOutputStream(conn.getOutputStream());

                    // 사용자 이름으로 폴더를 생성하기 위해 사용자 이름을 서버로 전송한다. << 폴더생성안함
                    dos.writeBytes(twoHyphens + boundary + lineEnd);
                    dos.writeBytes("Content-Disposition: form-data; name=\"data1\"" + lineEnd);
                    dos.writeBytes(lineEnd);
                    // data1에 user_id(인텐트로 받아온 로그인 사용자 아이디) 넣어줌
                    dos.writeBytes(user_id);
                    dos.writeBytes(lineEnd);

                    // 이미지 전송
                    dos.writeBytes(twoHyphens + boundary + lineEnd);
                    dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\"; filename=\"" + fileName + "\"" + lineEnd);
                    dos.writeBytes(lineEnd);

                    // create a buffer of  maximum size
                    bytesAvailable = fileInputStream.available();

                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];

                    // read file and write it into form...
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                    while (bytesRead > 0) {
                        dos.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                    }

                    // send multipart form data necesssary after file data...
                    dos.writeBytes(lineEnd);
                    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                    // Responses from the server (code and message)
                    serverResponseCode = conn.getResponseCode();
                    String serverResponseMessage = conn.getResponseMessage();

                    Log.d("[UploadImageToServer] HTTP Response is : ", serverResponseMessage + ": " + serverResponseCode);

                    if (serverResponseCode == 200) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(ProfileChange.this, "File Upload Completed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    //close the streams //
                    fileInputStream.close();
                    dos.flush();
                    dos.close();

                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(ProfileChange.this, "MalformedURLException", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Log.d("[UploadImageToServer] error: ", ex.getMessage() + ex);
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(ProfileChange.this, "Got Exception : see logcat ", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Log.d("[UploadImageToServer] Upload file to server Exception Exception : ", e.getMessage() + e);
                }
                Log.d("[UploadImageToServer]", "Finish");
                return null;
            } // End else block
        }

        @Override
        protected void onPostExecute(String s) {
            mProgressDialog.dismiss();
        }

    }

    class Select_ProfileChange_Request extends AsyncTask<String, Integer, String> {
        String result = null;
        @Override
        protected String doInBackground(String... rurls) {
            try {
                URL url = new URL("https://idox23.cafe24.com/profile_result.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                if(conn.getResponseCode()==HttpURLConnection.HTTP_OK) {
                    InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    result = stringBuilder.toString();
                } else {
                    result = "error";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        protected void onPostExecute(String _result) {
            try {
                JSONObject root = new JSONObject(_result);
                JSONArray results = new JSONArray(root.getString("results"));

                for (int index = 0; index < results.length(); index++) {
                    JSONObject Content = results.getJSONObject(index);
                    String userid = Content.getString("userID");
                    String imgPath = Content.getString("profile");

                    if(userid.equals(user_id)){
                        if (imgPath.equals(null)){
                            imgview.setImageResource(R.drawable.my);
                        }
                        // 이미지뷰에 서버에서 불러온 이미지 띄움
                        Glide.with(imgview).load("https://idox23.cafe24.com/"+imgPath).into(imgview);
                        Log.d("이미지 경로 : ", imgPath);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
