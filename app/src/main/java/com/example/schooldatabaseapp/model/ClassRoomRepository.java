package com.example.schooldatabaseapp.model;

import android.database.Cursor;

import java.util.List;

import io.reactivex.Single;

public interface ClassRoomRepository {

    Single<List<ClassRoom>> getAllClassrooms();

    long getCount();

    Single<List<Student>> getAllStudents();

    long insert(ClassRoom classRoom);

    int delete(int studentId);

    int deleteStudent(int studentId);

    int update(ClassRoom classRoom);

    void close();

    ClassRoom getById(int id);

    Single<Cursor> getAllClassRoomsEntries();

    void deleteAll();

}