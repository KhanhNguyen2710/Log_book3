package com.example.logbook_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Patterns;
import android.webkit.URLUtil;

import androidx.annotation.Nullable;

import java.net.MalformedURLException;
import java.net.URL;

public class Database extends SQLiteOpenHelper {
    private Context context;
    private static final  String DATABASE_NAME = "Log_book3.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Logbook3";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_URL = "urlImg";

    Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_URL + " TEXT)";
        db.execSQL(query);
    }

    public boolean AddUrlImg(String url){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_URL, url);

        long result = database.insert(TABLE_NAME, null, cv);
        if( result == -1) {
            return false;
        }
        return true;
    }

    Cursor ReadUrlImg(){

        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;


        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
