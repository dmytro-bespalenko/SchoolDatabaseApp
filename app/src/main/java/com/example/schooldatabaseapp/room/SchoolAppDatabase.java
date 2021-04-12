package com.example.schooldatabaseapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.schooldatabaseapp.room.dao.ClassRoomDao;
import com.example.schooldatabaseapp.room.dao.StudentsDao;
import com.example.schooldatabaseapp.room.entity.EntityClassRoom;
import com.example.schooldatabaseapp.room.entity.EntityStudent;

@Database(entities = {EntityStudent.class, EntityClassRoom.class}, version = 1)

public abstract class SchoolAppDatabase extends RoomDatabase {

    public abstract StudentsDao studentDao();
    public abstract ClassRoomDao classRoomDao();


}
