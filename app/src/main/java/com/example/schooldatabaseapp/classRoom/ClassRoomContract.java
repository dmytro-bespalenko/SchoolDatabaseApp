package com.example.schooldatabaseapp.classRoom;

import androidx.fragment.app.Fragment;

import com.example.schooldatabaseapp.addClass.AddClassRoomFragment;
import com.example.schooldatabaseapp.model.ClassRoom;

import java.util.List;

public interface ClassRoomContract {

    interface View {

        void updateRooms(List<ClassRoom> all);

        void deleteClassRoom(int position);

        void openClassRoomEditFragment(ClassRoom classRoom);

        void openClassRoomDetailsFragment(ClassRoom classRoom);

        void openOtherFragment();
    }

    interface Presenter {


        void updateClassRooms();

        void onItemWasLongClick(List<ClassRoom> all, int adapterPosition);


        void openEditFragment(ClassRoom classRoom);


        void onItemClickListener(ClassRoom classRoom);

        void showOtherFragment();
    }


}
