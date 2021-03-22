package com.example.schooldatabaseapp.model;

import android.database.Cursor;

import java.util.List;

public interface ClassRoomRepository {

    List<ClassRoom> getAll();

    long getCount();

    long insert(ClassRoom classRoom);

    int delete(int classId);

    int update(ClassRoom classRoom);

    void close();

    ClassRoom getById(int id);

    Cursor getAllEntries();

    void deleteAll();

}