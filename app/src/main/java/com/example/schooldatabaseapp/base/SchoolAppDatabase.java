package com.example.schooldatabaseapp.base;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.schooldatabaseapp.model.ClassRoomDao;
import com.example.schooldatabaseapp.model.EntityClassRoom;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.model.StudentsDao;

@Database(entities = {Student.class, EntityClassRoom.class}, version = 1)

public abstract class SchoolAppDatabase extends RoomDatabase {

    public abstract StudentsDao studentDao();
    public abstract ClassRoomDao classRoomDao();

    private SchoolAppDatabase schoolAppDatabase;

    public SchoolAppDatabase(SchoolAppDatabase schoolAppDatabase) {
        this.schoolAppDatabase = schoolAppDatabase;
    }


}
