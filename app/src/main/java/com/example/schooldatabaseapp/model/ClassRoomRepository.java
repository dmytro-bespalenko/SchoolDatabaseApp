package com.example.schooldatabaseapp.model;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface ClassRoomRepository {

    Single<List<ClassRoom>> getAllClassrooms();


    Completable insert(ClassRoom classRoom);

    Completable delete(Integer classRoom);

    Completable update(ClassRoom classRoom);


}