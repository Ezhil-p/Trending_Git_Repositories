package com.example.trendinggitrepositories;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Menu;

import android.view.MenuItem;
import android.os.Bundle;


import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;


import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    SearchView searchView;
    LinearLayoutManager linearLayoutManager;
    TextView tv;
    Adapter adapter;
    ProgressBar progressBar;
    List<Data> list;
    private RequestQueue mRequestQueue;
    private String url = "https://private-anon-53bc32ce93-githubtrendingapi.apiary-mock.com/repositories";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout=findViewById(R.id.Mainactivity_swipeRefresh);
         progressBar=findViewById(R.id.Mainactivity_ProgressBar);
         checkConnection();
        list = new ArrayList<>();
        jsonParse();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pulldown();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void jsonParse() {

        progressBar.setVisibility(View.VISIBLE);
        mRequestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest movieReq = new JsonArrayRequest(url, response -> {
                     list.clear();
                    checkConnection();
            for (int i = 0; i < response.length(); i++) {

                try {

                    JSONObject obj = response.getJSONObject(i);
                    list.add(new Data(obj.getString("name"), obj.getString("description"), obj.getString("language"), obj.getString("stars")));
                    recyclerView = findViewById(R.id.Mainactivity_recyclerView);
                    linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    adapter = new Adapter(list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.INVISIBLE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }, error -> VolleyLog.d(TAG, "Error: " + error.getMessage()));

        mRequestQueue.add(movieReq);
    }
    public void pulldown()
    {
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

public void checkConnection() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (null == networkInfo) {
            Intent intent = new Intent(MainActivity.this, NoNetwork.class);
            startActivity(intent);


    }

}

}




