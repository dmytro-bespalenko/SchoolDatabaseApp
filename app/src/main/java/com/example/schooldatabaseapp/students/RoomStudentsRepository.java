package com.example.schooldatabaseapp.students;

import android.database.Cursor;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomDao;
import com.example.schooldatabaseapp.model.RoomClassRoomRepository;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.model.StudentsDao;
import com.example.schooldatabaseapp.model.StudentsRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class RoomStudentsRepository implements StudentsRepository {

    private static RoomStudentsRepository instance;
    private final StudentsDao dao;

    public RoomStudentsRepository(StudentsDao dao) {
        this.dao = dao;
    }
    public static synchronized RoomStudentsRepository getInstance() {
        if (instance == null) {
            throw new RuntimeException();
        }
        return instance;
    }

    public static void initInstance(StudentsDao studentDao) {
        if (studentDao != null) {
            instance = new RoomStudentsRepository(studentDao);
        }
    }



    @Override
    public Single<List<Student>> getAllStudents() {
        return null;
    }

    @Override
    public Single<List<ClassRoom>> getAllClassRoom() {
        return null;
    }

    @Override
    public Completable insert(Student student) {
        return null;
    }

    @Override
    public Completable delete(int studentId) {
        return null;
    }

    @Override
    public Single<Student> update(Student student) {
        return null;
    }

    @Override
    public Single<Cursor> getAllEntries() {
        return null;
    }
}
