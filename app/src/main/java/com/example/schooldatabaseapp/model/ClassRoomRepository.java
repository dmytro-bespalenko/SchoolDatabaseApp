package com.example.schooldatabaseapp.model;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface ClassRoomRepository {

    Single<List<ClassRoom>> getAllClassrooms();

    Single<List<Student>> getAllStudents();

    //    void insert(ClassRoom classRoom);
    Single<Long> insert(ClassRoom classRoom);

    Completable delete(int classId);
//    void delete(int classId);

    Completable deleteStudent(int studentId);
//    void deleteStudent(int studentId);

    Completable update(ClassRoom classRoom);
//    void update(ClassRoom classRoom);


}