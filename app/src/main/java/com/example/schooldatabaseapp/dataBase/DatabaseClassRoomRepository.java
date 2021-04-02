package com.example.schooldatabaseapp.dataBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.schooldatabaseapp.base.DatabaseHelper;
import com.example.schooldatabaseapp.base.SchoolApplication;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.model.Student;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class DatabaseClassRoomRepository implements ClassRoomRepository {

    private static final String TAG = "My_Tag";
    private final DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private static DatabaseClassRoomRepository instance;


    public DatabaseClassRoomRepository(SchoolApplication application) {
        dbHelper = new DatabaseHelper(application);
    }


    public static synchronized DatabaseClassRoomRepository getInstance() {
        if (instance == null) {
            throw new RuntimeException();
        }
        return instance;

    }

    public static void initInstance(SchoolApplication schoolApplication) {

        if (schoolApplication != null) {
            instance = new DatabaseClassRoomRepository(schoolApplication);
        }
    }

    @Override
    public void close() {
        dbHelper.close();
    }

    @Override
    public void deleteAll() {
        dbHelper.deleteAll(database, DatabaseHelper.TABLE_CLASSROOMS);
    }

    @Override
    public Single<List<ClassRoom>> getAllClassrooms() {
        database = dbHelper.getWritableDatabase();

        return getAllClassRoomsEntries()
                .map(new Function<Cursor, List<ClassRoom>>() {
                    @Override
                    public List<ClassRoom> apply(@NonNull Cursor cursor) throws Exception {
                        List<ClassRoom> classrooms = new ArrayList<>();
                        while (cursor.moveToNext()) {
                            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                            String className = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CLASSNAME));
                            int classNumber = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_CLASSNUMBER));
                            int studentsCount = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_STUDENTSCOUNT));
                            int floor = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_FLOOR));
                            classrooms.add(new ClassRoom(id, className, classNumber, studentsCount, floor));
                        }

                        return classrooms;
                    }
                });
    }

    @Override
    public Single<List<Student>> getAllStudents() {
        database = dbHelper.getWritableDatabase();

        return getAllStudentsEntries()
                .map(new Function<Cursor, List<Student>>() {
                    @Override
                    public List<Student> apply(@NonNull Cursor cursor) throws Exception {
                        List<Student> students = new ArrayList<>();

                        while (cursor.moveToNext()) {
                            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_STUDENT_ID));
                            String firstName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FIRST_NAME));
                            String lastName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LAST_NAME));
                            int classId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_STUDENT_CLASS_ID));
                            String gender = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_GENDER));
                            int age = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_AGE));
                            students.add(new Student(id, firstName, lastName, classId, gender, age));
                        }
                        Log.d(TAG, "getAll: " + Thread.currentThread().getName());
                        cursor.close();
                        return students;
                    }
                });
    }

    @Override
    public Single<Cursor> getAllClassRoomsEntries() {
        database = dbHelper.getWritableDatabase();
        String[] columns = new String[]{DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_CLASSNAME,
                DatabaseHelper.COLUMN_CLASSNUMBER, DatabaseHelper.COLUMN_STUDENTSCOUNT, DatabaseHelper.COLUMN_FLOOR};
        return Single.just(database.query(DatabaseHelper.TABLE_CLASSROOMS, columns, null, null, null, null, null));
    }

    private Single<Cursor> getAllStudentsEntries() {
        database = dbHelper.getWritableDatabase();

        String[] columns = new String[]{DatabaseHelper.COLUMN_STUDENT_ID, DatabaseHelper.COLUMN_FIRST_NAME,
                DatabaseHelper.COLUMN_LAST_NAME, DatabaseHelper.COLUMN_STUDENT_CLASS_ID, DatabaseHelper.COLUMN_GENDER, DatabaseHelper.COLUMN_AGE};
        return Single.just(database.query(DatabaseHelper.TABLE_STUDENTS, columns, null, null, null, null, null));
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
    public int deleteStudent(int studentId) {
        database = dbHelper.getWritableDatabase();
        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(studentId)};
        return database.delete(DatabaseHelper.TABLE_STUDENTS, whereClause, whereArgs);
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
