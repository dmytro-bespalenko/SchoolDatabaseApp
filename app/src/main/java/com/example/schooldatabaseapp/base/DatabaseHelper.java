package com.example.schooldatabaseapp.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "school";
    private static final int DB_VERSION = 1;

    public final static String TABLE_CLASSROOMS = "CLASSROOMS";
    public final static String TABLE_STUDENTS = "STUDENTS";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CLASSNAME = "CLASSNAME";
    public static final String COLUMN_CLASSNUMBER = "CLASSNUMBER";
    public static final String COLUMN_STUDENTSCOUNT = "STUDENTSCOUNT";
    public static final String COLUMN_FLOOR = "FLOOR";

    public static final String COLUMN_STUDENT_ID = "_id";
    public static final String COLUMN_FIRST_NAME = "FIRSTNAME";
    public static final String COLUMN_LAST_NAME = "LASTNAME";
    public static final String COLUMN_STUDENT_CLASS_ID = "STUDENTCLASSID";
    public static final String COLUMN_GENDER = "GENDER";
    public static final String COLUMN_AGE = "AGE";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        updateClassRoomsTable(db, 0, DB_VERSION);
        updateStudentsTable(db, 0, DB_VERSION);

    }

    private void insertClassrooms(SQLiteDatabase db, String className, int classNumber, int studentsCount, int floor) {

        ContentValues classroomValues = new ContentValues();
        classroomValues.put(COLUMN_CLASSNAME, className);
        classroomValues.put(COLUMN_CLASSNUMBER, classNumber);
        classroomValues.put(COLUMN_STUDENTSCOUNT, studentsCount);
        classroomValues.put(COLUMN_FLOOR, floor);
        db.insert(TABLE_CLASSROOMS, null, classroomValues);
    }

    private void insertStudents(SQLiteDatabase db, String firstName, String lastName, int classId, String gender, int age) {
        ContentValues studentsValues = new ContentValues();
        studentsValues.put(COLUMN_FIRST_NAME, firstName);
        studentsValues.put(COLUMN_LAST_NAME, lastName);
        studentsValues.put(COLUMN_STUDENT_CLASS_ID, classId);
        studentsValues.put(COLUMN_GENDER, gender);
        studentsValues.put(COLUMN_AGE, age);
        db.insert(TABLE_STUDENTS, null, studentsValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateClassRoomsTable(db, oldVersion, newVersion);
        updateStudentsTable(db, oldVersion, newVersion);
    }

    public void deleteAll(SQLiteDatabase db, String tableName) {
        db.execSQL("delete from " + tableName);
     }

    private void updateClassRoomsTable(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE " + TABLE_CLASSROOMS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_CLASSNAME + " TEXT, "
                    + COLUMN_CLASSNUMBER + " INTEGER, "
                    + COLUMN_STUDENTSCOUNT + " INTEGER, "
                    + COLUMN_FLOOR + " INTEGER);");
            insertClassrooms(db, "Android", 1, 0, 2);

        }

    }

    private void updateStudentsTable(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE " + TABLE_STUDENTS + "(" + COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_FIRST_NAME + " TEXT, "
                    + COLUMN_LAST_NAME + " TEXT, "
                    + COLUMN_STUDENT_CLASS_ID + " INTEGER, "
                    + COLUMN_GENDER + " TEXT, "
                    + COLUMN_AGE + " INTEGER);");
            insertStudents(db, "Ivan", "Ivanov", 1, "Male", 18);

        }

    }
}

