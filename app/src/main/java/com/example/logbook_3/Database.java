package com.example.logbook_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Patterns;
import android.webkit.URLUtil;
import android.widget.Toast;

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
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_URL + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void AddUrlImg(String url) {
        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_URL, url);

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == 0) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
            return;
        }
    }


    Cursor readUrlImg(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}
