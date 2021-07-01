package com.example.trendinggitrepositories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Adapter adapter;
    List<Data> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initRecyclerView();


    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new Data("EZhil","Very nice","Java",123));
        list.add(new Data("Raina","Very nice","Java",123));
        list.add(new Data("Dhoni","Very nice","Java",123));
    }

    private void initRecyclerView() {
        recyclerView=findViewById(R.id.Mainactivity_recyclerView);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new Adapter(list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}