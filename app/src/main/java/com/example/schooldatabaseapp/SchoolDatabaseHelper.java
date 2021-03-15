package com.example.schooldatabaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SchoolDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "school";
    private static final int DB_VERSION = 1;

    public final static String TABLE = "CLASSROOMS";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CLASSNAME = "CLASSNAME";
    public static final String COLUMN_CLASSNUMBER = "CLASSNUMBER";
    public static final String COLUMN_STUDENTSCOUNT = "STUDENTSCOUNT";
    public static final String COLUMN_FLOOR = "FLOOR";


    public SchoolDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);


    }

    private void insertClassrooms(SQLiteDatabase db, String className, int classNumber, int studentsCount, int floor) {

        ContentValues studentsValues = new ContentValues();
        studentsValues.put("CLASSNAME", className);
        studentsValues.put("CLASSNUMBER", classNumber);
        studentsValues.put("STUDENTSCOUNT", studentsCount);
        studentsValues.put("FLOOR", floor);
        db.insert("CLASSROOMS", null, studentsValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        updateMyDatabase(db, oldVersion, newVersion);
    }


    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE " + TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_CLASSNAME + " TEXT, "
                    + COLUMN_CLASSNUMBER + " INTEGER, "
                    + COLUMN_STUDENTSCOUNT + " INTEGER, "
                    + COLUMN_FLOOR + " INTEGER);");
            insertClassrooms(db, "Chemistry", 48, 7, 2);

        }

    }
}

