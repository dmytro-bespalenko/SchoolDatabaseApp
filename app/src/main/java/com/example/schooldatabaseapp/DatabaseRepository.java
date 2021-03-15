package com.example.schooldatabaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.schooldatabaseapp.model.ClassRoom;

import java.util.ArrayList;
import java.util.List;

public class DatabaseRepository implements ClassRoomContract.Repository{

    private SchoolDatabaseHelper dbHelper;
    private SQLiteDatabase database;


    public DatabaseRepository(Context context) {
        dbHelper = new SchoolDatabaseHelper(context.getApplicationContext());
    }


    public DatabaseRepository open() {
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private Cursor getAllEntries() {
        String[] columns = new String[]{SchoolDatabaseHelper.COLUMN_ID, SchoolDatabaseHelper.COLUMN_CLASSNAME,
                SchoolDatabaseHelper.COLUMN_CLASSNUMBER, SchoolDatabaseHelper.COLUMN_STUDENTSCOUNT, SchoolDatabaseHelper.COLUMN_FLOOR};
        return database.query(SchoolDatabaseHelper.TABLE, columns, null, null, null, null, null);
    }

    @Override
    public List<ClassRoom> getClassRooms() {
        List<ClassRoom> classrooms = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(SchoolDatabaseHelper.COLUMN_ID));
            String className = cursor.getString(cursor.getColumnIndex(SchoolDatabaseHelper.COLUMN_CLASSNAME));
            int classNumber = cursor.getInt(cursor.getColumnIndex(SchoolDatabaseHelper.COLUMN_CLASSNUMBER));
            int studentsCount = cursor.getInt(cursor.getColumnIndex(SchoolDatabaseHelper.COLUMN_STUDENTSCOUNT));
            int floor = cursor.getInt(cursor.getColumnIndex(SchoolDatabaseHelper.COLUMN_FLOOR));
            classrooms.add(new ClassRoom(id, className, classNumber, studentsCount, floor));
        }
        cursor.close();
        return classrooms;
    }

    @Override
    public long getCount() {
        return DatabaseUtils.queryNumEntries(database, SchoolDatabaseHelper.TABLE);
    }

    @Override
    public ClassRoom getClassRoom(int id) {
        ClassRoom classRoom = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?", SchoolDatabaseHelper.TABLE, SchoolDatabaseHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            String className = cursor.getString(cursor.getColumnIndex(SchoolDatabaseHelper.COLUMN_CLASSNAME));
            int classNumber = cursor.getInt(cursor.getColumnIndex(SchoolDatabaseHelper.COLUMN_CLASSNUMBER));
            int studentsCount = cursor.getInt(cursor.getColumnIndex(SchoolDatabaseHelper.COLUMN_STUDENTSCOUNT));
            int floor = cursor.getInt(cursor.getColumnIndex(SchoolDatabaseHelper.COLUMN_FLOOR));
            classRoom = new ClassRoom(id, className, classNumber, studentsCount, floor);
        }
        cursor.close();
        return classRoom;
    }

    @Override
    public long insert(ClassRoom classRoom) {

        ContentValues cv = new ContentValues();
        cv.put(SchoolDatabaseHelper.COLUMN_CLASSNAME, classRoom.getClassName());
        cv.put(SchoolDatabaseHelper.COLUMN_CLASSNUMBER, classRoom.getClassNumber());
        cv.put(SchoolDatabaseHelper.COLUMN_STUDENTSCOUNT, classRoom.getStudentsCount());
        cv.put(SchoolDatabaseHelper.COLUMN_FLOOR, classRoom.getFloor());

        return database.insert(SchoolDatabaseHelper.TABLE, null, cv);
    }

    @Override
    public int delete(int classId) {

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(classId)};
        return database.delete(SchoolDatabaseHelper.TABLE, whereClause, whereArgs);
    }

    @Override
    public int update(ClassRoom classRoom) {

        String whereClause = SchoolDatabaseHelper.COLUMN_ID + "=" + classRoom.getId();
        ContentValues cv = new ContentValues();
        cv.put(SchoolDatabaseHelper.COLUMN_CLASSNAME, classRoom.getClassName());
        cv.put(SchoolDatabaseHelper.COLUMN_CLASSNUMBER, classRoom.getClassNumber());
        cv.put(SchoolDatabaseHelper.COLUMN_STUDENTSCOUNT, classRoom.getStudentsCount());
        cv.put(SchoolDatabaseHelper.COLUMN_FLOOR, classRoom.getFloor());

        return database.update(SchoolDatabaseHelper.TABLE, cv, whereClause, null);
    }

}
