package com.example.trendinggitrepositories;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.view.Menu;

import android.view.MenuItem;
import android.os.Bundle;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONException;
import org.json.JSONObject;


import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewClick {
    private static final String TAG = MainActivity.class.getName();
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Adapter adapter;
    SearchView searchView;
    ProgressBar progressBar;
    ArrayList<Data> list;
    private RequestQueue mRequestQueue;
    private RequestQueue RequestQueue;
    LocalDataBase db;
    private String url = "https://private-anon-53bc32ce93-githubtrendingapi.apiary-mock.com/repositories";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = findViewById(R.id.Mainactivity_swipeRefresh);
        progressBar = findViewById(R.id.Mainactivity_ProgressBar);
        checkConnection();
        db = new LocalDataBase(this);
        list = new ArrayList<>();

        jsonParse();



        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkConnection();
                pulldown();
            }
        });


    }




    private void jsonParse() {

        progressBar.setVisibility(View.VISIBLE);
        mRequestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest mReq = new JsonArrayRequest(url, response -> {
            list.clear();
            checkConnection();
            db.delete();

            for (int i = 0; i < response.length(); i++) {

                try {

                    JSONObject obj = response.getJSONObject(i);
                    list.add(new Data(obj.getString("name"), obj.getString("description"), obj.getString("language"), obj.getString("stars"), obj.getString("languageColor")));
                    db.insert(obj.getString("name"), obj.getString("description"), obj.getString("language"), obj.getString("stars"));
                    recyclerView = findViewById(R.id.Mainactivity_recyclerView);
                    linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    adapter = new Adapter(list, this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.INVISIBLE);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            }, error -> VolleyLog.d(TAG, "Error: " + error.getMessage()));
        mRequestQueue.add(mReq);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        searchView = (SearchView) menuItem.getActionView();
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

    public void pulldown() {
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }


    public void checkConnection() {
              progressBar.setVisibility(View.VISIBLE);
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (null == networkInfo) {
            Intent intent = new Intent(MainActivity.this, NoNetwork.class);
            startActivity(intent);


        }
        progressBar.setVisibility(View.INVISIBLE);

    }


    @Override
    public void onItemclick(int position) {

        checkConnection();

        RequestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest Req = new JsonArrayRequest(url, response -> {
            try {

                JSONObject obj = response.getJSONObject(position);
                Intent intent = new Intent(MainActivity.this, DisplayContent.class);
                intent.putExtra("author", obj.getString("author"));
                intent.putExtra("name", obj.getString("name"));
                intent.putExtra("avatar", obj.getString("avatar"));
                intent.putExtra("url", obj.getString("url"));
                intent.putExtra("description", obj.getString("description"));
                intent.putExtra("language", obj.getString("language"));
                intent.putExtra("languageColor", obj.getString("languageColor"));
                intent.putExtra("stars", obj.getString("stars"));
                intent.putExtra("forks", obj.getString("forks"));
                intent.putExtra("currentPeriodStars", obj.getString("currentPeriodStars"));
                startActivity(intent);



            } catch (JSONException e) {
                e.printStackTrace();
            }


        }, error -> VolleyLog.d(TAG, "Error: " + error.getMessage()));

        RequestQueue.add(Req);
    }



    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
    }
}




