package com.example.schooldatabaseapp.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface ClassRoomDao {

    @Query("SELECT * FROM EntityClassRoom")
    Single<List<EntityClassRoom>> getAllClassrooms();

    @Query("SELECT * FROM EntityStudent")
    Single<List<EntityStudent>> getAllStudents();

    @Insert
    Single<Long> insert(EntityClassRoom classRoom);

    @Delete
    Completable delete(int classId);

    @Delete
    Completable deleteStudent(int studentId);

    @Update
    void update(EntityClassRoom classRoom);





}