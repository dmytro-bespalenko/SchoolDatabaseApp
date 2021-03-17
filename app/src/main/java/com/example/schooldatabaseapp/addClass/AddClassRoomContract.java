package com.example.schooldatabaseapp.addClass;

public interface AddClassRoomContract {

    interface View {

    }

    interface Presenter {

        void addNewClassRoom(String className, int classNumber, int floor);

    }

}
