package com.example.schooldatabaseapp.model;

import android.database.Cursor;

import java.util.List;

public interface StudentsRepository {


    List<Student> getAll();

    List<ClassRoom> getAllClassRoom();


    long getCount();

    long insert(Student student);

    int delete(int studentId);

    int update(Student student);

    void close();

    Student getById(int id);

    Cursor getAllEntries();

    void deleteAll();


}
