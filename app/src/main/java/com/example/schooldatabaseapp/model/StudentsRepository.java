package com.example.schooldatabaseapp.model;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface StudentsRepository {

    Single<List<Student>> getAllStudents();

    Single<List<ClassRoom>> getAllClassRoom();

    Completable insert(Student student);

    Completable delete(int studentId);

    Completable update(Student student);



}
