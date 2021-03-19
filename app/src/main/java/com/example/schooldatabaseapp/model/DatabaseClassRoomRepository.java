package com.example.schooldatabaseapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class DatabaseClassRoomRepository implements ClassRoomRepository {

    private static final String TAG = "My_Tag";
    private final DatabaseHelper dbHelper;
    private SQLiteDatabase database;


    public DatabaseClassRoomRepository(Context context) {
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }


    @Override
    public void close() {
        dbHelper.close();
    }

    @Override
    public void deleteAll() {
        dbHelper.deleteAll(database);
        close();
    }

    private Cursor getAllEntries() {
        database = dbHelper.getWritableDatabase();
        String[] columns = new String[]{DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_CLASSNAME,
                DatabaseHelper.COLUMN_CLASSNUMBER, DatabaseHelper.COLUMN_STUDENTSCOUNT, DatabaseHelper.COLUMN_FLOOR};
        return database.query(DatabaseHelper.TABLE, columns, null, null, null, null, null);
    }

    @Override
    public List<ClassRoom> getAll() {
        database = dbHelper.getWritableDatabase();
        List<ClassRoom> classrooms = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
            String className = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CLASSNAME));
            int classNumber = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_CLASSNUMBER));
            int studentsCount = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_STUDENTSCOUNT));
            int floor = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_FLOOR));
            classrooms.add(new ClassRoom(id, className, classNumber, studentsCount, floor));
        }
        cursor.close();
        return classrooms;
    }

    @Override
    public long getCount() {
        return DatabaseUtils.queryNumEntries(database, DatabaseHelper.TABLE);
    }

    @Override
    public ClassRoom getById(int id) {
        database = dbHelper.getWritableDatabase();
        ClassRoom classRoom = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?", DatabaseHelper.TABLE, DatabaseHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            String className = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CLASSNAME));
            int classNumber = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_CLASSNUMBER));
            int studentsCount = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_STUDENTSCOUNT));
            int floor = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_FLOOR));
            classRoom = new ClassRoom(id, className, classNumber, studentsCount, floor);
        }
        cursor.close();
        return classRoom;
    }

    @Override
    public long insert(ClassRoom classRoom) {
        database = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_CLASSNAME, classRoom.getClassName());
        cv.put(DatabaseHelper.COLUMN_CLASSNUMBER, classRoom.getClassNumber());
        cv.put(DatabaseHelper.COLUMN_STUDENTSCOUNT, classRoom.getStudentsCount());
        cv.put(DatabaseHelper.COLUMN_FLOOR, classRoom.getFloor());

        return database.insert(DatabaseHelper.TABLE, null, cv);
    }

    @Override
    public int delete(int classId) {
        database = dbHelper.getWritableDatabase();
        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(classId)};
        return database.delete(DatabaseHelper.TABLE, whereClause, whereArgs);
    }

    @Override
    public int update(ClassRoom classRoom) {
        database = dbHelper.getWritableDatabase();
        String whereClause = DatabaseHelper.COLUMN_ID + "=" + classRoom.getId();
        ContentValues cv = new ContentValues();

        cv.put(DatabaseHelper.COLUMN_CLASSNAME, classRoom.getClassName());
        cv.put(DatabaseHelper.COLUMN_CLASSNUMBER, classRoom.getClassNumber());
        cv.put(DatabaseHelper.COLUMN_STUDENTSCOUNT, classRoom.getStudentsCount());
        cv.put(DatabaseHelper.COLUMN_FLOOR, classRoom.getFloor());

        Log.d(TAG, "run: " + Thread.currentThread().getName());

        return database.update(DatabaseHelper.TABLE, cv, whereClause, null);
    }

}
