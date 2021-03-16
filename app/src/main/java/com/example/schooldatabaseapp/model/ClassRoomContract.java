package com.example.schooldatabaseapp.model;

import java.util.List;

public interface ClassRoomContract {

    interface View {
        void onActivityClickBack(int classRoom);

    }

    interface Presenter {

        void onButtonWasClicked(int id);
        void onAddButtonClicked();

    }

    interface Repository {

        List<ClassRoom> getClassRooms();

        long getCount();

        ClassRoom getClassRoom(int id);

        long insert(ClassRoom classRoom);

        int delete(int classId);

        int update(ClassRoom classRoom);

        void close();

        DatabaseRepository open();
    }
}
