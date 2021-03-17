package com.example.schooldatabaseapp.model;

import java.util.List;

public interface ClassRoomRepository {

    List<ClassRoom> getAll();

    long getCount();

    long insert(ClassRoom classRoom);

    int delete(int classId);

    int update(ClassRoom classRoom);

    void close();

    ClassRoom getById(int id);

    void deleteAll();

}