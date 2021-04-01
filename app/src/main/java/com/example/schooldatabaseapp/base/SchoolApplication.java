package com.example.schooldatabaseapp.base;

import android.app.Application;

import com.example.schooldatabaseapp.dataBase.DatabaseClassRoomRepository;
import com.example.schooldatabaseapp.dataBase.DatabaseStudentsRepository;

public class SchoolApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseStudentsRepository.initInstance(this);
        DatabaseClassRoomRepository.initInstance(this);
    }
}
