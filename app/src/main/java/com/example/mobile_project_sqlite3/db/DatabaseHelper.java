package com.example.mobile_project_sqlite3.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database info
    private static final String DATABASE_NAME = "project_management.db";
    private static final int DATABASE_VERSION = 3;

    // Table names
    public static final String TABLE_USERS = "users";

    // User table columns
    public static final String KEY_ID = "id";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";


    public static final String TABLE_PROJECTS = "projects";
    public static final String KEY_PROJECT_ID = "id";
    public static final String KEY_PROJECT_NAME = "name";
    public static final String KEY_PROJECT_DESCRIPTION = "description";
    public static final String KEY_START_DATE = "start_date";
    public static final String KEY_END_DATE = "end_date";
    public static final String KEY_STATUS = "status";
    public static final String KEY_USER_ID = "user_id";

    private static final String CREATE_PROJECTS_TABLE =
            "CREATE TABLE " + TABLE_PROJECTS + "("
                    + KEY_PROJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + KEY_PROJECT_NAME + " TEXT NOT NULL,"
                    + KEY_PROJECT_DESCRIPTION + " TEXT,"
                    + KEY_START_DATE + " TEXT,"
                    + KEY_END_DATE + " TEXT,"
                    + KEY_STATUS + " TEXT,"
                    + KEY_USER_ID + " INTEGER,"
                    + "FOREIGN KEY(" + KEY_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + KEY_ID + ")"
                    + ")";
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
        db.execSQL(CREATE_PROJECTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECTS);

        onCreate(db);
    }
}