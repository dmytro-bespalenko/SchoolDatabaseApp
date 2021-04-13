package com.example.schooldatabaseapp.classRoom;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;

public interface ClassRoomListContract {

    interface View {

        void updateRooms(List<ClassRoom> all);


        void openClassRoomEditFragment(ClassRoom classRoom);

        void openClassRoomDetailsFragment(ClassRoom classRoom);

        void openAddClassRoomFragment();

        void openAddStudentFragment();

    }

    interface Presenter {


        void updateClassRooms();

        void openEditFragment(ClassRoom classRoom);


        void onItemClickListener(ClassRoom classRoom);

        void showAddClassFragment();

        void showAddStudentFragment();

        void deleteClassRoom(ClassRoom finalPosition);


    }


}
