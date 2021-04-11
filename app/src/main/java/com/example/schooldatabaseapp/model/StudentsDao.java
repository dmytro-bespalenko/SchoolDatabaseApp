package com.example.schooldatabaseapp.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import io.reactivex.Single;

@Dao
public interface StudentsDao {

    @Query("SELECT * FROM EntityStudent")
    Single<List<Student>> getAllStudents();

    @Query("SELECT * FROM EntityClassRoom")
    Single<List<EntityClassRoom>> getAllClassRoom();

    @Insert
    void insert(Student student);

    @Delete
    void delete(Student studentId);

    @Update
    void update(Student student);




}
