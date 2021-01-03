package com.aman.to_dolistapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Databasehelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="NotesDatabase";


    public Databasehelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuary="CREATE TABLE Notes (id INTEGER PRIMARY KEY AUTOINCREMENT ,title TEXT, description TEXT)";
        db.execSQL(sqlQuary);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlQuary="drop Table if exists Notes";
        db.execSQL(sqlQuary);
        onCreate(db);
    }
}
