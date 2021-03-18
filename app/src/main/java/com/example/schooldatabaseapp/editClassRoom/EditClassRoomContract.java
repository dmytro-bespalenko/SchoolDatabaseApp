package com.example.schooldatabaseapp.editClassRoom;

import com.example.schooldatabaseapp.model.ClassRoom;

import java.util.List;

public interface EditClassRoomContract {

    interface View {

        void updateRooms(List<ClassRoom> all);

    }

    interface Presenter {

        void editClassRoom(int pos);

        void onEditButtonWasClicked(List<ClassRoom> classRooms, int adapterPosition);

        void updateClassRooms();
    }
}
