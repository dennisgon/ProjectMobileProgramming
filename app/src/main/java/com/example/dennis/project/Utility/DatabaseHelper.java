package com.example.dennis.project.Utility;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dennis.project.Utility.Transaksi;
import com.example.dennis.project.Utility.User;

/**
 * Created by dennis on 6/2/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Database.db";
    public static final Integer version = 2;
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(User.CREATE_USER);
        db.execSQL(Transaksi.CREATE_TRASACTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(User.DROP_USER);
        db.execSQL(Transaksi.DROP_TRASACTION);
    }
}
