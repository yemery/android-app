package com.example.mobile_project_sqlite3.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database info
    private static final String DATABASE_NAME = "project_management.db";
    private static final int DATABASE_VERSION = 2;

    // Table names
    public static final String TABLE_USERS = "users";

    // User table columns
    public static final String KEY_ID = "id";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    // Table creation SQL
    private static final String CREATE_USERS_TABLE =
            "CREATE TABLE " + TABLE_USERS + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + KEY_FIRST_NAME + " TEXT NOT NULL,"
                    + KEY_LAST_NAME + " TEXT,"
                    + KEY_EMAIL + " TEXT UNIQUE NOT NULL,"
                    + KEY_USERNAME + " TEXT UNIQUE NOT NULL,"
                    + KEY_PASSWORD + " TEXT NOT NULL"
                    + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
}