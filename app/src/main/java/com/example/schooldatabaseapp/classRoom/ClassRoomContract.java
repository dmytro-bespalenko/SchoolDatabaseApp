package com.example.schooldatabaseapp.classRoom;

import com.example.schooldatabaseapp.model.ClassRoom;

import java.util.List;

public interface ClassRoomContract {

    interface View {

        void updateRooms(List<ClassRoom> all);

        void deleteClassRoom(int position);

        void openFragment(ClassRoom classRoom);
    }

    interface Presenter {


        void updateClassRooms();

        void onItemWasLongClick(List<ClassRoom> all, int adapterPosition);


        void openEditFragment(ClassRoom classRoom);
    }


}
