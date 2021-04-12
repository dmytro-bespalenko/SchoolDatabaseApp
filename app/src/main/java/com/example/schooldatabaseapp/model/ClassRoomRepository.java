package com.example.schooldatabaseapp.model;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface ClassRoomRepository {

    Single<List<ClassRoom>> getAllClassrooms();

    Single<List<Student>> getAllStudents();

    Single<Long> insert(ClassRoom classRoom);

    Completable delete(Integer classRoom);

    Completable deleteStudent(int studentId);

    Completable update(ClassRoom classRoom);


}