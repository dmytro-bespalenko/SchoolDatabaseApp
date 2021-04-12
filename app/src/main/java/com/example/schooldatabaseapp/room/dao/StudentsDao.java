package com.example.schooldatabaseapp.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.room.entity.EntityClassRoom;
import com.example.schooldatabaseapp.room.entity.EntityStudent;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface StudentsDao {

    @Query("SELECT * FROM EntityStudent")
    Single<List<EntityStudent>> getAllStudents();

    @Query("SELECT * FROM EntityClassRoom")
    Single<List<EntityClassRoom>> getAllClassRoom();

    @Insert
    Completable insert(EntityStudent student);

    @Query("DELETE FROM entitystudent WHERE id = :studentId")
    Completable delete(Integer studentId);

    @Update
    Completable update(EntityStudent student);


}
