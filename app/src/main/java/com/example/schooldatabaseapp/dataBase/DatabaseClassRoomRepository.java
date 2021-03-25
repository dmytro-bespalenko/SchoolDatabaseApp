package com.example.schooldatabaseapp.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.schooldatabaseapp.base.DatabaseHelper;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;

import java.util.ArrayList;
import java.util.List;

public class DatabaseClassRoomRepository implements ClassRoomRepository {

    private static final String TAG = "My_Tag";
    private final DatabaseHelper dbHelper;
    private SQLiteDatabase database;


    public DatabaseClassRoomRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public void close() {
        dbHelper.close();
    }

    @Override
    public void deleteAll() {
        dbHelper.deleteAll(database, DatabaseHelper.TABLE_CLASSROOMS);
        close();
    }

    @Override
    public Cursor getAllEntries() {
        database = dbHelper.getWritableDatabase();
        String[] columns = new String[]{DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_CLASSNAME,
                DatabaseHelper.COLUMN_CLASSNUMBER, DatabaseHelper.COLUMN_STUDENTSCOUNT, DatabaseHelper.COLUMN_FLOOR};
        return database.query(DatabaseHelper.TABLE_CLASSROOMS, columns, null, null, null, null, null);
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
        return DatabaseUtils.queryNumEntries(database, DatabaseHelper.TABLE_CLASSROOMS);
    }

    @Override
    public ClassRoom getById(int id) {
        database = dbHelper.getWritableDatabase();
        ClassRoom classRoom = null;
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_CLASSROOMS + " WHERE " + DatabaseHelper.COLUMN_ID;
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

        return database.insert(DatabaseHelper.TABLE_CLASSROOMS, null, cv);
    }

    @Override
    public int delete(int classId) {
        database = dbHelper.getWritableDatabase();
        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(classId)};
        return database.delete(DatabaseHelper.TABLE_CLASSROOMS, whereClause, whereArgs);
    }

    @Override
    public int update(ClassRoom classRoom) {
        database = dbHelper.getWritableDatabase();
        String whereClause = DatabaseHelper.COLUMN_ID + "=" + classRoom.getClassId();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_CLASSNAME, classRoom.getClassName());
        cv.put(DatabaseHelper.COLUMN_CLASSNUMBER, classRoom.getClassNumber());
        cv.put(DatabaseHelper.COLUMN_STUDENTSCOUNT, classRoom.getStudentsCount());
        cv.put(DatabaseHelper.COLUMN_FLOOR, classRoom.getFloor());

        Log.d(TAG, "run: " + Thread.currentThread().getName());

        return database.update(DatabaseHelper.TABLE_CLASSROOMS, cv, whereClause, null);
    }

}
