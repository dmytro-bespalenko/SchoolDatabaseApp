package com.example.schooldatabaseapp.repositories;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.schooldatabaseapp.base.DatabaseHelper;
import com.example.schooldatabaseapp.base.SchoolApplication;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.model.Student;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

@SuppressLint("Recycle")

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

    private Single<Cursor> getAllStudentsEntries() {
        database = dbHelper.getWritableDatabase();
        String[] columns = new String[]{DatabaseHelper.COLUMN_STUDENT_ID, DatabaseHelper.COLUMN_FIRST_NAME,
                DatabaseHelper.COLUMN_LAST_NAME, DatabaseHelper.COLUMN_STUDENT_CLASS_ID, DatabaseHelper.COLUMN_GENDER, DatabaseHelper.COLUMN_AGE};
        return Single.just(database.query(DatabaseHelper.TABLE_STUDENTS, columns, null, null, null, null, null));
    }

    public Single<Cursor> getAllClassRoomsEntries() {
        database = dbHelper.getWritableDatabase();
        String[] columns = new String[]{DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_CLASSNAME,
                DatabaseHelper.COLUMN_CLASSNUMBER, DatabaseHelper.COLUMN_STUDENTSCOUNT, DatabaseHelper.COLUMN_FLOOR};
        return Single.just(database.query(DatabaseHelper.TABLE_CLASSROOMS, columns, null, null, null, null, null));
    }


    @Override
    public Single<Long> insert(ClassRoom classRoom) {
        database = dbHelper.getWritableDatabase();
        return Single.fromPublisher(publisher -> {
            ContentValues cv = new ContentValues();
            cv.put(DatabaseHelper.COLUMN_CLASSNAME, classRoom.getClassName());
            cv.put(DatabaseHelper.COLUMN_CLASSNUMBER, classRoom.getClassNumber());
            cv.put(DatabaseHelper.COLUMN_STUDENTSCOUNT, classRoom.getStudentsCount());
            cv.put(DatabaseHelper.COLUMN_FLOOR, classRoom.getFloor());
            try {
                Log.d(TAG, "insert: ClassRoom " + Thread.currentThread().getName());
                database.insert(DatabaseHelper.TABLE_CLASSROOMS, null, cv);
            } catch (Exception e) {
                publisher.onError(e);
            } finally {
                publisher.onComplete();
            }


        });
    }

    @Override
    public Completable delete(int classId) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
                database = dbHelper.getWritableDatabase();
                String whereClause = "_id = ?";
                String[] whereArgs = new String[]{String.valueOf(classId)};
                Log.d(TAG, "delete:ccc " + Thread.currentThread().getName());
                try {
                    database.delete(DatabaseHelper.TABLE_CLASSROOMS, whereClause, whereArgs);
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
    public Completable deleteStudent(int studentId) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
                database = dbHelper.getWritableDatabase();
                String whereClause = "_id = ?";
                String[] whereArgs = new String[]{String.valueOf(studentId)};
                try {
                    database.delete(DatabaseHelper.TABLE_STUDENTS, whereClause, whereArgs);
                    Log.d(TAG, "deleteStudentSingle: " + Thread.currentThread().getName());
                } catch (Exception e) {
                    emitter.onError(e);
                } finally {
                    emitter.onComplete();
                }

            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public Completable update(ClassRoom classRoom) {

        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
                database = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(DatabaseHelper.COLUMN_CLASSNAME, classRoom.getClassName());
                cv.put(DatabaseHelper.COLUMN_CLASSNUMBER, classRoom.getClassNumber());
                cv.put(DatabaseHelper.COLUMN_STUDENTSCOUNT, classRoom.getStudentsCount());
                cv.put(DatabaseHelper.COLUMN_FLOOR, classRoom.getFloor());
                try {
                    database.update(DatabaseHelper.TABLE_CLASSROOMS, cv, DatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(classRoom.getClassId())});
                    Log.d(TAG, "getById:Class " + Thread.currentThread().getName());

                } catch (Exception e) {
                    emitter.onError(e);
                } finally {
                    emitter.onComplete();
                }

            }
        });

//        Single.fromCallable(database.rawQuery(query, columns));
//        database.update(DatabaseHelper.TABLE_CLASSROOMS, cv, whereClause, null)
//        getAllClassRoomsId().
//                map(new Function<Cursor, Cursor>() {
//                    @Override
//                    public Cursor apply(@NonNull Cursor cursor) throws Exception {
//                        ContentValues cv = new ContentValues();
//                        cv.put(DatabaseHelper.COLUMN_CLASSNAME, classRoom.getClassName());
//                        cv.put(DatabaseHelper.COLUMN_CLASSNUMBER, classRoom.getClassNumber());
//                        cv.put(DatabaseHelper.COLUMN_STUDENTSCOUNT, classRoom.getStudentsCount());
//                        cv.put(DatabaseHelper.COLUMN_FLOOR, classRoom.getFloor());
//                        database.update(DatabaseHelper.TABLE_CLASSROOMS, cv, whereClause, null);
//                        Log.d(TAG, "runClass: " + Thread.currentThread().getName());
//
//                        return cursor;
//                    }
//                })
//        return Single.fromCallable(new Callable<Cursor>() {
//            @Override
//            public Cursor call() throws Exception {
//                database = dbHelper.getWritableDatabase();
//
//                String whereClause = "_id = ?";
//                String query = "SELECT * FROM " + DatabaseHelper.TABLE_CLASSROOMS + " WHERE " + whereClause;
//                ContentValues cv = new ContentValues();
//                cv.put(DatabaseHelper.COLUMN_CLASSNAME, classRoom.getClassName());
//                cv.put(DatabaseHelper.COLUMN_CLASSNUMBER, classRoom.getClassNumber());
//                cv.put(DatabaseHelper.COLUMN_STUDENTSCOUNT, classRoom.getStudentsCount());
//                cv.put(DatabaseHelper.COLUMN_FLOOR, classRoom.getFloor());
//                Cursor cursor = database.rawQuery(query, null);
//                database.update(DatabaseHelper.TABLE_CLASSROOMS, cv, DatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(classRoom.getClassId())});
//
//                Log.d(TAG, "getById:Class " + Thread.currentThread().getName());
//
//                return cursor;
//            }
//        });


    }


//    public Single<Cursor> getAllClassRoomsId() {
//        database = dbHelper.getWritableDatabase();
//        String[] columns = new String[]{DatabaseHelper.COLUMN_ID};
//        return Single.just(database.query(DatabaseHelper.TABLE_CLASSROOMS, columns, null, null, null, null, null));
//    }

}
