package com.example.trendinggitrepositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class LocalDataBase extends SQLiteOpenHelper {
    public static final String TABLE_NAME="trendingrepositories";
    public LocalDataBase(Context context) {
        super(context,"localDataBase.db",null,1);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table trendingrepositories("+"name varchar(20),"+"description varchar(1000),"+"language varchar(20),"+"stars varchar(10))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    public boolean insert(String name,String description,String language,String stars)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("description",description);
        values.put("language",language);
        values.put("stars",stars);
        long result=db.insert(TABLE_NAME,null,values);
        if (result==-1)
            return false;
        else
            return true;
    }
    public void delete()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.close();


    }
   /* public Cursor showData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME+"",null);
       System.out.println("........................................"+res);
        return res;
    }*/

}
