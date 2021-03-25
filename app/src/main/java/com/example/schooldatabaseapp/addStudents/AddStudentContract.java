package com.example.schooldatabaseapp.addStudents;

import com.example.schooldatabaseapp.model.ClassRoom;

import java.util.List;

interface AddStudentContract {

    interface Presenter {

        void addNewStudent(String firstName, String lastName, int selectedClass, String gender, int age);

        List<ClassRoom> getClassRooms();

    }

    interface View {


    }

}
