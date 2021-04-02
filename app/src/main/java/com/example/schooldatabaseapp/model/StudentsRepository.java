package com.example.schooldatabaseapp.model;

import android.database.Cursor;

import java.util.List;

import io.reactivex.Single;

public interface StudentsRepository {


    Single<List<Student>> getAllStudents();

    Single<List<ClassRoom>> getAllClassRoom();


    long insert(Student student);

    int delete(int studentId);

    int update(Student student);

    void close();

    Student getById(int id);

    Single<Cursor> getAllEntries();

    void deleteAll();


}
