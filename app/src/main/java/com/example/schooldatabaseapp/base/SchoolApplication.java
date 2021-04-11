package com.example.schooldatabaseapp.base;

import android.app.Application;

import androidx.room.Room;

import com.example.schooldatabaseapp.model.RoomClassRoomRepository;
import com.example.schooldatabaseapp.repositories.DatabaseClassRoomRepository;
import com.example.schooldatabaseapp.repositories.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.students.RoomStudentsRepository;

public class SchoolApplication extends Application {

    private SchoolAppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(this, SchoolAppDatabase.class, "database").build();
        DatabaseStudentsRepository.initInstance(this);
        DatabaseClassRoomRepository.initInstance(this);

        RoomClassRoomRepository.initInstance(database.classRoomDao());
        RoomStudentsRepository.initInstance(database.studentDao());

    }

}
