package com.example.schooldatabaseapp.model;

import android.database.Cursor;

import java.util.List;

public interface ClassRoomRepository {

    List<ClassRoom> getAll();

    long getCount();

    List<Student> getAllStudents();

    long insert(ClassRoom classRoom);

    int delete(int studentId);

    int deleteStudent(int studentId);

    int update(ClassRoom classRoom);

    void close();

    ClassRoom getById(int id);

    Cursor getAllClassRoomsEntries();

    void deleteAll();

}