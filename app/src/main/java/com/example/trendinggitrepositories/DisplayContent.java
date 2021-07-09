package com.example.trendinggitrepositories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DisplayContent extends AppCompatActivity {
    TextView author,name,description,language,url,languagecolor,stars,forks,currentperiodstars;
    ImageView avatar_dp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_content);
        author=findViewById(R.id.DisplayContent_author);
        name=findViewById(R.id.DisplayContent_name);
        description=findViewById(R.id.DisplayContent_description);
        url=findViewById(R.id.DisplayContent_url);
        language=findViewById(R.id.DisplayContent_language);
        languagecolor=findViewById(R.id.DisplayContent_languagecolor);
        stars=findViewById(R.id.DisplayContent_stars);
        forks=findViewById(R.id.DisplayContent_forks);
        currentperiodstars=findViewById(R.id.DisplayContent_currentperiodstars);
        avatar_dp=findViewById(R.id.DisplayContent_avatarimage);


        Intent intent=getIntent();
        String dc_author=intent.getStringExtra("author");
        String dc_name=intent.getStringExtra("name");
        String dc_currentperiodstars=intent.getStringExtra("currentPeriodStars");
        String dc_description=intent.getStringExtra("description");
        String dc_avatar=intent.getStringExtra("avatar");
        String dc_url=intent.getStringExtra("url");
        String dc_language=intent.getStringExtra("language");
        String dc_languagecolor=intent.getStringExtra("languageColor");
        String dc_stars=intent.getStringExtra("stars");
        String dc_forks=intent.getStringExtra("forks");

        author.setText(dc_author);
        name.setText(dc_name);
        description.setText(dc_description);
        Glide.with(getApplicationContext()).load(dc_avatar).into(avatar_dp);
        url.setText(dc_url);
        language.setText(dc_language);
        languagecolor.setText(dc_languagecolor);
        stars.setText(dc_stars);
        forks.setText(dc_forks);
        currentperiodstars.setText(dc_currentperiodstars);

    }
    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

}