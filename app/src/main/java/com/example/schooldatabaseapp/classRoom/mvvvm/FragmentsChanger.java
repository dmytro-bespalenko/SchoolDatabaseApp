package com.example.schooldatabaseapp.classRoom.mvvvm;

import com.example.schooldatabaseapp.model.ClassRoom;

public interface FragmentsChanger {

    void openClassRoomEditFragment(ClassRoom classRoom);

    void openClassRoomDetailsFragment(ClassRoom classRoom);

    void openAddClassRoomFragment();

    void openAddStudentFragment();
}
