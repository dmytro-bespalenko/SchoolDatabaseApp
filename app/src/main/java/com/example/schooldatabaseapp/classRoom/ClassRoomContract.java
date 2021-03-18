package com.example.schooldatabaseapp.classRoom;

import com.example.schooldatabaseapp.model.ClassRoom;

import java.util.List;

public interface ClassRoomContract {

    interface View {

        void updateRooms(List<ClassRoom> all);

        void deleteClassRoom(int position);
    }

    interface Presenter {


        void updateClassRooms();

        void onItemWasLongClick(List<ClassRoom> all, int adapterPosition);
    }


}
