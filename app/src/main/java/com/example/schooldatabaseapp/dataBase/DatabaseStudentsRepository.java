package com.example.schooldatabaseapp.dataBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.schooldatabaseapp.base.DatabaseHelper;
import com.example.schooldatabaseapp.base.SchoolApplication;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.model.StudentsRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class DatabaseStudentsRepository implements StudentsRepository {


    private static final String TAG = "My_Tag";
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private static DatabaseStudentsRepository instance;


    private DatabaseStudentsRepository(SchoolApplication application) {
        this.dbHelper = new DatabaseHelper(application);
    }

    public static synchronized DatabaseStudentsRepository getInstance() {
        if (instance == null) {
            throw new RuntimeException();
        }

        return instance;
    }

    public static void initInstance(SchoolApplication schoolApplication) {
        if (schoolApplication != null) {
            instance = new DatabaseStudentsRepository(schoolApplication);
        }
    }


    @Override
    public Single<List<Student>> getAllStudents() {
        database = dbHelper.getWritableDatabase();
        return getAllEntries()
                .map(new Function<Cursor, List<Student>>() {
                    @Override
                    public List<Student> apply(@NonNull Cursor cursor) throws Exception {
                        Log.d(TAG, "apply: " + Thread.currentThread().getName());
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
                        Log.d(TAG, "getAll: " + students.size());
                        cursor.close();
                        return students;
                    }
                });
    }

    @Override
    public Single<List<ClassRoom>> getAllClassRoom() {
        database = dbHelper.getWritableDatabase();
        return getAllClassRoomEntries()
                .map(new Function<Cursor, List<ClassRoom>>() {
                    @Override
                    public List<ClassRoom> apply(@NonNull Cursor cursor) throws Exception {
                        List<ClassRoom> classrooms = new ArrayList<>();
                        while (cursor.moveToNext()) {
                            Log.d(TAG, "getAllCalssRoom: " + Thread.currentThread());
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
                });
    }

    public Single<Cursor> getAllClassRoomEntries() {
        database = dbHelper.getWritableDatabase();
        String[] columns = new String[]{DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_CLASSNAME,
                DatabaseHelper.COLUMN_CLASSNUMBER, DatabaseHelper.COLUMN_STUDENTSCOUNT, DatabaseHelper.COLUMN_FLOOR};
        return Single.just(database.query(DatabaseHelper.TABLE_CLASSROOMS, columns, null, null, null, null, DatabaseHelper.COLUMN_CLASSNAME));
    }


    @Override
    public long insert(Student student) {
        database = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DatabaseHelper.COLUMN_FIRST_NAME, student.getFirstName());
        cv.put(DatabaseHelper.COLUMN_LAST_NAME, student.getLastName());
        cv.put(DatabaseHelper.COLUMN_STUDENT_CLASS_ID, student.getClassId());
        cv.put(DatabaseHelper.COLUMN_GENDER, student.getGender());
        cv.put(DatabaseHelper.COLUMN_AGE, student.getAge());

        return database.insert(DatabaseHelper.TABLE_STUDENTS, null, cv);
    }

    @Override
    public int delete(int studentId) {
        database = dbHelper.getWritableDatabase();
        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(studentId)};
        return database.delete(DatabaseHelper.TABLE_STUDENTS, whereClause, whereArgs);
    }

    @Override
    public int update(Student student) {
        database = dbHelper.getWritableDatabase();
        String whereClause = DatabaseHelper.COLUMN_STUDENT_ID + "=" + student.getId();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_FIRST_NAME, student.getFirstName());
        cv.put(DatabaseHelper.COLUMN_LAST_NAME, student.getLastName());
        cv.put(DatabaseHelper.COLUMN_STUDENT_CLASS_ID, student.getClassId());
        cv.put(DatabaseHelper.COLUMN_GENDER, student.getGender());
        cv.put(DatabaseHelper.COLUMN_AGE, student.getAge());
        Log.d(TAG, "run: " + Thread.currentThread().getName());

        return database.update(DatabaseHelper.TABLE_STUDENTS, cv, whereClause, null);
    }

    @Override
    public void close() {
        dbHelper.close();
    }

    @Override
    public Student getById(int id) {
        database = dbHelper.getWritableDatabase();
        Student student = null;
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_STUDENTS + " WHERE " + DatabaseHelper.COLUMN_ID;
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            String firstName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FIRST_NAME));
            String lastName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LAST_NAME));
            int classId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_CLASSNAME));
            String gender = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_GENDER));
            int age = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_AGE));
            student = new Student(firstName, lastName, classId, gender, age);
        }
        cursor.close();

        return student;
    }

    @Override
    public Single<Cursor> getAllEntries() {
        database = dbHelper.getWritableDatabase();
        String[] columns = new String[]{DatabaseHelper.COLUMN_STUDENT_ID, DatabaseHelper.COLUMN_FIRST_NAME,
                DatabaseHelper.COLUMN_LAST_NAME, DatabaseHelper.COLUMN_STUDENT_CLASS_ID, DatabaseHelper.COLUMN_GENDER, DatabaseHelper.COLUMN_AGE};
        return Single.just(database.query(DatabaseHelper.TABLE_STUDENTS, columns, null, null, null, null, DatabaseHelper.COLUMN_LAST_NAME));
    }

    @Override
    public void deleteAll() {
        dbHelper.deleteAll(database, DatabaseHelper.TABLE_STUDENTS);
        close();
    }
}
