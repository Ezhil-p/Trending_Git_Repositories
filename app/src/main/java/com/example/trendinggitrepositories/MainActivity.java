package com.example.trendinggitrepositories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    TextView tv;
    Adapter adapter;
    List<Data> list;
    private RequestQueue mRequestQueue;
    private String url = "https://private-anon-53bc32ce93-githubtrendingapi.apiary-mock.com/repositories";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        jsonParse();



    }

    private void jsonParse()
    {
        mRequestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest movieReq = new JsonArrayRequest(url, response -> {

            for (int i = 0; i < response.length(); i++) {

                try {

                    JSONObject   obj = response.getJSONObject(i);
                    list.add(new Data(obj.getString("name"),obj.getString("description"),obj.getString("language"),obj.getString("stars")));
                    recyclerView=findViewById(R.id.Mainactivity_recyclerView);
                    linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    adapter = new Adapter(list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }, error -> VolleyLog.d(TAG, "Error: " + error.getMessage()));

        mRequestQueue.add(movieReq);
    }


                    }




