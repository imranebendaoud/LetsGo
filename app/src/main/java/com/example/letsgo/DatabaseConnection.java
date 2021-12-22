package com.example.letsgo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseConnection extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "LetsGo";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME="userr";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_FN="firstname";
    private static final String COLUMN_LN="lastname";
    private static final String COLUMN_EMAIL="email";
    private static final String COLUMN_PASSWORD="password";
    private static final String COLUMN_BIO="bio";

    public DatabaseConnection(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FN + " TEXT, " +
                COLUMN_LN + " TEXT, " +
                COLUMN_EMAIL+ " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
