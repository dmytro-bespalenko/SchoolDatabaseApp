package com.example.schooldatabaseapp;

import com.example.schooldatabaseapp.model.ClassRoom;

import java.util.List;

public interface ClassRoomContract {

    interface View {
        void onActivityClickBack(int classRoom);

    }

    interface Presenter {
        void onButtonWasClicked();

    }

    interface Repository {

        List<ClassRoom> getClassRooms();

        long getCount();

        ClassRoom getClassRoom(int id);

        long insert(ClassRoom classRoom);

        int delete(int classId);

        int update(ClassRoom classRoom);

    }
}
