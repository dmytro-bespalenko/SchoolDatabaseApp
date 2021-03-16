package com.example.schooldatabaseapp.classRoom;

import com.example.schooldatabaseapp.model.ClassRoom;

import java.util.List;

public interface ClassRoomContract {

    interface View {

        void updateRooms(List<ClassRoom> all);
    }

    interface Presenter {

        void onButtonWasClicked(int id);
        void onAddButtonClicked();

        void refresh();
    }



}
