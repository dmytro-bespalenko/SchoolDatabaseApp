package com.example.schooldatabaseapp.dataBase;

import android.annotation.SuppressLint;
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

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

@SuppressLint("CheckResult")

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
                        Log.d(TAG, "getAll: " + Thread.currentThread().getName());
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
                            Log.d(TAG, "getAllCassRoom: " + Thread.currentThread().getName());
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
    public Completable insert(Student student) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
                database = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(DatabaseHelper.COLUMN_FIRST_NAME, student.getFirstName());
                cv.put(DatabaseHelper.COLUMN_LAST_NAME, student.getLastName());
                cv.put(DatabaseHelper.COLUMN_STUDENT_CLASS_ID, student.getClassId());
                cv.put(DatabaseHelper.COLUMN_GENDER, student.getGender());
                cv.put(DatabaseHelper.COLUMN_AGE, student.getAge());
                Log.d(TAG, "insert: " + Thread.currentThread().getName());
                try {
                    database.insert(DatabaseHelper.TABLE_STUDENTS, null, cv);
                } catch (Exception e) {
                    emitter.onError(e);
                } finally {
                    emitter.onComplete();
                }

            }
        });
    }

    @Override
    public Completable delete(int studentId) {

        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
                database = dbHelper.getWritableDatabase();
                String whereClause = "_id = ?";
                String[] whereArgs = new String[]{String.valueOf(studentId)};
                try {
                    database.delete(DatabaseHelper.TABLE_STUDENTS, whereClause, whereArgs);
                    Log.d(TAG, "delete: " + Thread.currentThread().getName());
                } catch (Exception e) {
                    emitter.onError(e);
                } finally {
                    emitter.onComplete();
                    Log.d(TAG, "subscribe: onComplete");
                }


            }
        });
    }

    @Override
    public Single<Student> update(Student student) {
        return Single.fromPublisher(publisher -> {
            database = dbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(DatabaseHelper.COLUMN_FIRST_NAME, student.getFirstName());
            cv.put(DatabaseHelper.COLUMN_LAST_NAME, student.getLastName());
            cv.put(DatabaseHelper.COLUMN_STUDENT_CLASS_ID, student.getClassId());
            cv.put(DatabaseHelper.COLUMN_GENDER, student.getGender());
            cv.put(DatabaseHelper.COLUMN_AGE, student.getAge());
            try {
                database.update(DatabaseHelper.TABLE_STUDENTS, cv, DatabaseHelper.COLUMN_STUDENT_ID + " = ?", new String[]{String.valueOf(student.getId())});
                publisher.onNext(student);
                Log.d(TAG, "update fromPublisher: " + Thread.currentThread().getName());
            } catch (Exception e) {
                publisher.onError(e);
            } finally {
                publisher.onComplete();
            }


        });
    }

    @Override
    public Single<Cursor> getAllEntries() {
        database = dbHelper.getWritableDatabase();
        String[] columns = new String[]{DatabaseHelper.COLUMN_STUDENT_ID, DatabaseHelper.COLUMN_FIRST_NAME,
                DatabaseHelper.COLUMN_LAST_NAME, DatabaseHelper.COLUMN_STUDENT_CLASS_ID, DatabaseHelper.COLUMN_GENDER, DatabaseHelper.COLUMN_AGE};
        return Single.just(database.query(DatabaseHelper.TABLE_STUDENTS, columns, null, null, null, null, DatabaseHelper.COLUMN_LAST_NAME));
    }

}
