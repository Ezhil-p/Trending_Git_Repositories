package com.example.trendinggitrepositories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class NoNetwork extends AppCompatActivity  {
    Button tryagain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_network);
        tryagain=findViewById(R.id.NoNetwork_tryagainbutton);


        tryagain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

               Intent intent=new Intent(NoNetwork.this,MainActivity.class);
               startActivity(intent);


            }

        });



    }
    @Override
    public void onBackPressed() {

    }

}