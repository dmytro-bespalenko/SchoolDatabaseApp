package com.example.schooldatabaseapp.model;

import android.database.Cursor;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface StudentsRepository {


    Single<List<Student>> getAllStudents();

    Single<List<ClassRoom>> getAllClassRoom();


    Completable insert(Student student);

    Completable delete(int studentId);

    Single<Student> update(Student student);

    void close();

    Student getById(int id);

    Single<Cursor> getAllEntries();

    void deleteAll();


}
