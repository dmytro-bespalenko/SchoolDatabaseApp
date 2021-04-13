package com.example.schooldatabaseapp.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.schooldatabaseapp.room.entity.EntityClassRoom;
import com.example.schooldatabaseapp.room.entity.EntityStudent;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ClassRoomDao {

    @Query("SELECT * FROM EntityClassRoom")
    Single<List<EntityClassRoom>> getAllClassrooms();

    @Query("SELECT * FROM EntityStudent")
    Single<List<EntityStudent>> getAllStudents();

    @Insert(onConflict = REPLACE)
    Completable insert(EntityClassRoom classRoom);

    @Query("DELETE FROM entityclassroom WHERE id = :classId")
    Completable delete(Integer classId);

    @Update()
    Completable update(EntityClassRoom classRoom);


}