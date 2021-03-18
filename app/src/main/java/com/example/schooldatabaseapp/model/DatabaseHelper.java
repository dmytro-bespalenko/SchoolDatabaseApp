package com.example.schooldatabaseapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "school";
    private static final int DB_VERSION = 1;

    public final static String TABLE = "CLASSROOMS";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CLASSNAME = "CLASSNAME";
    public static final String COLUMN_CLASSNUMBER = "CLASSNUMBER";
    public static final String COLUMN_STUDENTSCOUNT = "STUDENTSCOUNT";
    public static final String COLUMN_FLOOR = "FLOOR";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);


    }

    private void insertClassrooms(SQLiteDatabase db, String className, int classNumber, int studentsCount, int floor) {
        db = getWritableDatabase();
        ContentValues studentsValues = new ContentValues();
        studentsValues.put("CLASSNAME", className);
        studentsValues.put("CLASSNUMBER", classNumber);
        studentsValues.put("STUDENTSCOUNT", studentsCount);
        studentsValues.put("FLOOR", floor);
        db.insert("CLASSROOMS", null, studentsValues);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db = getWritableDatabase();
        updateMyDatabase(db, oldVersion, newVersion);
        db.close();

    }

    public void deleteAll(SQLiteDatabase db) {
        db = getWritableDatabase();
        db.execSQL("delete from " + TABLE);
        db.execSQL("delete from SQLITE_SEQUENCE where name=" + TABLE);

        db.close();
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        db = getWritableDatabase();
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE " + TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_CLASSNAME + " TEXT, "
                    + COLUMN_CLASSNUMBER + " INTEGER, "
                    + COLUMN_STUDENTSCOUNT + " INTEGER, "
                    + COLUMN_FLOOR + " INTEGER);");
            insertClassrooms(db, null, 0, 0, 0);

        }

    }
}

