package com.example.schooldatabaseapp.model;

import android.database.Cursor;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface ClassRoomRepository {

    Single<List<ClassRoom>> getAllClassrooms();


    Single<List<Student>> getAllStudents();

    Single<Long> insert(ClassRoom classRoom);

    Completable delete(int classId);

    Completable deleteStudent(int studentId);

    Completable update(ClassRoom classRoom);


    Single<ClassRoom> getById(int id);

    Single<Cursor> getAllClassRoomsEntries();

    void deleteAll();

}