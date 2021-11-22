package com.example.haedream;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SimhelpList extends AppCompatActivity {
    ListView listView;
    ArrayList<simhelpitem> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helplist);

        listView = findViewById(R.id.listview);
        arrayList = new ArrayList<>();

        new SimhelpList.Select_HelpList_Request().execute();

        ImageButton simButton = (ImageButton) findViewById(R.id.callbutton);
        simButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HelpCall.class);
                startActivity(intent);
            }
        });
    }

    class Select_HelpList_Request extends AsyncTask<String, Integer, String> {
        String result = null;
        @Override
        protected String doInBackground(String... rurls) {
            try {
                URL url = new URL("https://idox23.cafe24.com/task_result.php");
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
                    String category = Content.getString("category");
                    String details = Content.getString("details");
                    String location = Content.getString("location");
                    String info = Content.getString("info");
                    String point = Content.getString("point");

                    simhelpitem item = new simhelpitem();

                    item.setCategory(category);
                    item.setDetails(details);
                    item.setLocation(location);
                    item.setInfo(info);
                    item.setPoint(point);
                    arrayList.add(item);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            SimhelpItemAdapter helpListViewAdapter = new SimhelpItemAdapter(SimhelpList.this, arrayList);
            listView.setAdapter(helpListViewAdapter);
        }
    }
}