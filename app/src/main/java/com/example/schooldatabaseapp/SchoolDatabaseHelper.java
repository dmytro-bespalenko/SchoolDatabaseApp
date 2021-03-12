package com.example.schooldatabaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SchoolDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "school";
    private static final int DB_VERSION = 1;

    public SchoolDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE CLASSROOMS (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "NUMBER INTEGER, "
                + "FLOOR NUMBER);");

        insertStudents(db, "Chemistry", 48, 2);

    }

    private void insertStudents(SQLiteDatabase db, String className, int classNumber, int floor) {

        ContentValues studentsValues = new ContentValues();
        studentsValues.put("NAME", className);
        studentsValues.put("NUMBER", classNumber);
        studentsValues.put("FLOOR", floor);
        db.insert("CLASSROOMS", null, studentsValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
