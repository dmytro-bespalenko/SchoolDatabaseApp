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
            db.execSQL("CREATE TABLE CLASSROOMS (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "CLASSNAME TEXT, "
                    + "CLASSNUMBER INTEGER, "
                    + "STUDENTSCOUNT INTEGER, "
                    + "FLOOR INTEGER);");
            insertClassrooms(db, "Chemistry", 48, 7, 2);

        }

    }
}

